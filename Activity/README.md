# Activity

Activity组件是四大组件之一，在应用中一个Activity可以用来表示一个界面， 中文意思也可以理解为“活动” ，即一个活动开始，代表Activity组件启动；活动结束，代表一个Activity的生命周期结束

## 活动声明

活动的声明需要放在AndroidManifest.xml的 `<application>`标签中，通过`<activity>`标签声明，如: 

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.xing.firstactivity">
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <!-- 声明一个活动 -->
        <!-- android:name 指定注册的活动页面,即 com.example.xing.firstactivity.MainActivity,由于manifest 的package属性已经指定了包名，因此可以省略 -->
        <activity
            android:name=".MainActivity"
            android:label="This is my first activity">
            <intent-filter>
                <!-- 指定当前活动为主活动页面 -->
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

## Activity生命周期

详见:[`Activity生命周期`](../Activity生命周期)

## Activity启动模式

Activity有四种启动模式：`standard` `singleTop` `singleTask` `singleInstance` 

- `standard`
  `模式启动模式`，每次激活Activity时都会创建新的Activity，并放入任务栈中
- `singleTop`
  如果在任务的栈顶正好存在该Activity的实例，就重用该实例，否则就会创建新的实例并放入栈顶(即使栈中已经存在该Activity实例，只要不在栈顶，都会创建实例)
- `singleTask`
  如果在栈中已经有该Activity的实例，就重用该实例(会调用实例的onNewIntent())。重用时，会让该实例回到栈顶，因此在它上面的实例将会被移除栈。如果栈中不存在该实例，将会创建新的实例放入栈中
- `singleInstance`
  在一个`新栈`中创建该Activity实例，并让多个应用共享改栈中的该Activity实例。一旦改模式的Activity的实例存在于某个栈中，任何应用再激活改Activity时都会重用该栈中的实例，其效果相当于多个应用程序共享一个应用，不管谁激活该Activity都会进入同一个应用中

通过在`AndroidManifest.xml`中的`activity`标签，配置属性`android:launchMode`设置加载模式,如：

```xml
<activity 
    android:name=".app.ExampleActivity"  
    android:label="@string/example_label"  
    android:theme="@style/Theme.Dialog"  
    android:launchMode="singleTask"  
</activity> 
```

- `android:name`: 指定活动的加载类，即页面展示时对应显示的Activity
- `android:label`: 指定活动的显示的名称

```xml
<application
    android:name="com.example.xing.MyApplication"
    android:allowBackup="true"
    android:icon="@drawable/ic_launcher"
    android:theme="@style/AppTheme" 
    android:label="@string/appication_app_name" >
    <activity
        android:name=".MainActivity"
        android:label="@string/app_name" >
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
</application>
```

上方的代码在 `appliation` 和 `activity` 中都设置`android:label`属性:
> 运行发现：结果为`@string/app_name`，即`activity`设置的`label`的名字。   
> 有以下几种情况：   
>（1）activity的优先级高于application，也就是说两者都设置这个标签的话，activity的值覆盖application   
>（2）application里设置了此标签，其他activity没有设置的情况下，应用程序名在桌面上的名字和所有activity的title是这个设置的标签   
>（3）application里设置了此标签，主activity中也设置了此标签，则应用程序名和主activity的title都是主activity中设置的标签，其他非主activity的title如果没有自己设置此标签，还是使用application中设置的标签，如果其他非主activity也设置了此标签，则其title就是自己设置的这个标签  

**activity和application里都可以设置android:label标签，activity的优先级高于application**

**`具体示例参见参考文档`**

## Activity的基本用法

- 隐藏标题栏

```java
requestWindowFeature(Window.FEATURE_NO_TITLE);
setContentView(R.layout.activity_main);
```

- 使用Toast

```java
button.setOnClickListener(new OnClickListener() {            
    @Override
    public void onClick(View view) {
        Toast.makeText(MainActivity.this, "message you want to display !",Toast.LENGTH_SHORT).show();                
    }
});
```

- Activity中启动另一个Activity

```java
Intent intent = new Intent();
//setClass函数的第一个参数是一个Context对象
//Context是一个类，Activity是Context类的子类，也就是说，所有的Activity对象，都可以向上转型为Context对象
//setClass函数的第二个参数是一个Class对象，在当前场景下，应该传入需要被启动的Activity类的class对象
intent.setClass(MainActivity.this, SecondActivity.class);
startActivity(intent);
```

- android:onClick="method"属性设置跳转

```xml
<Button
  android:id="@+id/button1"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:layout_below="@+id/textView1"
  android:layout_marginTop="22dp"
  android:onClick="toSecondActivity" 
  android:text="SecondActivity" />
  <!-- 通过android:onClick属性设置点击按钮执行方法 -->
```
- `android:text`: 指定组件显示的文本内容

更多布局参数查看 [UI布局](../UI布局#布局属性)

```java
public void toSecondActivity(View view){
    //创建一个意图
    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
    startActivity(intent);
}
```

- Activity运行时屏幕方向与显示方式
  - 通过`android:screenOrientation`设置屏幕方向：横屏 / 竖屏

  `portrait` : 时强制为竖屏
  `landscape`: 时强制为横屏

  ```xml
  <activity android:name=".EX01"
    android:label="@string/app_name"
    android:screenOrientation="portrait"> 
  </activity>
  ```

  - 通过代码实现（一般放在onCreate方法中的前面)

  ```java
  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATTION_LANDSCAPE); 
  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATTION_PORTRAIT);
  ```

- 全屏显示

onCreate()方法中添加:

```java
//设置全屏模式
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//去除标题栏
requestWindowFeature(Window.FEATURE_NO_TITLE);
```

- 以对话框形式显示Activity

```xml
<activity 
    android:name="com.example.SecondActivity"
    android:label="SecondActivity"
    android:theme="@android:style/Theme.DeviceDefault.Dialog">            
</activity>
```

- Activity现场保存

_程序运行时，设备的配置可能会改变，如：横竖屏的切换、键盘的可用性等。此时，Activity会重新创建_    
创建过程如下：
  - 
    - 在销毁之前，会调用`onSaveInstanceState()`去保存应用中的数据到系统中
    - 调用`onDestroy()`销毁之前的Activity
    - 调用`onCreate()`或`onRestoreInstanceState()`方法去重新创建一个Activity

  现场保存的步骤如下:    

    (1) 在MainActivity中，调用onSaveInstanceState()，即添加如下代码就可以将临时数据保存：    

```java
@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    String tempData = "Something you want to save";
    outState.putString("data_key", tempData);
}
```

    (2) 修改MainActivity的onCreate()方法,判断当前状态是否有保存数据  

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //步骤2：还原当前activity的状态
    if (savedInstanceState != null) {
        String tempData = savedInstanceState.getString("data_key");
    }
}
```

- 通过Shared Preferences保存数据

通常情况下会发生这样的问题，我们在编辑短信的同时有电话打进来，那么接电话肯定是要启动另一个Activiy，那么当前编辑短信的Activity所编辑的信息我们想暂时保存下来，等接完电话后回到该Activity时，可以继续编辑短信。该功能需要如何去实现呢？

其实，SharedPreferences使用xml格式为Android应用提供一种永久的数据存贮方式。对于一个Android应用，它存贮在文件系统的/data/ data/your_app_package_name/shared_prefs/目录下，可以被处在同一个应用中的所有Activity 访问。Android 提提供了相关的API来处理这些数据而不需要程序员直接操作这些文件或者考虑数据同步的问题

**实际上SharedPreferences处理的就是一个key-value（键值对）,常用来存储一些轻量级的数据**

  - 第一步：首先使用SharedPreferences这个工具类,创建共享偏好（不知道翻译的对不对？？）
  ```java
  private EditText etMsg ;
  private Button sendButton;
  private SharedPreferences sp;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      
      etMsg = (EditText)findViewById(R.id.editText1);
      sendButton = (Button)findViewById(R.id.button1);
      
      // 获取共享属性操作的工具（文件名，操作模式）
      sp = This.getSharedPreferences("data", 0); // 存储路径 /data/data/Your_App_Package_Name/shared_prefs/data.xml
  }
  ```

  - 第二步：获取要保存的数据

`public SharedPreferences getSharedPreferences (String name, int mode)`其中，    
第一个参数代表XML文件，如果有这个文件，就会操作这个文件，如果没有这个文件，就会创建这个文件   
第二个参数代表一种操作模式，0代表私有。   
然后，我们要在onPause()方法里保存数据，之所以在onPause()方法里保存，是因为在所有可能会被内存销毁的生命周期函数中，而onPause()方法最先执行

  ```java
  //在onPause()方法中保存数据
  @Override
  protected void onPause() {
      super.onPause();
      String msg = etMsg.getText().toString();
      Editor editor = sp.edit();    // 获取editor组件
      editor.putString("msg", msg); // 执行方法：public abstract SharedPreferences.Editor putString (String key, String value) ，设置当前要保存的值
      editor.commit();              // 提交    
  }
  ```

将数据保存在msg变量中，然后拿到Editor这个编辑器，给它put进去。当然，这些只是在内存中操作，如果要反映到文件当中，还要执行 `commit()`方法

  - 第三步：在onResume()方法中还原数据 

  ```java
  @Override
  protected void onResume() {
      super.onResume();
      etMsg.setText(sp.getString("msg", ""));                 // 设置编辑框值 
      etMsg.setSelection((sp.getString("msg", "")).length()); // 设置光标位置
      Editor editor = sp.edit();
      editor.clear();   // 清空编辑器
      editor.commit();      
  }
  ```

完整代码示例请移步到参考文档,尊重原文...

## 参考文档

[`Android组件系列----Activity组件详解`](http://www.cnblogs.com/smyhvae/p/3924567.html)
[`Android中Activity四种启动模式和taskAffinity属性详解`](http://blog.csdn.net/zhangjg_blog/article/details/10923643)