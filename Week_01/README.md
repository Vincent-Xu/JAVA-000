# JVM
## 常用启动参数
1. 打印日志（JDK8）：-Xloggc:gc.demo.log（日志保存路径） -XX:+PrintGCDetails（打印详细信息） -XX:+PrintGCDateStamps（打印时间戳）
2. 默认GC线程数：8核以下默认是核心数，8核以上是5/8+3。-XX:ParallelGCThreads=n