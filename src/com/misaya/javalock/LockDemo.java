package com.misaya.javalock;

import java.util.Locale;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-04 10:15
 **/
public class LockDemo {
    public synchronized void fun1() {
        System.out.println(Thread.currentThread().getId()+"执行fun1方法");
        fun2();
    }

    private synchronized void fun2() {
        System.out.println(Thread.currentThread().getId()+"执行fun2方法");
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        new Thread(() -> lockDemo.fun1()).start();
    }
}
