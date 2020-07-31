package com.misaya.demo01;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-07-31 10:02
 **/
public class TestThread1 extends Thread {
    @Override
    public void run() {
        //run方法线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码===" + i);

        }
    }

    public static void main(String[] args) {
        //main线程 主线程
        TestThread1 testThread1 = new TestThread1();
        //start方法开启线程
        testThread1.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习多线程===" + i);

        }
    }
}
