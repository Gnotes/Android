# HelloWorld代码分析

> 初始化最基本的HelloWorld项目，并分析其代码运行逻辑，及基本配置

## AndroidManifest.xml

> Android项目的清单文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.xing.helloworld">
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <!-- 对MainActivity这个活动进行注册，没有注册的活动是不能使用的 -->
        <activity
            android:name=".MainActivity"
            android:label="@string/app_name"
            android:theme="@style/AppTheme.NoActionBar">
            <intent-filter>
                <!-- 下边这两行代码很重要，表示了这个活动是项目的主活动，点击手机的应用，启动的就是这个页面 -->
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

```java
// AppCompatActivity是一种向下兼容的活动，可以将activity在各个系统中增加的特性和功能最低兼容到 2.1的系统
// activity是安卓提供的活动基类，AppCompatActivity继承自activity
public class MainActivity extends AppCompatActivity {

    // onCreate：一个活动创建的必要生命周期
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 引入布局文件
        setContentView(R.layout.activity_main);
    }
}

```

## res/values/strings.xml

```xml
<resources>
    <string name="app_name">HelloWorld</string>
</resources>
```

- Java代码中使用 `R.string.app_name`引用
- xml中使用 `@string/app_name`引用

注：`string` 部分是可以替换的，如果是图片则是 `drawable`，应用图标 `impmap`，布局 `layout`

- `android:icon` : 指定应用图标
- `android:label` : 指定应用名称

## build.gradle

> Android Studio 是用过 [Gradle](../Gradle) 编译安卓应用的

可以看到一个有 `2` 个 `build.gradle` 文件，其都对项目构建有着重要的作用。  

- 外层的build.gradle

**这个文件是项目全局的配置**

```js
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.0'
        

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```

两个 `repositories`中都声明了 `google` `jcenter`，那么它到底是什么呢？其实它是代码托管仓库，类似`Maven` `NPM`等。  
`dependencies`中的`classpath`声明了一个Gradle`插件`，为什么要声明这个插件呢，因为Gradle并不是专门为Android开发的，因此当构建Android项目时需要声明此插件，`3.0.0`是指定插件版本。  

- app下的build.gradle

```js
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.example.xing.helloworld"
        minSdkVersion 16
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}
```

- 第一行代码: `apply plugin`表示应用一个插件
 > 一般可选值有两种类型 `com.android.application`表示是一个**应用程序模块**  
 > `com.android.library`表示一个**库**模块  
- android: 安卓项目配置
  - `compileSdkVersion`: 项目编译SDK版本，详细查看 [API Level-几个重要属性说明](../API-Level#几个重要属性说明)
  - applicationId : 项目包名
  - versionCode : 项目版本号
  - versionName : 项目版本名称
  - buildTypes : 指定项目编译`生成安装文件`的配置，通常其中只有两个闭包，一个 `debug`，一个`release`，其中 `debug`是可以忽略不写的
    - minifyEnabled : 指定是否对项目进行代码混淆
    - proguardFiles : 混淆时引用的规则文件，这里引入了2个文件, `proguard-android.txt`是在 `Android SDK`目录下的通用规则，`proguard-rules.pro`是项目根目录下的本地混淆规则
- dependencies : 指定项目所有的依赖关系，Android Studio项目一共有三种依赖关系
  - 本地依赖 : 对本地的jar包或目录依赖，如 `implementation fileTree(dir: 'libs', include: ['*.jar'])`
  - 库依赖 : 对项目添加的库模块添加依赖，如 `compile project(':helper')`
  - 远程依赖 : 对 `jcenter`仓库的依赖，如 `implementation 'com.android.support:appcompat-v7:26.1.0'`， `com.android.support` 是依赖`域名部分`，用于和其他公司的库区分；`appcompat-v7` 是`组名称`，用于同公司的不同库区分；`26.1.0`是依赖库的版本







