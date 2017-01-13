# 搭建开发环境

环境依赖：[`JDk`](http://www.oracle.com/technetwork/java/javase/downloads/index.html) [`Android Studio`](https://developer.android.com/studio/index.html)

参考文档：[Mac上搭建Android开发环境](http://blog.163.com/zhou_411424/blog/static/19736215620130161284750/)

## JDK

Mac上自带`JDK`但是版本比较旧，我安装的`JDK1.8` 通过`java -version`可以查看JDK版本.

### 安装

下载好JDK后直接安装，默认安装目录：`/Library/Java/JavaVirtualMachines/`

### 配置环境变量

`vim ~/.bash_profile`

```shell
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_111.jdk/Contents/Home 
export PATH=$JAVA_HOME/bin:$PATH 
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
```

### 卸载

- 在`finder`中查找`JavaAppletPlugin.plugin` 并删除   
- `sudo rm -rf /Library/PreferencesPanes/JavaControlPanel.prefPane`
- `sudo rm -rf /Library/Java/JavaVirtualMachines/*`

## Android Studio

