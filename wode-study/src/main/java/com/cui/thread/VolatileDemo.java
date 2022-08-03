package com.cui.thread;

/**
 * @Descripttion java并发编程三大特性之一：可见性
 *
 * 多个线程访问同一个变量是，一个线程修改这个变量的值，其它线程能够看到修改之后的值
 * @Author cuihongmin
 * @Date 2022/8/3 10:31
 */
public class VolatileDemo {

    /**
    * JMM对可见性的保证：
     *
     * 加Volatile关键字
     *
     * 对Volatile变量进行写操作的时候，会在写操作之后加一个指令（store）存储,会将数据强制刷新到主内存中
     *
     * 对Volatile变量进行读操作的时候，会在读操作之前加一个指令（load）载入，强制每次都去主内存拉取最新的变量值 */
    private volatile static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        // 启动第一个线程
        new Thread(()->{
            while (!flag) {
//                System.out.println("0000");
            }
            System.out.println("第一个线程执行完毕");
        }).start();

        // 让当前线程休眠100ms
        Thread.sleep(100);

        // 启动第二个线程
        new Thread(()->{
            flag = true; // 思考：第一个线程会不会跳槽while循环，输出System.out.println("第一个线程执行完毕");
            System.out.println("第二个线程执行完毕");
        }).start();
    }
}
