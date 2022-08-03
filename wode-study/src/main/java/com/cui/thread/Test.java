package com.cui.thread;

/**
 * @Descripttion 多线程环境重排序问题
 * @Author cuihongmin
 * @Date 2022/8/3 11:02
 */
public class Test {

        int a = 0;
        boolean flag = false;
        //写操作
        public void write(){
            a = 1;   // 1
            flag = true;   //2
        }
        //读操作
        public void reader(){
            if(flag){ // 3
                int i = a+1;  //4
            }
        }
        //假设write方法和reader在不同线程环境下运行，可能write会被其它线程优先执行，由于指令重排序会把2放在1前面，
        // 那么2会优于1先执行，此时另外一个线程执行reader方法，由于flag=ture,但是a=0,int i的结果不是我们期望的结果
}
