# Log日志

> 安卓中的日志使用的是 `Log (android.util.Log)`，其提供了5个API  

- `Log.v()` : 打印最琐碎的信息，对应verbose级别，是日志级别最低的
- `Log.d()` : 打印调试信息，对应debug级别
- `Log.i()` : 打印比较重要的信息，对应info级别
- `Log.w()` : 打印警告信息，对应warn级别
- `Log.e()` : 打印错误信息，对应error级别

Log.*() 一般会有两个参数，如 `Log.i('TAG','Something important')`,第一个参数对应信息标签，用于信息过滤；第二个参数是要打印的信息

