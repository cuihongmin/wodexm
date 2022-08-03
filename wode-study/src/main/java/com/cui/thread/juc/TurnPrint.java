package com.cui.thread.juc;

import java.util.concurrent.Semaphore;

/**
 * @Descripttion 使用Semaphore的方式 实现 a 、b 交替打印
 * @Author cuihongmin
 * @Date 2022/8/3 16:23
 */
public class TurnPrint {
    static class A extends Thread {
        private Semaphore semaphore1;
        private Semaphore semaphore2;

        public A( Semaphore semaphore1,Semaphore semaphore2) {
            this.semaphore1 = semaphore1;
            this.semaphore2 = semaphore2;
        }
        public void run() {
            try {
                while (true) {
                    semaphore1.acquire();
                    System.out.println("a");
                    Thread.sleep(1000);
                    semaphore2.release();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class B extends Thread {
//        private int i;
        private Semaphore semaphore1;
        private Semaphore semaphore2;

        public B( Semaphore semaphore1,Semaphore semaphore2) {
            this.semaphore1 = semaphore1;
            this.semaphore2 = semaphore2;
        }
        public void run() {
            while (true) {
                try {
                    semaphore2.acquire();
                    System.out.println("b");
                    Thread.sleep(1000);
                    semaphore1.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(0);
        new A(semaphore1,semaphore2).start();
        new B(semaphore1,semaphore2).start();

    }
}
