package com.cui.thread;

import java.io.IOException;

/**
 * @Descripttion 方式二：利用实现runnable接口实现多线程
 * @Author cuihongmin
 * @Date 2022/8/2 10:45
 */

/**
* Thread和Runnable区别？
 * 1、java是单继承，如果一个类已经继承了父类，那么无法继承Thread,所以需要实现Runnable接口
 * 2、Thread实现了Runnable接口
 * 3、实现Runnable资源可以共享
*  */
public class MyRunnable implements Runnable{
    String name = "";
    @Override
    // 注void不带返回值 如果需要返回值就不能是void
    public void run () {
        System.out.println("开始");
        name = Thread.currentThread().getName(); // 多个同时可以共享name属性（操作name属性）
        System.out.println(Thread.currentThread().getName()+ "-->" + name);
    }
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
//        thread.setName("我的线程2");
        thread.start();
        thread1.start();
        thread2.start();



    }
}
