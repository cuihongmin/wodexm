package com.cui.thread.juc;

import java.util.concurrent.Semaphore;

/**
 * @Descripttion 信号量 Semaphore 方式二
 * @Author cuihongmin
 * @Date 2022/8/3 16:10
 */
public class SemaphoreDemo1 {
    static class Car extends  Thread{
        private int num;
        private Semaphore semaphore;

        public Car(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }
        public void run(){
            try {
                semaphore.acquire(); //获得一个令牌, 如果拿不到令牌，就会阻塞
                System.out.println("第"+num+" 抢占一个车位");
                Thread.sleep(2000);
                System.out.println("第"+num+" 开走喽");
                semaphore.release();// 释放令牌
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(5);//初始化5张令牌
        for(int i=0;i<10;i++){
            new Car(i,semaphore).start();
        }
    }
}
