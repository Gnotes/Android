# TextView 文本控件

> 用于显示文本字符串信息

## 常用属性

**所有组件都有 `id` `layout_width` `layout_height`三个属性**，其他信息参考 [UI布局](../UI布局) ，更多详细信息参考 [Android 开发者官网](https://developer.android.com/index.html)

| 名称 | 描述 | 可选值 | 备注 |
|------|------|------|------|------|
| id | 唯一标示 | - | - |
| layout_width | 宽度 | `match_parent` `fill_parent` `wrap_content` | match_parent 和 fill_parent 意思一样，官方推荐 `match_parent` |
| layout_height | 高度 | `match_parent` `wrap_content` | - |
| gravity | 高度 | `top` `bottom` `left` `right` `center` | `center`等同于 `center_vertical|center_horizontal`,多个值使用 竖线`|`分隔 |
| textSize | 字号 | - | 单位：`sp` |
| textColor | 颜色 | - | - |
| text | 显示文本 | - | - |
