package com.cui.jvm;

/**
 * @Descripttion 典型事例：演示栈的深度，递归调用
 *
 * 请求的栈深度大于虚拟机所允许的深度，抛出StackOverflowError
 * @Author cuihongmin
 * @Date 2022/8/5 11:04
 */
public class StackSOF {
    private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        // 递归
        stackLeak();
    }

    public static void main(String[] args) {
        StackSOF stackSOF = new StackSOF();
        try {
            stackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println("当前栈的最大深度为:" + stackSOF.stackLength);
            e.printStackTrace();
        }
    }
    //当前栈深度:18355  // 通过-Xss128k来修改栈的深度
    //当前栈的最大深度为:1000
}
