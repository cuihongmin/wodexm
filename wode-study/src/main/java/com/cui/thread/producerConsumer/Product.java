package com.cui.thread.producerConsumer;

/**
 * @Descripttion 产品类
 * @Author cuihongmin
 * @Date 2022/8/4 14:25
 */
public class Product {

    private String from;  // 来自那个生产者
    private String name;
    private int id = 0;

    public Product(String from,String name,int id) {
        this.from = from;
        this.name = name;
        this.id = id;
    }

    public Product(String from, String name) {
        this.from = from;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
