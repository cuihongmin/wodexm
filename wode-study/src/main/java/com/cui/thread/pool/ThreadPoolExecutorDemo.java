package com.cui.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Descripttion   上面提到的四种线程池的构建，都是基于 ThreadpoolExecutor 来构建的,构造函数参数介绍
 * @Author cuihongmin
 * @Date 2022/8/4 13:52
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,// 核心线程数
                3, // 最大线程数
                30,  // 空闲线程存活时间
                TimeUnit.SECONDS, // 时间单位
                new ArrayBlockingQueue<>(3), // 任务队列（数组有界队列）
                new ThreadPoolExecutor.AbortPolicy() //队列满了,执行的拒绝策略 采用的拒绝策略是什么
        );
        //这边总共7个任务，2个线程执行任务，345进入队列，第六个线程启动最大线程数来执行，第七个任务触发拒绝策略
        for (int i = 0; i <= 6 ; i++) {
            executor.execute(new MyTask(i,"任务:" + i));
        }
        executor.shutdown();
    }
}
