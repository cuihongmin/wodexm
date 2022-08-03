package com.cui.thread.notifyWait;

/**
 * @Descripttion
 * @Author cuihongmin
 * @Date 2022/8/3 11:19
 */
public class NotifyWaitDemo {

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        // 把前对象传给ThreadA并开启ThreadA
        new ThreadA(obj).start();
        // 让当前线程休眠10ms
        Thread.sleep(10);
        // 把前对象传给ThreadB并开启ThreadB
        new ThreadB(obj).start();
    }
}
