# Broadcast Receiver

[`BroadcastReceiver`](http://www.vogella.com/tutorials/AndroidBroadcastReceiver/article.html)
[`Android组件系列----BroadcastReceiver广播接收器`](http://www.cnblogs.com/smyhvae/p/3960623.html)
[`Android中BroadCastReceiver使用（整理）`](http://www.cnblogs.com/jico/articles/1838293.html)

_A broadcast receiver (receiver) is an Android component which allows you to register for system or application events. All registered receivers for an event are notified by the Android runtime once this event happens_

**广播接收者** 用于在`运行时`接受`已注册`的`系统或应用`发出的`广播`.

_和所有组件一样，广播对象也是在应用进程的主线程中被构造，所以广播对象的执行必须是要同步且快速的。也不推荐在里面开子线程，因为往往线程还未结束，广播对象就已经执行完毕被系统销毁。如果需要完成一项比较耗时的工作 , 应该通过发送 Intent 给 Service, 由 Service 来完成_

## Broadcast和使用BroadcastReceiver过滤接收的过程

发送者，把要发送的信息和用于过滤的信息(如Action、Category)装入一个`Intent`对象，然后通过调用 `sendOrderBroadcast()`或`sendStickyBroadcast()`方法，把 Intent对象以广播方式发送出去.

当Intent发送以后，`所有已经注册`的`BroadcastReceiver`会检查`注册时`的`IntentFilter`是否与`发送`的Intent相`匹配`，若匹配则就会`调用BroadcastReceiver`的`onReceive()`方法接收广播信息。所以当我们定义一个BroadcastReceiver的时候，都需要实现onReceive()方法.

## 两种注册方式

### 静态注册(代码中注册)

**注：动态注册的广播接收器一定要在`onDestroy`取消注册**

具体实现：   
- 创建类`Class`继承`BroadcastReceiver`并`注册监听`，然后覆写`onReceive()`

示例：动态注册监听网络变化

- 查询系统网络是需要权限的需要在清单文件中添加

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```

- 创建内部类类实现`onReceive`

```java
class netWorkChangeReceiver extends BroadcastReceiver {
    
    @Override
    public void onReceive(Context context, Intent intent) {
        //通过getSystemService()方法得到connectionManager这个系统服务类，专门用于管理网络连接
        ConnectivityManager connectionManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isAvailable()){
            Toast.makeText(context, "network is available",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "network is unavailable",Toast.LENGTH_SHORT).show();
        }
    }
}
```

- 在`onCreate`方法中进行`动态注册`，然后在`onDestroy`方法中进行`取消注册`

```java
private IntentFilter intentFilter;
private netWorkChangeReceiver netWorkChangeReceiver;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    //动态注册：创建一个IntentFilter的实例，添加网络变化的广播(功能是对组件进行过滤，只获取需要的消息)
    intentFilter = new IntentFilter();
    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    //创建NetWorkChangeReceiver的实例，并调用Context.registerReceiver()进行注册接收者
    netWorkChangeReceiver = new netWorkChangeReceiver();
    registerReceiver(netWorkChangeReceiver, intentFilter); 
}

//取消注册，一定要记得，不然系统会报错
@Override
protected void onDestroy() {
    super.onDestroy();
    unregisterReceiver(netWorkChangeReceiver); // Context.unregisterReceiver()
}
```

### 动态注册(清单文件中注册AndroidManifest.xml)

_动态注册的方式比较灵活，但缺点是：必须在程序启动之后才能接收到广播，因为注册的逻辑是写在onCreate()方法中的_

示例：静态注册实现开机启动

- 创建类继承`BroadcastReceiver`覆写`onReceive`

```java
public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_SHORT).show();
    }
}
```

- 编辑清单文件AndroidManifest.xml添加注册

```xml
<uses-sdk
  android:minSdkVersion="16"
  android:targetSdkVersion="25" />
    <!-- 添加访问权限 -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>  

    <application
        android:allowBackup="true"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >
        <activity
            android:name="com.example.broadcastreceiver.MainActivity"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <!-- 注册接收器 -->
        <!-- name为接收器名称 -->
        <receiver android:name=".BootCompleteReceiver">
            <intent-filter >
                <!-- Android系统启动完成后会发出名为：android.intent.action.BOOT_COMPLETED的广播哦，所以我们需要添加意图过滤来匹配我们要监听的广播 -->
                <action android:name="android.intent.action.BOOT_COMPLETED"/>
            </intent-filter>
        </receiver>
    </application>
```

### 发送自定义广播

- 第一步:还是创建类`MyBroadcastReceiver`继承`BroadcastReceiver` 覆写 `onReceive`
```java
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
    }
}
```

- 第二步:在AndroidManifest.xml注册自定义的接收器,和广播类型
```xml
<receiver android:name=".MyBroadcastReceiver">
    <intent-filter >
        <!-- 定义匹配一个名为：com.example.broadcastreceiver.MY_BROADCAST的广播类型，稍后我们就应该发送对应的广播到该接收器 -->
        <action android:name="com.example.broadcastreceiver.MY_BROADCAST"/>
    </intent-filter>
</receiver>
```

- 第三步:修改MainActivity.java，添加Button的监听事件：点击按钮时，发送广播
```java
Button button1=(Button)findViewById(R.id.button1);
button1.setOnClickListener(new OnClickListener() {            
    @Override
    public void onClick(View view) {
        Intent intent =new Intent("com.example.broadcastreceiver.MY_BROADCAST"); // 创建意图，添加动作(Action)
        sendBroadcast(intent); // 通过sendBroadcast()发送
    }
});
```


### 发送有序广播

有序广播不仅有先后顺序，而且前面的广播还可以将后面的广播截断

```java
sendOrderedBroadcast(intent, null); // 第二个参数是一个与权限相关的字符串，这里传入null即可
```

改清单文件AndroidManifest.xml中对广播接收器的注册，通过`android:priority`设置优先级：`-1000~1000`，数值越大，优先级越高

```xml
<receiver android:name=".MyBroadcastReceiver">
    <intent-filter android:priority="100">
        <action android:name="com.example.broadcastreceiver.MY_BROADCAST"/>
    </intent-filter>
</receiver>
```

如果想要拦截这个广播，防止让后面的广播接收器也接收到了这个广播,可以再接收广播后使用`abortBroadcast()`截断向后传播

### 本地广播

以上我们发送和接收的广播全部都是属于全局广播，即发出去的广播可以被其他任何应用程序接收到，并且我们也可以接收来自于其他任何应用程序的广播。这样一来，必然会造成安全问题。于是便有了本地广播：即只能在本应用程序中发送和接收广播。这就要使用到了LocalBroadcastManager这个类来对广播进行管理。

```java
package com.example.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private IntentFilter intentFilter;

    private LocalReceiver localReceiver;

    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通过LocalBroadcastManager的getInstance()方法得到它的一个实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        "com.example.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);//调用sendBroadcast()方法发送广播
            }
        });
        //动态注册本地的广播接收器
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
```

**注** ：本地广播是无法通过静态注册的方式来接收的。因为静态注册主要就是为了让程序在未启动的情况下也能收到广播。而发送本地广播时，我们的程序肯定是已经启动了，没有必要使用到静态注册的功能

### System Events

| Event | Description |
| ----- | ----------- |
| Intent.ACTION_BOOT_COMPLETED | 启动完成，需要 android.permission.RECEIVE_BOOT_COMPLETED 权限 |
| Intent.ACTION_POWER_CONNECTED | 链接到外部电源（充电）|
| Intent.ACTION_POWER_DISCONNECTED | 断开外部电源 |
| Intent.ACTION_BATTERY_LOW | 电量过低 |
| Intent.ACTION_BATTERY_OKAY | 电量充足 |
| Intent.ACTION_TIME_TICK | 每分钟发送，表明时间在运行 |
| Intent.ACTION_TIME_CHANGED | 当用户更改了设备上的时间时发送 |
| Intent.ACTION_TIMEZONE_CHANGED | 当用更改了设备上的时区时发送 |
| Intent.ACTION_PACKAGE_ADDED | 当添加了新的包到设备上时发送 |
| Intent.ACTION_PACKAGE_REMOVED | 删除一个包时发送 |
| Intent.ACTION_PACKAGE_CHANGED | 当包被更改时，如改为disable  |
| Intent.ACTION_PACKAGE_RESTARTED | 当程序被中断，重新打开的时候 |
| Intent.ACTION_UID_REMOVED | 一个UID从系统删除的时候 |
| Intent.ACTION_SHUTDOWN | 关机的时候 |
| Intent.ACTION_BATTERY_CHANGED | 当电池的状态，充电改变时发送，需要 android.permission.BATTERY_STATS权限 |
| Intent.ACTION_ANSWER | 处理拨入的电话 |
| Intent.ACTION_DATE_CHANGED | 日期被改变 |
| Intent.ACTION_SCREEN_OFF | 屏幕被关闭 |
| Intent.ACTION_SCREEN_ON | 屏幕已经被打开 |
