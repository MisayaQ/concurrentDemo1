package com.misaya.state;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-09 23:17
 **/
//测试join方法
public class TestJoin implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        //主线程
        for (int i = 0; i <100; i++) {
            if (i == 50){
                thread.start();
                thread.join();
            }
            System.out.println("main"+i);
        }
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("vip来了");
        }
    }
}
