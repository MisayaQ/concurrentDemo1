package com.misaya.javalock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-04 10:25
 **/
public class LockDemo2 {
    public void fun1() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": 执行fun1方法");
            fun2();
        } finally {
            lock.unlock();
        }
    }

    public void fun2() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": 执行fun2方法");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        new Thread(() -> lockDemo.fun1()).start();
    }
}
