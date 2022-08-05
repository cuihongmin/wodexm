package com.cui.thread.producerConsumer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Descripttion 启动类
 * @Author cuihongmin
 * @Date 2022/8/4 15:10
 */
public class Application {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Producer p1 = new Producer("华为", "P40", storage);
        Producer p2 = new Producer("中兴集团", "中兴手机", storage);
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);

        // 创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(p1);
        executorService.submit(p2);
        executorService.submit(c1);
        executorService.submit(c2);

        executorService.shutdown();
    }

}
