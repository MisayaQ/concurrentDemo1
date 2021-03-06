package com.misaya.lock2;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-04 13:56
 **/
public class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName()+"正在写入："+value);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName()+"写入完成");
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName()+"正在读取");
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取完成"+result);
    }

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final Integer temp = i;
            new Thread(() -> myCache.put(temp+"", temp),temp +"号线程").start();
        }

        for (int i=0; i<5; i++){
            final Integer temp = i;
            new Thread(() -> myCache.get(temp+""),temp +"号线程").start();
        }
    }
}
