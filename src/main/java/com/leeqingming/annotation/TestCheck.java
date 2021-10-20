package com.leeqingming.annotation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 框架类
 * 自动检测加了check注解的方法，并判断方法是否有异常，记录到文件中
 */
public class TestCheck {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Class<?> cls = Class.forName("com.leeqingming.annotation.Calculator");
        Object obj = cls.newInstance();
        Method[] methods = cls.getMethods();
        int count = 0;
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/bug.txt"));
        for (Method method : methods) {
            if (method.isAnnotationPresent(Check.class)) {
                try {
                    method.invoke(obj);
                } catch (Exception e) {
                    count++;
                    writer.write(method.getName() + "方法出异常了");
                    writer.newLine();
                    writer.write("异常的名称:" + e.getCause().getClass().getSimpleName());
                    writer.newLine();
                    writer.write("异常的原因:" + e.getMessage());
                    writer.newLine();
                    writer.write("------------------------");
                    writer.newLine();
                }
            }
        }
        writer.write("本次测试一共出现" + count + "次错误");
        writer.flush();
        writer.close();
    }
}
