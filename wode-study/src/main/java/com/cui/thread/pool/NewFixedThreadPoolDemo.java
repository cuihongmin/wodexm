package com.cui.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Descripttion 第一种线程池：newFixedThreadPool
 * @Author cuihongmin
 * @Date 2022/8/4 9:24
 */
public class NewFixedThreadPoolDemo {
    /**
    * newFixedThreadPool(): 该方法返回一个固定数量的线程池，特点就是可以重用固定数量线程的线程池使用
     * */
    //线程池只会有三个线程执行任务
    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        ExecutorService es = Executors.newFixedThreadPool(3);

        for (int i = 0; i <10 ; i++) {
           es.execute(()->{
               try {
                   Thread.sleep(300);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(Thread.currentThread().getName());
           });
        }
        es.shutdown();
    }

}
