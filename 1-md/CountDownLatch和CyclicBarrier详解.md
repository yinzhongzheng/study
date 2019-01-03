# CountDownLatch和CyclicBarrier
> 在看Curator的时候，看到了分布式屏障的设计，所以回头仔细看看Jdk的CyclicBarrier实现思路。这里CountDownLatch和CyclicBarrier的作用有点类似，就一并分析了。
## CountDownLatch
* 特点
> 是一个原子递减操作，当计数器为0的时候，所有等待的线程将释放，阻塞状态将打开。
> 只能使用一次，count值不能被外界修改。
> 主要是当当前计数器==0的时候，等待的线程将释放阻塞。可以一个异步执行，比如Zookeeper的连接就可以这么干。
> 修改count的值可以是一个线程也可以是多个线程

* 类图
![](/class_picture/CountDownLatch.png)
### 实现逻辑
1. 初始化CountDownLatch，会有count参数传入，该count参数表示计数器的初始值。
2. **await方法**，当调用await方法时，会去检查是否需要等待，CountDownLatch的Sync类重写了tryAcquireShared方法，该方法在count=0的时候返回1，否则返回-1，所以count必须是一个**非负数**。
    1. 当```count>0```的情况下，表示该线程需要等待，进入AQS的等待队列。
    2. 当```count==0```的情况下,那么该线程将会直切返回，不会自旋且入队。如果该线程已经是处于等待队列中的时候，那么，将会出队并退出自旋。
3. **countDown方法**，该方法负责去对count去做--操作，当当前计数器为0的时候，将会直接返回，否则将**自旋+CAS**进行count--。

### 源码分析
> CountDownLatch.Sync
```java
        //这里是await将会调用该方法
        protected int tryAcquireShared(int acquires) {
            //在count==0的时候直接返回1，否则返回-1，返回-1就表示该线程需要进行自旋入队
            return (getState() == 0) ? 1 : -1;
        }
        
        //countDown方法将会调用该方法，该方法一定会有返回结果，是一个类同步方法
        protected boolean tryReleaseShared(int releases) {
            // Decrement count; signal when transition to zero
            for (;;) {
                int c = getState();
                //count==0表示不需要进行--操作，直接返回
                if (c == 0)
                    return false;
                //这里通过CAS来进行赋值，加上自旋操作，来保证该方法一定会产生数据变更
                int nextc = c-1;
                if (compareAndSetState(c, nextc))
                    //当当前count的值==0的时候返回true将进行doReleaseShared操作，否则不做任何操作
                    return nextc == 0;
            }
        }
```
> AbstractQueuedSynchronizer相关源码
```java
//实际做Acquire的方法(当count!=0的时候)
 private void doAcquireShared(int arg) {
        //入队
        final Node node = addWaiter(Node.SHARED);
        boolean failed = true;
        try {
            boolean interrupted = false;
            //自旋操作
            for (;;) {
                //得到上面入队的node的前继
                final Node p = node.predecessor();
                //当p是队首的话，将会再次count的值
                if (p == head) {
                    //再次判断，这里count==0的时候,r=1
                    int r = tryAcquireShared(arg);
                    //当不需要等待的时候，就去释放head
                    if (r >= 0) {
                        //这个时候其实需要进行出队操作了，需要将node设置为head节点
                        setHeadAndPropagate(node, r);
                        //清理出队的前head
                        p.next = null; // help GC
                        if (interrupted)
                            selfInterrupt();
                        failed = false;
                        return;
                    }
                }
                //这里是Condition相关的操作
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }
    
    private void setHeadAndPropagate(Node node, int propagate) {
            Node h = head;
            //将此node设置成head节点
            setHead(node);
            //propagate 就是 tryAcquireShared的值，当count==0为1
            if (propagate > 0 || h == null || h.waitStatus < 0 ||
                (h = head) == null || h.waitStatus < 0) {
                Node s = node.next;
                //如果没有获取节点或者是共享节点
                if (s == null || s.isShared())
                    //释放
                    doReleaseShared();
            }
        }

private void doReleaseShared() {
        for (;;) {
            Node h = head;
            if (h != null && h != tail) {
                int ws = h.waitStatus;
                //独占模式
                if (ws == Node.SIGNAL) {
                    if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                        continue;            // loop to recheck cases
                    unparkSuccessor(h);
                }
                //共享模式(ws==0只有第一次进入该方法有效)
                else if (ws == 0 &&
                         !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                    continue;                // loop on failed CAS
            }
            if (h == head)                   // loop if head changed
                break;
        }
    }
```
# CyclicBarrier
## 实现逻辑
>当所有await的线程数达到初始值时，阻塞的线程将会继续顺序执行。有点像先让线程初一某个屏障，任何线程都不能穿越该屏障，知道线程数达到期望值的时候，那么就会改期屏障，所有线程都会执行。
## 源码
```java
private int dowait(boolean timed, long nanos)
        throws InterruptedException, BrokenBarrierException,
               TimeoutException {
        //排它锁，因为多个线程来修改count的时候会出现线程不安全的问题，count的共享资源
        final ReentrantLock lock = this.lock;
        //进入await方法必须先获取锁
        lock.lock();
        try {
            //来标识，是否被中断
            final Generation g = generation;
            //中断就抛异常
            if (g.broken)
                throw new BrokenBarrierException();
            //这里线程被中断也会抛异常
            if (Thread.interrupted()) {
                breakBarrier();
                throw new InterruptedException();
            }
            //等待线程+1，原始值-1
            int index = --count;
            //当前count==0标识这屏障就要是要，所有线程将会自由飞翔
            if (index == 0) {  // tripped
                boolean ranAction = false;
                try {
                    //这里会从构造方法传入一个callback对象，在屏障点进行回调执行
                    final Runnable command = barrierCommand;
                    if (command != null)
                        command.run();
                    ranAction = true;
                    //唤醒其他线程，并且重置generation
                    nextGeneration();
                    return 0;
                } finally {
                    if (!ranAction)
                        //如果command执行出现异常，那么将会中断此次屏障
                        breakBarrier();
                }
            }
            
            //index!=0的情况，以为着需要等待屏障解除
            // loop until tripped, broken, interrupted, or timed out
            for (;;) {
                try {
                    //是否设置了超时,没有设置，那么该线程等待，释放锁资源，进入等待queue
                    if (!timed)
                        trip.await();
                    //设置了超时的情况 只是加了一个超时判断
                    else if (nanos > 0L)
                        nanos = trip.awaitNanos(nanos);
                } catch (InterruptedException ie) {
                    //此处是线程被中断
                    if (g == generation && ! g.broken) {
                        breakBarrier();
                        throw ie;
                    } else {
                        // We're about to finish waiting even if we had not
                        // been interrupted, so this interrupt is deemed to
                        // "belong" to subsequent execution.
                        Thread.currentThread().interrupt();
                    }
                }
                //中断
                if (g.broken)
                    throw new BrokenBarrierException();
                 //这里做了double check generation也是共享资源
                //其他线程重置了generation，这里直接退出自旋
                if (g != generation)
                    return index;
                //超时了
                if (timed && nanos <= 0L) {
                    breakBarrier();
                    throw new TimeoutException();
                }
            }
        } finally {
            //最后将释放锁,其他在争抢队列中的线程将去获取锁
            //count==0的时候
            lock.unlock();
        }
    }
    
    private void nextGeneration() {
            // signal completion of last generation
            //这里唤醒所有等待的线程
            trip.signalAll();
            // set up next generation
            //count==0 parties=初始值
            count = parties;
            //重置generation字段
            generation = new Generation();
        }
```
## 比较
> CountDownLatch更像是一个计数器，join操作。
> CyclicBarrier像是一个多线程状态一致后的再次执行，现实生活中的比赛。
