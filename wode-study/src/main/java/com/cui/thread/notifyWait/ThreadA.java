package com.cui.thread.notifyWait;

/**
 * @Descripttion
 * @Author cuihongmin
 * @Date 2022/8/3 11:18
 */
public class ThreadA extends Thread{

    private Object obj;

    public ThreadA (Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        // 同步代码块
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + "TheadA开始执行了....");
            try {
                obj.wait(); // 阻塞，等待唤醒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "TheadA结束运行了....");
        }
    }
}
