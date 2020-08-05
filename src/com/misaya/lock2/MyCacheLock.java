package com.misaya.lock2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-04 13:56
 **/
public class MyCacheLock {

    private volatile Map<String, Object> map = new HashMap<>();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + ": 正在写入： " + value);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "：写入完成");
        lock.writeLock().unlock();
    }

    public void get(String key){
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + ": 正在读取");
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + ": 读取完成： " + result);
        lock.readLock().unlock();
    }

    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();
        for (int i = 0; i < 5; i++) {
            final Integer temp = i;
            new Thread(() -> myCache.put(temp + "", temp), temp + "号线程").start();
        }

        for (int i = 0; i < 5; i++) {
            final Integer temp = i;
            new Thread(() -> myCache.get(temp + ""), temp + "号线程").start();
        }
    }

}
