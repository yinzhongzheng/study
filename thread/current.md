= Java并发和多线程基础总结

== 基础**volatile**和**synchronized**

=== volatile

==== 功能

. 保证多线程的**可见性**
. 

禁止一部分的**重排序**。 

==== 原理

[source]
----
instance = new Singleton(); // instance是volatile变量
0x01a3de1d: movb $0×0,0×1104800(%esi);0x01a3de24: **lock** addl $0×0,(%esp);
----

_lock_前缀的指令在多核处理器下会触发两种操作
. 将当前处理器缓存行的数据写回到系统内存。
. 这个写回内存的操作会使在其他CPU里缓存了该内存地址的数据无效。

_依赖CPU的缓存一致性的支持_

== synchronized

. 原子性。
. 

由于线程同步，某一时刻，只能一个线程操作共享变量，也就保证了多线程间的**可见性**

=== 使用

* 普通同步方法:锁是this锁
* 代码块:锁是该对象锁
* 

静态同步方法:锁是当前Class对象锁

=== 原理

* 都是使用Monitor(监视器)对象来操作的
monitorenter在编译时插入到synchronized的开始位置,monitorexit在编译时插入在synchronized的结束位置和者异常结束的位置。
* 任何对象都是锁对象，那么任何对象都与一个monitor相关联，当获取到monitor，该monitor就会处于锁定状态。当指令执行到monitorenter时，就会去尝试获取该monitor的所有权。

== 原子性

* 通过锁来实现一些列操作的原子性。但是性能差，会造成阻塞，增加性能消耗。
* 通过CAS(compare and swap)来实现，此实现针对于赋值操作。i++，
* 通过E(期望值,也就是缓存/本地内存/工作内存的副本),V(当前值)来操作。通过E和主内存的值进行比较，如果相等的话，就会将新值赋值给当前值。
* 问题，会造成ABA的问题，当其他线程将共享变量的值变换为B，然后再该为A，那么该线程去将本地内存的值刷到主内存的时候，将会执行成功。但是结果可能是错的。
* 通过CAS+version的方式来解决ABA的问题。
* 也就是，共享变量V的值为 1A-&gt;2B-&gt;3A 那么通过此版本号去控制，就不会巧妙的去除ABA问题。
* 注意，CAS只能处处理一个变量，如果多个可以使用ij = 1a的方式，AtomicReference保证引用对象的原子性。
* 

CAS 不需要添加锁，但是循环验证会增加CPU的消耗。

=== 核心代码

CAS实现操作,CASXXXX方法就是CAS操作。Unsafe.class 操作c代码
`javascript
    do{
        N = E+1;
    }while(CASXXXX((E,N));
`

=== java concurrent包下的atomic类

AtomicInteger AtomicBoolean…（CAS,会出现BAB的问题）
AtomicStampedReference 解决了CAS的ABA的问题

== Java内存模型