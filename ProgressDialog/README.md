# ProgressDialog

> 显示进度条的弹框控件

## 常用属性


## 常用方法

- setTitle : 设置标题
- setMessage : 设置显示内容信息
- setCancelable : 设置是否可取消
  > setCancelable(false) : 参数false时是不能通过Android自带的返回键关闭弹框，当数据加载完成后需要通过`dismiss()`来关闭弹框
- show : 显示对话框
- dismiss : 关闭对话框