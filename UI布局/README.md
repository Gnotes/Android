# UI 布局

[`UI Layouts`](https://www.tutorialspoint.com/android/android_user_interface_layouts.htm)
[`Android组件-四大布局的属性详解`](http://www.cnblogs.com/smyhvae/p/4372222.html)
[`Android-布局管理 (五大布局控件使用）`](http://blog.csdn.net/cctcc/article/details/9943587)

## 布局实现方式

- Java代码动态布局
- xml布局文件布局

## 布局类型

| 名称 | 描述 |
| ---- | ---- |
| Linear Layout 线性布局 | 所有子组件在一条线上，水平或垂直 | 
| Relative Layout 相对布局 | 所有子组件相对于父组件 |
| Absolute Layout 绝对布局 | 绝对定位子组件 |
| Frame Layout 帧布局 | 帧布局为每个加入其中的组件创建一个空白的区域(称为一帧)，每个子组件占据一帧，这些帧会根据gravity属性执行自动对齐 |

其他布局

| 名称 | 描述 |
| ---- | ---- |
| PercentFrame Layout 百分比布局| 所有子组件按照百分比排列 |
| Table Layout 表格布局| 所有子组件按照行列方式排列 |
| GridLayout 网格布局 | 容器划分为rows × columns个网格，每个网格可以放置一个组件 |
| List View | ListView is a view group that displays a list of scrollable items |
| Grid View | GridView is a ViewGroup that displays items in a two-dimensional, scrollable grid. |
| Coordinator Layout | 粘性布局 |
| Constraint Layout | 约束布局 |

## 布局属性

| 名称 | 描述 |
| ---- | ---- |
| android:id | 组件唯一id |
| android:layout_width | 布局宽度 |
| android:layout_height | 布局高度 |
| android:layout_marginTop | 距上高度 |
| android:layout_marginBottom | 距下高度 |
| android:layout_marginLeft | 距左宽度 |
| android:layout_marginRight | 距右宽度 |
| android:layout_gravity | 相对于父组件的位置 |
| android:layout_weight | 权重设置|
| android:layout_x | x坐标位置 |
| android:layout_y | y坐标位置 |
| android:paddingLeft | 左边填充宽度 |
| android:paddingRight | 右边填充宽度 |
| android:paddingTop | 上边填充高度 |
| android:paddingBottom | 下边填充高度|

### wrap_content & fill_parent

常用属性

- `android:layout_width=wrap_content` 根据内容大小自适应宽度或高度
- `android:layout_width=fill_parent` 填充满父容器宽度或高度

### Gravity attribute 

[`android:layout_gravity 和 android:gravity 的区别`](http://www.cnblogs.com/ghj1976/archive/2011/04/26/2029535.html)

Gravity attribute plays important role in positioning the view object and it can take one or more (separated by '|') of the following constant values
多个值`|` 分隔

| Value | Description |
| ---- | ---- |
| top | Put the object at the top of its container, not changing its size.  将对象放在其容器的顶部，不改变其大小. |
| bottom | Put the object at the bottom of its container, not changing its size. 将对象放在其容器的底部，不改变其大小. |
| left | Put the object at the left edge of its container, not changing its size. 将对象放在其容器的左侧，不改变其大小. |
| right | Put the object at the right edge of its container, not changing its size. 将对象放在其容器的右侧，不改变其大小. |
| center_vertical | Place object in the vertical center of its container, not changing its size. 将对象纵向居中，不改变其大小. 垂直对齐方式：垂直方向上居中对齐。 |
| fill_vertical | Grow the vertical size of the object if needed so it completely fills its container. 必要的时候增加对象的纵向大小，以完全充满其容器. 垂直方向填充  |
| center_horizontal | Place object in the horizontal center of its container, not changing its size. 将对象横向居中，不改变其大小. 水平对齐方式：水平方向上居中对齐  |
| fill_horizontal | Grow the horizontal size of the object if needed so it completely fills its container. 必要的时候增加对象的横向大小，以完全充满其容器.  水平方向填充 |
| center  | Place the object in the center of its container in both the vertical and horizontal axis, not changing its size. 将对象横纵居中，不改变其大小. |
| fill  | Grow the horizontal and vertical size of the object if needed so it completely fills its container. This is the default. 必要的时候增加对象的横纵向大小，以完全充满其容器. |
| clip_vertical | Additional option that can be set to have the top and/or bottom edges of the child clipped to its container's bounds. The clip is based on the vertical gravity: a top gravity clips the bottom edge, a bottom gravity clips the top edge, and neither clips both edges.  附加选项，用于按照容器的边来剪切对象的顶部和/或底部的内容. 剪切基于其纵向对齐设置：顶部对齐时，剪切底部；底部对齐时剪切顶部；除此之外剪切顶部和底部.  
垂直方向裁剪 |
| clip_horizontal  | Additional option that can be set to have the left and/or right edges of the child clipped to its container's bounds. The clip is based on the horizontal gravity: a left gravity clips the right edge, a right gravity clips the left edge, and neither clips both edges. 附加选项，用于按照容器的边来剪切对象的左侧和/或右侧的内容. 剪切基于其横向对齐设置：左侧对齐时，剪切右侧；右侧对齐时剪切左侧；除此之外剪切左侧和右侧. 水平方向裁剪 |

## 尺寸单位

[`Android 尺寸单位转换和屏幕适配相关`](http://www.cnblogs.com/mengdd/archive/2013/11/16/3426305.html)

- `dp` `dip`  (Density-independent Pixels) 单位密度像素
- `sp` ( Scale-independent Pixels) 缩放尺寸
- `pt` ( Points which is 1/72 of an inch)
- `px`( Pixels) 像素
- `mm` ( Millimeters) 毫米
- `in` (inches) 英寸

## View Identification

```xml
android:id="@+id/my_button"
```

通过`@` 和 `+` 及 `?`

> @ 和 ？都是用来引用其他已经定义好的资源的，@引用的是之前定义好的资源，存在于当前项目或者android的framework里。  
> 而？则是引用的当前加载的`样式文件`里。意思就是说你在xml里某行定义了一个资源，在下面某行需要引用这个资源时用`?`即可  

- `@` : 标明这个属性，xml解析时会自动转化为对应资源
- `+` : 标明这个属性是`新增`的并需要`添加到资源文件`中
- `?` : 标明这个属性，是引用之前已定义好的样式属性

## Coordinator Layout

[`Coordinator Layout`](https://www.aswifter.com/2015/11/12/mastering-coordinator/)
[`使用CoordinatorLayout打造各种炫酷的效果`](https://www.jianshu.com/p/f09723b7e887/)

## Constraint Layout

- [解析ConstraintLayout的性能优势](https://mp.weixin.qq.com/s/gGR2itbY7hh9fo61SxaMQQ)
- [ConstraintLayout 完全解析](https://blog.csdn.net/lmj623565791/article/details/78011599?utm_source=tuicool&utm_medium=referral)