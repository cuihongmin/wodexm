package com.cui.thread.juc;

/**
 * @Descripttion 死锁    方式一
 * @Author cuihongmin
 * @Date 2022/8/3 14:03
 */
public class DeadLockDemo {
    static Object o1 = new Object();
    static Object o2 = new Object();

    // 第一个线程里面需要等待第二个线程所释放，而第二个线程里面需要等待第一个线程锁释放，造成两个线程相互等待，这就是死锁

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            // t1线程开始获取o1锁
            synchronized (o1) {// 第一个线程获得o1锁成功
                System.out.println("t1线程获取o1锁成功");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // t1线程开始获取o2锁
                synchronized (o2) { // 第1个线程t1获取o2锁对象的时候发现o2锁处于monitor状态，那么需要等待o2锁释放
                    System.out.println("t1线程获取o2锁成功");
                }

            }
        });
        t1.start();

        // 创建第二个线程
        Thread t2 = new Thread(()->{
            synchronized (o2) { // 第二个线程获得o2锁成功
                System.out.println("t2线程获取o2锁成功");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // t2线程开始获取o1锁
                synchronized (o1) { // 第二个线程获取o1锁对象的时候发现o1锁处于monitor状态，那么需要等待o1锁释放
                    System.out.println("t2线程获取o1锁成功");
                }
            }
        });
        t2.start();
    }

}
