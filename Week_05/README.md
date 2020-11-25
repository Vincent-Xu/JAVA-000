# Spring
## Spring AOP
* 线程池事务所怎么解决
    1. 加打断
    2. 加超时
* 代理
    1. interface默认使用jdkproxy，在proxy里面加before和after、round
    2. 使用instrumentation，可以在代码加载前修改，之后使用的都是这个jar，命令是java -agent myagent.jar myproject.jar。
    3.


