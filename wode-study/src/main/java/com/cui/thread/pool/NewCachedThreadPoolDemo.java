package com.cui.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Descripttion 第三种线程池：newCachedThreadPool
 *
 * 不限制容量的线程池，特点是可以根据需要来创建新的线程执行任务，没有特定的corePool
 * @Author cuihongmin
 * @Date 2022/8/4 9:43
 */
// 无限大小线程池 jvm自动回收
public class NewCachedThreadPoolDemo {
    public static void main(String[] args) {
        // 创建newCachedThreadPool线程池
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i <10 ; i++) {
            es.execute(()->{
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在运行");
            });
        }
        es.shutdown();

    }
}
