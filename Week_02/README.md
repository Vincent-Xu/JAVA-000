# 笔记
1. 程序里面的对象都还有引用，回收为什么会减少？因为本地方法执行完就释放。
2. CMS并发GC线程数默认占CPU1/4的线程
3. 解决死锁的两种方式①限制执行时间，超时 ②强制杀死其中一个
4. xss默认线程内存1M，降低xss可以增加可执行的线程数
5. 数据库连接池设置为50-100比500-1000性能更好
6. 调用方法需要把方法和参数都入栈，所以线程栈深度跟参数一致
7. JVM启动参数有600多个
8. 设置Xmx考虑非堆和堆外用多少
9. GC的暂停

# JVM调优经验
## 分配速率和提升速率
1. 分配速率表示单位时间分配的内存量。
2. 提升速率用于衡量单位时间内从年轻代提升到老年代的数据量。
## 解决分配速率过高和过早提升
1. 可以尝试增加年轻代大小。
2. 优化业务代码，减少批处理数据量大小。

