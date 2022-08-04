package com.cui.thread.pool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Descripttion 第四种线程池：newScheduledThreadPool
 *
 * 创建一个可以指定线程的数量的线程池，特点一个定时功能的线程池。
 * @Author cuihongmin
 * @Date 2022/8/4 9:48
 */
public class NewScheduledThreadPoolDemo {
    public static void main(String[] args) {
        // 创建newScheduledThreadPool任务线程池
        ScheduledExecutorService es = Executors.newScheduledThreadPool(5);
        System.out.println("初始化开始:" + new Date());
        // 第一个参数：执行的线程
        // 第二个参数：初始化延迟时间
        // 第三个参数：第二次以后最小执行任务的间隔时间
        // 第四个参数：时间单位
        es.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "--" + new Date());
            }
        },3,2, TimeUnit.SECONDS);

    }

}
