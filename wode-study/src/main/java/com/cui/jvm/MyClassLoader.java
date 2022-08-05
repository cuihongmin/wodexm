package com.cui.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Descripttion 自定义加载器案例ClassLoader里面的三个重要方法：
 * loadClass() 方法是加载目标类的入口
 * findClass() 让自定义加载器自己来加载目标类
 * defineClass() 方法将字节码转换成 Class 对象
 * @Author cuihongmin
 * @Date 2022/8/5 9:34
 */
public class MyClassLoader extends ClassLoader{
    // 目的：让类加载器生成一个Class对象，然后通过反射调用对象方法

    protected Class<?> findClass(String name) {
        // 得到类class--->fileName=MyClassLoader.class
        // name: com.cui.jvm.MyClassLoader
        // fileName: MyClassLoader.class
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        // 得到一个输入流
        InputStream stream = getClass().getResourceAsStream(fileName);

        // 将class字节码转化为class对象
        try {
            // 初始化一个字节数组，里面初始化了数组长度等于流的大小
            byte[] b = new byte[stream.available()];  // stream.available() 可以读取数据流里面有多少字节
            // 给字节数组赋值
            stream.read(b);
            //将class字节转为class对象
            return  defineClass(name, b, 0, b.length);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void wodeShow() {
        System.out.println("我已被你调用。。。");
    }
}
