package com.cui.jvm;

/**
 * @Descripttion 验证 ClassLoader
 * @Author cuihongmin
 * @Date 2022/8/4 17:20
 */
public class ClassLoaderTest {


    public static void main(String[] args) {
        // 获取当前的classLoader的父类的父类  （获取爷爷） null -- Bootstrap ClassLoader
        System.out.println(new ClassLoaderTest().getClass().getClassLoader().getParent().getParent());
        //null,因为Bootstrap ClassLoader使用C++代码编写，java程序无法获取到classloader对象
        // 获取当前classLoader的父类 （获取父亲）  ExtClassLoader -- Extension ClassLoader
        System.out.println(new ClassLoaderTest().getClass().getClassLoader().getParent());//ExtClassLoader ：扩展类加载器
        // 获取当前的classLoader   AppClassLoader  -- App ClassLoader
        System.out.println(new ClassLoaderTest().getClass().getClassLoader());//AppClassLoader：系统类加载器
    }
}
