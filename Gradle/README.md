# Gradle

**Gradle** _是一个基于Apache Ant和Apache Maven概念的项目自动化建构工具。它使用一种基于Groovy的特定领域语言来声明项目设置，而不是传统的XML_ -- [Wiki Gradle](https://zh.wikipedia.org/wiki/Gradle)    

简单来说：Gradle就是一个编译脚本（Build System），使用了更简洁的方式书写编译配置。

## 目录
- [`Reference`](#reference)

### build.gradle

> 可以看到项目中有两个`build.gradle`文件

- 外层的`build.granle`

```gradle
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

> 可以看到两处`repositories`都声明了`jcenter()`,`jcenter()`是一个开源代码托管仓库，类似`maven / npm`  
> 接着在`dependences`中使用`classpath`声明了一个`Gradle插件`，由于Gradle并不是专门为Android开发设计的，其他的语言也可以使用它来开发，因此，需要使用`com.android.tools.build:gradle:3.0.0`来声明，并指定版本

- 内层的`build.gradle`

```gradle
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.example.xing.hellowrold"
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
    implementation 'com.android.support.constraint:constraint-layout:1.1.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}
```

> 第一行应用了一个插件,其值有两种方式，`com.android.application`表示这是一个应用程序模块，`com.android.library`表示这是一个库模块
> defaultConfig 指定项目基本配置  
> buildTypes 指定了生成安装文件的相关配置,一般会有两个闭包，一个`debug`，一个`release`，其中`debug`不是必须的，`minifyEnable`指定是否进行代码混淆，`proguardFiles`指定具体的混淆规则，这里指定了两个，一个是`proguard-android.text`是Android SDK目录下通用的规则，一个`proguard-rules.pro`是当前项目根目录下的，Android Studio 自动生成的都是测试版安装文件  
> dependences 指定依赖，第一行`implementation fileTree` 老版本的使用`compile`标示） 是本地依赖声明，表示将`libs`目录下的`*.jar`文件都添加到项目构建中；第二行的`implementation`表示对远程依赖的声明`com.android.support:appcompat-v7:26.1.0`  
> 对于库的依赖我们这里没有用到，基本格式是`implementation project(:project_name)`,如implementation project(:JsonParse)  
> testCompile 是用于声明测试用例库  

其他版本信息参考 [API-Level](../API-Level)

## Reference
[`Gradle Plugin User Guide `](http://tools.android.com/tech-docs/new-build-system/user-guide)   
[`用Gradle 构建你的android程序`](http://www.cnblogs.com/youxilua/archive/2013/05/20/3087935.html)  
[`Gradle 教程`](http://ask.android-studio.org/?/explore/category-gradle)  
[`Gradle学习系列`](http://www.cnblogs.com/davenkin/p/gradle-learning-1.html)  
[`Gradle for Android`](https://segmentfault.com/a/1190000004229002) 
