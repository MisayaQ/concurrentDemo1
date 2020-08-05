package com.misaya.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-04 17:17
 **/
public class ABADemo2 {
    public static AtomicStampedReference<String> atomicReference = new AtomicStampedReference<>("A", 1);
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);// 睡一秒，让t1线程拿到最初的版本号
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet("A", "B", atomicReference.getStamp(), atomicReference.getStamp() + 1);
            atomicReference.compareAndSet("B", "A", atomicReference.getStamp(), atomicReference.getStamp() + 1);
        }, "t2").start();
        new Thread(() -> {
            int stamp = atomicReference.getStamp();//拿到最开始的版本号
            try {
                TimeUnit.SECONDS.sleep(3);// 睡3秒，让t2线程的ABA操作执行完
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet("A", "C", stamp, stamp + 1));
        }, "t1").start();
    }
}
