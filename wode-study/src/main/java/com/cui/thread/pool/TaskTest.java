package com.cui.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Descripttion 案例
 * @Author cuihongmin
 * @Date 2022/8/4 10:45
 */
public class TaskTest implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
    // 创建一个固定大小的线程池
     static ExecutorService es = Executors.newFixedThreadPool(3);
    public static void main(String[] args) {


        for (int i = 0; i <20 ; i++) {
            es.execute(new TaskTest());
        }
        es.shutdown();
    }

}
