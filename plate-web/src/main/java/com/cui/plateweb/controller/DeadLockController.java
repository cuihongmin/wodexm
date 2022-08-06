package com.cui.plateweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Descripttion 模拟死锁案例
 *
 * 用jstacki命令分析
 * @Author cuihongmin
 * @Date 2022/8/6 9:37
 */
@RestController
public class DeadLockController {
    @GetMapping("/getLock")
    public void getLock(){
        Object o1 = new Object();
        Object o2 = new Object();
        // 第一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o1){//第一个线程获得o1锁对象成功
                    System.out.println("thread 1 get o1");
                    try {
                        Thread.sleep(100);
                        synchronized (o2){//第一个线程获取o2锁对象的时候发现o2锁处于了Monitor状态，那么需要等待o2锁释放吧
                            System.out.println("thread1 get o2");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
        // 第二个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o2){//第二个线程获得o2锁对象成功
                    System.out.println("thread 2 get o2");
                    try {
                        Thread.sleep(100);
                        synchronized (o1){//第二个线程获取o1锁对象的时候发现o1锁处于了Monitor状态，那么需要等待o1锁释放吧
                            System.out.println("thread2 get o1");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
