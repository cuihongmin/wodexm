package com.cui.algorithm;

/**
 * @Descripttion 几个实际编程中遇到的问题
 * @Author cuihongmin
 * @Date 2022/8/20 9:26
 */
public class start {
    public static void main(String[] args) {
        String str = "java,Java,hello,world!";
        // 区分大小写
        String newStr = str.replaceAll("Java","崔宏民~");// 算法
        System.out.println("newStr="+newStr);
    }
}
// 小结：需要使用到单链表数据结构

