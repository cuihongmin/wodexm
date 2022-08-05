package com.cui.jvm;

import java.lang.reflect.Method;

/**
 * @Descripttion 自定义加载器案例ClassLoader运行类
 * @Author cuihongmin
 * @Date 2022/8/5 10:14
 */
public class Application {
    public static void main(String[] args) throws Exception {
//        findClass() 让自定义加载器自己来加载目标类
        Class clazz = new MyClassLoader().findClass("com.cui.jvm.MyClassLoader");

        // class实例化
        Object obj = clazz.newInstance();
        Method method = clazz.getMethod("wodeShow");
        // 方法调用
        method.invoke(obj);
    }
//    自定义加载器自己来加载目标类
}
