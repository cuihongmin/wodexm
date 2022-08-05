package com.cui.thread.producerConsumer;

/**
 * @Descripttion 消费者
 * @Author cuihongmin
 * @Date 2022/8/4 15:01
 */
public class Consumer implements Runnable{

    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            while (true) {
                storage.pop();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
