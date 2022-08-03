package com.cui.thread.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Descripttion juc-同步工具类
 * @Author cuihongmin
 * @Date 2022/8/3 14:38
 */

// countdownlatch 是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完毕再执行
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        // 举例场景：医院看病：挂号---看病---拿药---回家，
        // 挂号---看病---拿药这三步用线程处理，等着三步流程执行完毕在回家

        CountDownLatch countDownLatch = new CountDownLatch(3); // 设置内部计数次数：3

        new Thread(()-> {
            System.out.println("挂号。。。");
            countDownLatch.countDown(); //3-1=2  如果当前计数大于零，调用一次内部的计数次数减1

        }).start();

        new Thread(()-> {
            System.out.println("看病。。。");
            countDownLatch.countDown();//2-1=1
        }).start();

        new Thread(()-> {
            System.out.println("拿药。。。");
            countDownLatch.countDown();//1-1=0
        }).start();
        countDownLatch.await();// 内部计数器大于0会阻塞，直到内部计数器为0则唤醒

        System.out.println("回家。。。");
    }

    // 结论与对比
     // 1、为加countDownLatch.countDown()和countDownLatch.await()运行结果
     //    回家。。。
     //    挂号。。。
     //    看病。。。
     //    拿药。。。
    //2、加了countDownLatch.countDown()和countDownLatch.await()运行结果
      //    挂号。。。
      //    看病。。。
      //    拿药。。。
      //    回家。。。

}
