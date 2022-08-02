package com.cui.thread;

import java.util.Date;

/**
 * @Descripttion 让当前的流程睡眠多少毫秒
 * @Author cuihongmin
 * @Date 2022/8/2 17:17
 */
public class ThreadSleepDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始:"+new Date());

        Thread.sleep(2000);// 2 秒

        System.out.println("结束:"+new Date());

    }
}
