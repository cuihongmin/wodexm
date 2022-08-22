package com.cui.bsxm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Descripttion
 * @Author cuihongmin
 * @Date 2022/8/6 15:27
 */
@RestController
public class UserController {
    @GetMapping
    public String getData() {
        return "这个端口是8081";
    }
}
