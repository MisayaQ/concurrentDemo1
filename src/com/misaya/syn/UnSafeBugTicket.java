package com.misaya.syn;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-10 22:18
 **/
//不安全的买票
//线程不安全 有负数
public class UnSafeBugTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "ljq").start();
        new Thread(station, "zyw").start();
        new Thread(station, "jiaqi").start();
    }

}

class BuyTicket implements Runnable {

    //票数
    private int ticketNums = 10;
    private boolean flag = true;//外部停止方式

    @Override
    public void run() {
        //买票
        while (flag) {
            buy();
        }
    }
    //synchronized 同步方法，锁的是this
    private synchronized void buy() {
        //判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //买票
        System.out.println(Thread.currentThread().getName() + "拿到第" + ticketNums-- + "张票");
    }
}
