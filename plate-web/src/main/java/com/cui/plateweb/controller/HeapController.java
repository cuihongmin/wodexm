package com.cui.plateweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descripttion 模拟堆异常
 * 设置参数：-Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap.hprof
 * 用jmap分析
 * -Xms6000M    //堆初始值内存
 * -Xmx6000M    //堆的最大内存
 * -Xmn500M     //新生代内存
 * -Xss100		//栈的深度
 * 发生堆内存溢出的时候，能自动dump出该文件
 *
 * 最伟大的错误：(java.lang.OutOfMemoryError)
 * @Author cuihongmin
 * @Date 2022/8/6 9:11
 */

@RestController
public class HeapController {
    List<Object> list = new ArrayList<Object>();
    @GetMapping("/heap")
    public String heap() {
        while (true) {
            list.add(new Object());
        }
    }
}
