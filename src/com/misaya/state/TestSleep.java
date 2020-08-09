package com.misaya.state;

import com.misaya.demo01.TestThread4;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-09 19:36
 **/
//模拟网络延时:方法问题的放生性
public class TestSleep implements Runnable {
    //票数
    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"----->拿到了第"+ticketNums--+"票");
        }
    }

    public static void main(String[] args) {
        TestSleep ticket = new TestSleep();
        new Thread(ticket,"老师").start();
        new Thread(ticket,"小明").start();
        new Thread(ticket,"小雯").start();
    }
}
