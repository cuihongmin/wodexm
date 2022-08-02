package com.cui.thread;

import java.util.concurrent.*;

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
        MyCallable myCallable = new MyCallable();
        // 线程池后面会详细的说明，先知道有这么一个东西即可；
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(myCallable);
        System.out.println(future.get());
    }
}
