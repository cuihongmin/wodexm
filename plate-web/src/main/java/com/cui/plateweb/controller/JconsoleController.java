package com.cui.plateweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descripttion 用jconsole观察程序内存和线程的情况
 * 设置参数： -Xms20M -Xmx20M
 * @Author cuihongmin
 * @Date 2022/8/6 9:54
 */
@RestController
public class JconsoleController {
    class OOMObject{
        public byte[] placeholder = new byte[2*1024];
    }
    @GetMapping("/jConsoleTest")
    public  void jConsoleTest(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(100);
            list.add(new OOMObject());
        }
        System.gc();
        Thread.sleep(5000);
    }
}
