package com.cui.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Descripttion 第二种线程池：newSingleThreadExecutor
 *  创建一个线程的线程池，特点是使用单个工作线程执行任务
 * @Author cuihongmin
 * @Date 2022/8/4 9:34
 */
public class NewSingleThreadExecutorDemo {

    //线程池只有1个线程执行任务
    public static void main(String[] args) {
        // 创建newSingleThreadExecutor()线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        for (int i = 0; i <10 ; i++) {
            es.execute(()->{
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在执行。。。");
            });
        }
        es.shutdown();
    }
}
