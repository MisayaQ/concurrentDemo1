package com.misaya.demo01;

/**
 * @program: concurrentDemo1
 * @description: 多个线程同时操作同一个对象
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-07-31 10:58
 **/

/**
 *  多个线程操作同一个资源 不安全
 */
public class TestThread4 implements Runnable {
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
        TestThread4 ticket = new TestThread4();
        new Thread(ticket,"老师").start();
        new Thread(ticket,"小明").start();
        new Thread(ticket,"小雯").start();
    }
}
