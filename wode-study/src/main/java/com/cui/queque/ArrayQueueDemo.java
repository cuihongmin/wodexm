package com.cui.queque;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Descripttion 数组模拟队列
 * @Author cuihongmin
 * @Date 2022/8/20 16:17
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        // 测试一把
        // 创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in); // 创建接收器
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0); // 接收一个字符 返回值是char类型
            System.out.println(key);
            switch (key) {
                case 's':
                    // 如果输入的是s则显示队列
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    // 如果输入的是a则添加数据到队列
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt(); //接收一个整形的数
                    arrayQueue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
//                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
//                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");

    }
}

//使用数组模拟队列-编写ArrayQueue类
class ArrayQueue {
    private int maxSize; // 表示数组的最大容量
    private int front; // 队列头
    private int rear;  // 队列尾
    private int[] arr; // 该数据用于存放数据，模拟队列

    // 创建队列的构造器

    public ArrayQueue(int arrMaxSize) {
       maxSize = arrMaxSize;
       arr = new int[maxSize];
       front = -1; // 指向队列头部，分析出front是指向队列的前一个位置。
       rear = -1;  // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
    }
    // 判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }
    // 判断队列是否为空
    public boolean isEmpty () {
        return rear == front;
    }
    // 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列已满,不能加入数据~");
            return;
        }
        rear ++; // 让rear后移
        arr[rear] = n;
    }
    // 获取队列的数据，出队列
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列为空，不能取数据");
        }
        front++; // front后移
        return arr[front];
    }
    // 显示队列的所有数据
    public void showQueue () {
        // 遍历
        if (isEmpty()){
            System.out.println("队列为空，没有数据~~~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }
    // 显示队列的头数据，注意不是取数据
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列为空,没有队列头数据~~~");
        }
        return arr[front+1];
    }
// 验证两数之和
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] indexs = new int[2];
        for(int i = 0; i < nums.length; i++){
//            System.out.println(nums[i] + "sss");
                 for(int j = nums.length - 1; j > i; j --){
//                     System.out.println(nums[j] + "jjjj");
                     if(nums[i]+nums[j] == target){
                        indexs[0] = i;
                        indexs[1] = j;
                         System.out.println(Arrays.toString(indexs));
                     }
                 }
             }
    }
}


