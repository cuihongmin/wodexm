package com.cui.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Descripttion 方式三:实现Callable接口实现多线程
 * @Author cuihongmin
 * @Date 2022/8/2 11:39
 */

/**
* Callable跟其它两种线程的区别？
 * 说白了：Callable有返回值
 * */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "返回成功";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final MyCallable myCallable = new MyCallable();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        //AtomicInteger用来计数
        final AtomicInteger number = new AtomicInteger();
        // 线程池后面会详细的说明，先知道有这么一个东西即可；
        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        Future<String> future = executorService.submit(myCallable);
//        System.out.println(future.get());
        for (int i = 0; i < 6; i++) {
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {

                    System.out.println("运行第" + number.incrementAndGet() + "个线程，当前时间【" + format.format(new Date()) + "】");
                    try {
                       Thread.sleep(6000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "自写成功";
                }
            });
        }
    }
}
