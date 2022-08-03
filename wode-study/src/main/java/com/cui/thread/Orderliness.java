package com.cui.thread;

/**
 * @Descripttion java并发编程三大特性之一：有序性
 * @Author cuihongmin
 * @Date 2022/8/3 10:52
 */
public class Orderliness {
//    程序执行的顺序按照代码的先后顺序执行。
//    一般来说处理器为了提高程序运行效率，可能会对输入代码进行优化，它不保证程序中各个语句的执行先后顺序同代码中的顺序一致，
//    但是它会保证程序最终执行结果和代码顺序执行的结果是一致的。如下：
    int a=2; //1

    int b=3; //2

    int rs=a*b; //3

//    则因为指令重排序，他还可能执行顺序为 1-2-3,2-1-3
//    但是绝对不会是3-1-2/3-2-1这种顺序,因为这打破了依赖关系。
}