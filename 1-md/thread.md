# 线程知识
## 线程优先级
> 在java中，通过setPriority()方法来设置线程优先级。默认是5，线程优先级高的线程会分配更多是时间片。
  * 对于阻塞的线程(休眠/IO操作的)要分配更高的优先级,保证处理器不会被**CPU密集型的**的线程独占。
  * why? 长时间阻塞操作，CPU会进行线程切换。???
  * 但是程序不能依靠该优先级
## 线程类型
   >**Daemon线程(守护线程)**\
       一般用作后台支持型的工作，JVM中不存在非Daemon线程时，JVM将退出。\
       setDaemon()方法来设置线程的类型
        
   >**工作线程(用户线程)**
       在主线程结束，工作线程并不会退出，JVM也不会退出。
   
### **区别**
  * JVM的结束依据是：所有的work Thread都结束了，就会退出，同事杀死所有的Daemon线程。
   * Daemon守护线程就像一个工作线程的守护者，被守护者不存在，当然其就失去了存在的意义

## 方法

* init 方法
  ```
   /**
       * Initializes a Thread.
       *
       * @param g the Thread group
       * @param target the object whose run() method gets called
       * @param name the name of the new Thread
       * @param stackSize the desired stack size for the new thread, or
       *        zero to indicate that this parameter is to be ignored.
       * @param acc the AccessControlContext to inherit, or
       *            AccessController.getContext() if null
       * @param inheritThreadLocals if {@code true}, inherit initial values for
       *            inheritable thread-locals from the constructing thread
       */
      private void init(ThreadGroup g, Runnable target, String name,
                        long stackSize, AccessControlContext acc,
                        boolean inheritThreadLocals) {
          if (name == null) {
              throw new NullPointerException("name cannot be null");
          }
           //线程名称
          this.name = name;
            
          Thread parent = currentThread();
          SecurityManager security = System.getSecurityManager();
          if (g == null) {
              /* Determine if it's an applet or not */
  
              /* If there is a security manager, ask the security manager
                 what to do. */
              if (security != null) {
                  g = security.getThreadGroup();
              }
  
              /* If the security doesn't have a strong opinion of the matter
                 use the parent thread group. */
              if (g == null) {
                  g = parent.getThreadGroup();
              }
          }
  
          /* checkAccess regardless of whether or not threadgroup is
             explicitly passed in. */
          g.checkAccess();
  
          /*
           * Do we have the required permissions?
           */
          if (security != null) {
              if (isCCLOverridden(getClass())) {
                  security.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
              }
          }
  
          g.addUnstarted();
  
          this.group = g;
          //集成父线程(创建该线程的线程)线程类型
          this.daemon = parent.isDaemon();
          this.priority = parent.getPriority();
          if (security == null || isCCLOverridden(parent.getClass()))
              this.contextClassLoader = parent.getContextClassLoader();
          else
              this.contextClassLoader = parent.contextClassLoader;
          this.inheritedAccessControlContext =
                  acc != null ? acc : AccessController.getContext();
          this.target = target;
          setPriority(priority);
          //继承父线程的ThreadLocal
          if (inheritThreadLocals && parent.inheritableThreadLocals != null)
              this.inheritableThreadLocals =
                  ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
          /* Stash the specified stack size in case the VM cares */
          this.stackSize = stackSize;
  
          /* Set thread ID */
          tid = nextThreadID();
      }
  
  
  ```

* interrupt

    >interrupt()阻断，标志位（成员方法）
    
    >private native boolean isInterrupted(boolean ClearInterrupted)\
     返回当前是否被阻断，ClearInterrupted true标识清除，false标识不清楚该阻断状态\
     **true**的话，会重置该阻断状态，返回重置之前的状态
    
    >Thread.isInterrupted()  静态方法 isInterrupted(true) 重置状态，不能重复消费
    
    >isInterrupted() 成员方法 isInterrupted(false) 保持当前状态，可重复消费

* suspend()、resume()和stop()
    >暂停、恢复、停止
    不建议使用了\
    suspend 不会释放资源，锁不释放的话，进入休眠状态，很可能会造成死锁\
    resume 当暂停造成了死锁，那么恢复是没有希望的\
    stop 在停止时不会保证线程资源的正常释放，通常是没有给与线程释放资源的机会。可能导致程序工作在不确定的状态下。
## 安全的停止线程
   > 保证线程有时间去做资源释放
   * 使用volatile修饰的flag字段去做
   * 使用intercept去做
## 线程间通信

### Object的方法
   * getClass()
   * hashcode
   * equals 
   * toString
   * clone  浅拷贝
   * notify 唤醒一个等待该monitor的线程，去获取该monitor锁
   * notifyAll  唤醒等待该monitor的锁池中的所有线程，去竞争该monitor锁
   * wait 线程等待，释放锁
   * finalize 对象被回收之前会调用该方法，但是方法的执行是一个后天Daemon线程去不定时触发的，所以调用实际并不能符合预期值
> 超类的这几个方法，包含了一些线程间通讯的方法，这就说明，线程间通讯是很常见的一种操作。

### **wait notify** 

>**注意：锁池(monitor锁等)和等待池(调用了wait的线程，会进入等待池，将不会参与竞争)**

>**等待的线程重新获得monitor锁后，会继续逻辑顺序执行**

>一般写法
   >等待方
   ```
      synchronized(target){
           if(条件不满足){
            target.waite();
           }
      }     
   ```
   >通知方
   ```
       synchronized(target){
          //修改条件，使wait满足条件
          target.notifyAll();
       }     
   ```
>消费者一般写法\
消费者和生产者都需要互相通知等待对方

  ```
      synchronized(target){
           if(条件不满足){
            target.waite();
           }
           //多个生产者和消费者的情况下，务必要再判断一下，否则会出现
           if(条件满足){
             //被通知了
             1.操作。。。，
             2.改变状态
             target.notifyAll();
           }
      }     
   ```
>感悟：当wait的线程重新唤醒，其实这个wait/notify的流程就完成了，该线程可以去竞争锁。
![wait和notify的运行过程](md_img\waitAndNotify.png)

  