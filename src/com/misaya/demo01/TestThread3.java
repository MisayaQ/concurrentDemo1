package com.misaya.demo01;

/**
 * @program: concurrentDemo1
 * @description: 创建线程方式2 实现runnable接口
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-07-31 10:41
 **/
public class TestThread3 implements Runnable {

    @Override
    public void run() {
        //run方法线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码===" + i);

        }
    }

    public static void main(String[] args) {
        //创建Runnable接口的实现类对象
        TestThread3 testThread3 = new TestThread3();

        //创建线程对象 通过线程对象开启线程
        Thread thread = new Thread(testThread3);
        thread.start();

        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习多线程===" + i);

        }
    }
}
