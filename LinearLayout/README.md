# LinearLayout

> 线性布局，我们通过 `android:orientation` 数据来指定线性布局的方向 `vertical | horizontal`，默认的排列方向是 `horizontal`(水平方向)

## 常用属性

- `android:gravity` : 指定文本的对齐方式
- `android:layout_gravity` : 指定控件在布局的对齐方式
  > 当orientation为`vertical`时只有`horizontal`(水平)方向的属性值起作用，为`horizontal`时只有垂直方向的属性才有作用
- `layout_weight` : 布局权重，只用比例的方式来布局，当使用了此属性时，宽度或高度值不应该由`layout_width | layout_height`来决定
