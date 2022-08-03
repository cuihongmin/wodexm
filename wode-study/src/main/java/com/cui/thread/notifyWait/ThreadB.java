package com.cui.thread.notifyWait;

/**
 * @Descripttion
 * @Author cuihongmin
 * @Date 2022/8/3 11:18
 */
public class ThreadB extends Thread{
    private Object obj;

    public ThreadB (Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        // 同步代码块
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + "ThreadB开始执行了....");
            obj.notify(); // 唤醒 (是为了让唤醒的这线程个来争抢锁)
//            try {
//                ThreadB.sleep(20000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName() + "ThreadB结束运行了....");
        }
    }
}
