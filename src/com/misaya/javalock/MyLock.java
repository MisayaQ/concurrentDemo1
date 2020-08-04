package com.misaya.javalock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-04 10:51
 **/
public class MyLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ":come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + ": come out");
    }

    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        new Thread(() -> {
            myLock.myLock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myLock.myUnlock();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            myLock.myLock();
            myLock.myUnlock();
        }, "B").start();
    }
}
