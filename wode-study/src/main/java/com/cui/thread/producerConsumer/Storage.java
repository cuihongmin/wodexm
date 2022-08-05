package com.cui.thread.producerConsumer;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Descripttion 产品的仓库:Storage
 * @Author cuihongmin
 * @Date 2022/8/4 14:48
 */
public class Storage {
    // 阻塞队列（用来存储产品）
    private BlockingDeque<Product> storage = new LinkedBlockingDeque<>();
    // 用于生成产品编号
    private int index = 1;

    /**
    * 消费
     * */
    public void pop() throws InterruptedException {
        Product product = storage.take(); // 从队列中取存储的产品
        System.out.println(product.getFrom() + "的" + product.getName() + "-->id:" + product.getId() + "被消费了");
    }

    /**
    * 生产 */
    public void push(Product product) throws InterruptedException {
        storage.put(product);
        product.setId(index++);
        System.out.println(product.getFrom() + "生产了" + product.getName() + "-->id:" + product.getId());
    }

}
