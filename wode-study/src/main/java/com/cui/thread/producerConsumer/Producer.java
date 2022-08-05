package com.cui.thread.producerConsumer;

/**
 * @Descripttion 生产者
 * @Author cuihongmin
 * @Date 2022/8/4 15:04
 */
public class Producer implements Runnable{
    private String name; // 生产者的名称
    private String pname; // 生产的产品
    private Storage storage;  // 仓库

    public Producer(String name, String pname, Storage storage) {
        this.name = name;
        this.pname = pname;
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <5 ; i++) {
                Product product = new Product(name, pname);
                storage.push(product);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
