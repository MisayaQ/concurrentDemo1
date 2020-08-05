package com.misaya.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-04 17:04
 **/
public class ABADemo {
    public static AtomicReference<String> atomicReference = new AtomicReference<>("A");
    public static void main(String[] args){
        new Thread(() -> {
            atomicReference.compareAndSet("A","B");
            atomicReference.compareAndSet("B","A");
        },"t2").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet("A","C")
                    + "\t" + atomicReference.get());
        },"t1").start();
    }
}
