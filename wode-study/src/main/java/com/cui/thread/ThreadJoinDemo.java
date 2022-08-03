package com.cui.thread;

/**
 * @Descripttion 等待某一个线程执行结束
 * @Author cuihongmin
 * @Date 2022/8/2 17:18
 */

// join方法是保证线程执行顺序的一种简单的方式
public class ThreadJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1");
        });

        t1.start();
        //需求：让第二线程必须等到第一个线程执行完之后再执行
        t1.join();//阻塞，等到t1线程执行完毕

        Thread t2 = new Thread(()->{
            System.out.println("t2");
        });
        t2.start();
        //期望结果：t1永远都在t2前面打印
    }
}
