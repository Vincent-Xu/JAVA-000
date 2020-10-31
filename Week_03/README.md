# Nio
## Netty
* 
## 自己动手实现API网关
### 架构设计
1. 设计：技术复杂度和设计复杂度，一般的系统来说，设计更重要，要抽象做好业务封装设计，方便后续继续运维迭代。
2. 抽象：概念理清，正确命名。做好层次划分，规则和关联关系理清。（DSL：每个行业有自己的黑话，构造团队能力打造自己的体系和共同语言）
3. 组合：组件之间相互关系、组合。

# 并发编程01
## 多线程基础
1. 为什么要用多线程，摩尔定律失效->多核+分布式时代.
2. 操作系统最小单位进程，CPU最小单位是线程，进程可以包含多个线程，进程间内存隔离，进程内内存共享。Linux上都是fd（文件），进程=线程，多个进程可以绑定一个端口（Nginx）。

## Java多线程
1. 守护线程：如果将thread设置成守护线程（setDaemon），jvm在发现当前线程没有相关线程会直接结束。
2. 线程状态：start-> runable (等待CPU调度) -> runing (可能wait、interrupt-> Non-Runable) -> Terminated.
3. Object#方法wait释放当前CPU和锁，和notify配合进行线程协同，类似MQ实现生产消费。
4. 线程组（ThreadGroup）
5. System.in.read可以阻塞当前执行。
6. 线程状态：R（Runingable，Ready）W（Waiting，Timed_Waiting）B（Blocked）。

## 线程安全
* 原子性：保证所有的
* 可见性：jvm的问题，方法引用时是副本，不会改变主存的值。用volatile修饰可以同步主存，但不保证原子性。synchronized和lock。
* Synchronized：对象头标记字。
* Volatile：每次读取都强制 适用单个线程写，多个线程读。能不用就不用，替代方案Atomic原子类操作。
