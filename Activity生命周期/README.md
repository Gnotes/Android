# Activity生命周期

Android是以`Activity (界面)`方式向用户展示应用，那么这个`Activity`的生命周期如下：

**生命周期包含** `3`个阶段，`7`个方法

<img src="./image/lifecycle.png" width="400">

## 活动状态

> 每个活动在其生命周期总`最多`可能有4中状态.

- 运行状态
> 活动处于栈顶端，这时活动处于运行状态

- 暂停状态
> 活动不处于栈顶端，但`任然可见`,如`对话框`

- 停止状态
> 不处于栈顶端，也完全不可预见，系统任会保持该活动的`状态`和`变量`，但这个时候是`不可靠的`

- 销毁状态
> 从栈中移除

## 7个方法

- `void onCreate(Bundle savedInstanceState)` : 在Activity对象被第一次创建时调用
- `void onStart()` : 当Activity变得可见时调用
- `void onResume()` : 当Activity开始准备和用户交互时调用,Activity对象处于运行状态。
- `void onPuase()` : 当系统即将启动另外一个Activity之前调用,另一个Activity位于上层，但是本Activity还可见
- `void onStop()` : 当前Activity变得不可见时调用
- `void onDestory()` : 当前Activity被销毁之前调用
- `void onRestart()` : 当一个Activity再次启动之前调用，除了这个方法外其他的都是两两相对的

```
Paused状态常用于：另一个Activity为对话框，弹出来之后只占据了屏幕的一小部分；但后面的Activity还是可见的，但是处于paused状态。
注：一个Activity出于paused状态时，系统并不会释放资源。释放资源你的操作要靠开发者来完成。
```

## 3个阶段

- `创建活跃阶段` : `onCreate` -> `onStart` -> `onResume`
- `重新活跃阶段` : `onRestart` -> `onStart` -> `onResume`
- `销毁阶段` : `onPuase` -> `onStop` -> `onDestory`

## 示例代码

```java
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    private static final String TAG = "Android Lifecycle Tag:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
}
```

## 活动被回收了

<img src="./image/gc.jpg" width="500">

## 参考文档

[`Actvity生命周期`](http://www.cnblogs.com/smyhvae/p/3856555.html)

## 视频

[`第四集android生命周期的介绍`](https://pan.baidu.com/play/video#video/path=%2FAndroid%2F%E5%A6%82%E4%BD%95%E6%90%AD%E5%BB%BAandroid%E5%BC%80%E5%8F%91%E7%8E%AF%E5%A2%83%20%EF%BC%88Android%E5%85%A5%E9%97%A8%E4%BB%8B%E7%BB%8D%EF%BC%89%E7%AC%AC%E5%9B%9B%E9%9B%86android%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F%E7%9A%84%E4%BB%8B%E7%BB%8D.avi&t=-1)