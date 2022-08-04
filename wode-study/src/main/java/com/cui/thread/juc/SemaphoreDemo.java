package com.cui.thread.juc;

import java.util.concurrent.Semaphore;

/**
 * @Descripttion 信号量 Semaphore 方式一
 * @Author cuihongmin
 * @Date 2022/8/3 15:55
 */
public class SemaphoreDemo {
    Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        for (int i = 0; i <10 ; i++) {
            new Thread(()-> semaphoreDemo.A()).start();
        }
    }

    public void A() {
        try {
            semaphore.acquire(); //获得一个令牌, 如果拿不到令牌，就会阻塞
            System.out.println("第" + Thread.currentThread().getName() + " 抢占一个车位");
            Thread.sleep(2000);
            System.out.println("第" + Thread.currentThread().getName() + " 开走喽");
            semaphore.release();// 释放令牌
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
