package com.misaya.state;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-09 18:49
 **/
//测试stop
    //1.建议线程正常停止---->利用次数 不建议死循环
    //2.建议使用标志位------> 设置一个标志位
    //3.不要使用stop或者destroy等过时或者JDK不建议使用的方法
public class TestStop implements Runnable {
    //1. 设置一个标志位
    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run....Thread"+i++);

        }
    }

    //1.设置一个线程停止的方法
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main"+i);
            if (i == 900) {
                //调用stop方法切换标志位，让线程停止
                testStop.stop();
                System.out.println("线程该停止了");
            }
        }

    }
}
