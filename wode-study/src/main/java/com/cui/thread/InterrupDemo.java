package com.cui.thread;

/**
 * @Descripttion 如何优雅的终止一个线程
 * @Author cuihongmin
 * @Date 2022/8/2 15:14
 */
public class InterrupDemo {
    static int i;
// throws InterruptedException
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            //isInterrupted()默认是false,如果当前线程调用了interrupt()之后，isInterrupted()变成true
            //说白了，就是判断有没有调用线程中断方法
            while(! Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("while之外的输出:"+i);
        },"InterrupDemo");
        t.start();
        Thread.sleep(100);  // 让当前的流程睡眠多少毫秒
        t.interrupt();
    }
}
