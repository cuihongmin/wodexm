package com.cui.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Descripttion 编程三大特性之一 ：原子性
 *
 * 一个线程在CPU中操作不可暂定，也不可中断，要不执行完成，要不不执行
 * @Author cuihongmin
 * @Date 2022/8/3 9:27
 */
public class TicketDemo {
    int ticketNum = 10;

//    Lock lock = new ReentrantLock();
    public synchronized String getTicket() {
//        lock.lock();
        if (ticketNum <= 0) {
            System.out.println(Thread.currentThread().getName() + "没有抢到票");
            return "票的数量不足，请等待补票员补票。。。";
        }
        ticketNum--;
        System.out.println(Thread.currentThread().getName() + "抢到一张票，剩余:" + ticketNum);
//        lock.unlock();
        return "抢票成功";

    }

    public static void main(String[] args) {
        TicketDemo demo = new TicketDemo();
        // 创建20个线程
        for (int i = 0; i <20; i++) {
            new Thread(demo::getTicket).start();
        }
        //结论：不是原子操作，怎么保证原子操作呢？
//        JMM对原子性的保证：
//
//        1.synchronized：同步加锁
//
//        2.JUC里面的lock：加锁

    }
}
