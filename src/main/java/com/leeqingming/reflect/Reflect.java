package com.leeqingming.reflect;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 框架类
 */
public class Reflect {
    public static void main(String[] args) throws Exception{
        //可以创建任意类的对象，并且执行任意方法
        //借助配置文件
        //运用反射技术
        Properties properties = new Properties();
        ClassLoader classLoader = Reflect.class.getClassLoader();
        properties.load(classLoader.getResourceAsStream("pro.properties"));
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        Class<?> cls = Class.forName(className);
        Object obj = cls.newInstance();
        Method method = cls.getDeclaredMethod(methodName, String.class);
        method.invoke(obj, "apple");
    }
}
