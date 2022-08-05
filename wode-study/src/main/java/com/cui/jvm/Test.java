package com.cui.jvm;

/**
 * @Descripttion (方法句柄 ） 现代化反射
 *
 * 实际上：是用来执行其他类的方法,如果这个方法句柄所对应的类还没初始化，则需要先触发类初始化。
 * @Author cuihongmin
 * @Date 2022/8/4 16:25
 */
public class Test {
    public String add(String str) {
        System.out.println("执行了add方法");
        return null;
    }
}
