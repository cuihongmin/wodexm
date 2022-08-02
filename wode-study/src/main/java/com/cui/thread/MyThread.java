package com.cui.thread;

import java.io.IOException;

/**
 * @Descripttion 继承的方式实现多线程
 * @Author cuihongmin
 * @Date 2022/8/2 9:55
 */
public class MyThread extends Thread{

    public MyThread() {
        System.out.println("------" + "构造函数开始" + "------");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().getId) = " + Thread.currentThread().getId());
        System.out.println("Thread.currentThread().isDaemon() = " + Thread.currentThread().isDaemon());
        System.out.println("Thread.currentThread().isInterrupted()= " + Thread.currentThread().isInterrupted());
        System.out.println("Thread.currentThread().getState() = " + Thread.currentThread().getState());
        System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
        System.out.println("------" + "构造函数结束" + "------");
    }

    @Override
    public void run () {
        /**Thread.currentThread()可以获取当前线程的引用，一般都是在没有线程对象又需要获得线程信息时通过
        Thread.currentThread()获取当前代码段所在线程的引用。*/
        System.out.println("------" + "run()开始" + "------");
        System.out.println(Thread.currentThread().getName()); // 获取该线程名称
        System.out.println(Thread.currentThread().getId());   // 获取该线程的标识符
        System.out.println(Thread.currentThread().getState()); // 获取线程状态
        System.out.println(Thread.currentThread().isAlive());  // 测试线程是否属于活动状态
        System.out.println("Thread.currentThread().isDaemon()=" + Thread.currentThread().isDaemon()); // 测试线程是否为守护线程
        System.out.println(Thread.currentThread().isInterrupted()); // 测试线程是否已经中断
        System.out.println("------" + "run()结束" + "------");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("我的线程1");
        myThread.setDaemon(true);
        myThread.start();
        try {
            System.in.read();  // 接受输入，使程序在此停顿，一旦接收到用户输入，main线程结束，守护线程自动结束
        }catch (IOException exception) {}

    }
}

