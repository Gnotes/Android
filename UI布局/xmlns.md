# xmlns

在编写xml布局文件时常会写一个叫做 `xmlns` 的东西，但这个具体是什么意思呢？  

> 它是 XML 文档中的一个概念：英文叫做 XML namespace，中文翻译为 XML 命名空间。XML namespace也是为了解决 XML 中元素和属性命名冲突。

## 常用的xmlns

- `xmlns:android="http://schemas.android.com/apk/res/android"` 系统自带的默认xmlns
- `xmlns:tools="http://schemas.android.com/tools"` 系统自带的工具类xmlns:布局文件内预览期间展示，运行后会给自动干掉
- `xmlns:app="http://schemas.android.com/apk/res-auto"` 开发人员自定义的所有控件的自定义属性都在app这个命名空间里

## xml声明

XML 命名空间定义语法为`xmlns:namespace-shortcut="namespace-uri"`，一共分为三个部分：  

- `xmlns`：声明命名空间的保留字，其实就是XML中元素的一个属性；
- `namespace-shortcut`：命名空间的简写，这个简写与某个命名空间相关联；
- `namespace-uri`：命名空间的唯一标识符，一般就是一个URI引用

## 参考文档

- [如何理解Android中的xmlns](https://www.jianshu.com/p/6fcaffaeffd2)