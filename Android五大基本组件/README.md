# Android五大基本组件

- `Activity` : 用户界面
- `Intent` : 意图
- `Service` : 服务
- `Broadcast Receiver` : 广播接收器
- `Content Provider` : 内容提供者

## [Activity](Activity/)

应用程序中，一个Activity通常就是一个单独的屏幕，它上面可以显示一些控件也可以监听并处理用户的事件做出响应

## [Intent](Intent/)

Intent是一种运行时绑定（runtime binding）机制，它能够在程序运行的过程中连接两个不同的组件。通过Intent，
你的程序可以向Android表到某种请求或者意愿，Android会根据意愿的内容选择适当的组件来处理请求

## [Service](Service/)

一个Service 是一段长生命周期的，没有用户界面的程序，可以用来开发如监控类程序

## [Broadcast Receiver](Broadcast Receiver/)

你的应用可以使用它对外部事件进行过滤只对感兴趣的外部事件(如当电话呼入时，或者数据网络可用时)进行接收并做出响应。广播接收器没有用户界面。然而，它们可以启动一个activity或serice 来响应它们收到的信息，或者用NotificationManager 来通知用户。通知可以用很多种方式来吸引用户的注意力──闪动背灯、震动、播放声音等。一般来说是在状态栏上放一个持久的图标，用户可以打开它并获取消息

## [Content Provider](Content Provider/)

android平台提供了Content Provider使一个应用程序的指定数据集提供给其他应用程序。这些数据可以存储在文件系统中、在一个SQLite数据库、或以任何其他合理的方式,
其他应用可以通过ContentResolver类(见ContentProviderAccessApp例子)从该内容提供者中获取或存入数据.(相当于在应用外包了一层壳),
只有需要在多个应用程序间共享数据是才需要内容提供者

## 参考文档

[`Android四大（五大）基本组件简介`](http://blog.163.com/feng_yun_ju/blog/static/178190393201332822155751/)
[`Android四大基本组件介绍与生命周期`](http://www.cnblogs.com/bravestarrhu/archive/2012/05/02/2479461.html)
[`Android之四大组件、六大布局、五大存储`](http://blog.csdn.net/shenggaofei/article/details/52450668)