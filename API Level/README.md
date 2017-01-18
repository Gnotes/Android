# API Level

**请认真阅读参考文档，相信你会对API Level有更正确的认识**

**每部Android手机都有各自的Android版本，即：系统平台**，而我们开发的应用能否在此系统平台上运行就取决于你选择的`API Level`

由于每个`新版本`的`Android API` 相对于`旧版本`的`Android API`都有不同的`新特性` 和 `新API`开放，所以我们开发Android应用时，需要判断当前选择的API级别是否符合我们的需要，如：     

_我们选择了`API 18（Android 4.3）` ，这个级别开放了一个`新的API (假如叫做：API_18)`，那我们在`小于` `API 18（Android 4.3）` 级别的手机上就`不该`使用此`API_18` ,如果使用了就会出错；而在`大于等于`此级别`(API 18（Android 4.3）)`的手机上就可以正常使用此`API_18`，因为`高版本`的API是`包含`了`低版本`的API，这也是所说的`向前兼容`_

## 目录

- [`什么是API级别`](#什么是api级别)
- [`平台与API级别对照表`](#平台与api级别对照表)
- [`几个重要属性说明`](#几个重要属性说明)
- [`Reference`](#reference)

## 什么是API级别

官方：`API 级别是一个对 Android 平台版本提供的框架 API 修订版进行唯一标识的整数值`，说的尼玛👂不懂。    
个人理解：`API 级别`就是对`Android 平台` `版本`的`唯一` 标示，标示的`值`是一个`正整数`，最小值是1。

## 平台与API级别对照表

| 平台 | API 级别 |
| ------------ | :-------------: |
| Android 7.0 | 24  |
| Android 6.0 | 23  |
| Android 5.1 | 22  |
| Android 5.0 | 21  |
| Android 4.4W | 20 |
| Android 4.4 | 19  |
| Android 4.3 | 18  |
| Android 4.2、4.2.2 | 17  |
| Android 4.1、4.1.1 | 16  |
| Android 4.0.3、4.0.4 | 15|
| Android 4.0、4.0.1、4.0.2 | 14  |
| Android 3.2 |13  |
| Android 3.1.x |12  |
| Android 3.0.x、2.3.4 | 11  |
| Android 2.3.3、2.3.2、2.3.1 | 10  |
| Android 2.3   | 9 |
| Android 2.2.x | 8 |
| Android 2.1.x | 7 |
| Android 2.0.1 | 6 |
| Android 2.0   | 5 |
| Android 1.6   | 4 |
| Android 1.5   | 3 |
| Android 1.1   | 2 |
| Android 1.0   | 1 |

## 几个重要属性说明

- `minSdkVersion` : 指定能够运行应用的最低 API 级别。 默认值为“1”，其值必须`小于或等于` `系统`的 `API 级别`
  这个指你所支持的最低系统平台版本，如果系统API级别（手机Android版本）小于此值，那么我们的开发的应用将拒绝安装到此系统（手机）上，因为我是在高版本中使用的API或特性，在这个手机上根本不支持。
- `targetSdkVersion` : 指定运行应用的目标 API 级别，默认与minSdkVersion相同
  这是指我们期望运行我们应用的系统版本都是这个，如果小于等于此版本，那么你需要在开发是调试好小于此版本的平台，展现出来的效果（表现形式）；如果大于此版本，那么可以开启兼容模式来保证展现效果与此版本保持一致。
- `maxSdkVersion` : 指定能够运行应用的最高 API 级别，其值必须`大于或等于` `系统`的 `API 级别`
  
- `compileSdkVersion` : 告诉 Gradle 用哪个 Android SDK 版本编译你的应用
  这个制定了应用的编译版本，如果编译版本`小于`我们使用的`API`版本，那么我们的应用会编译不通过，因为该编译版本还不存在这个API。如果`大于`选择的API版本，我们可正常编译应用，应为高版本的API包含了低版本的API和特性。一般我们会选择较高版本的编译版本。

  理性状态：`minSdkVersion <= targetSdkVersion <= compileSdkVersion`

## Reference
[`农民伯伯 - Android API Levels`](http://www.cnblogs.com/over140/archive/2011/04/29/2032433.html) 
[`API 级别介绍及使用`](https://developer.android.com/guide/topics/manifest/uses-sdk-element.html#considerations) 
[`Android如何选择targetSDKVersion`](http://www.th7.cn/Program/Android/201606/886177.shtml)
