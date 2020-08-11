package com.misaya.lock;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-11 22:39
 **/
//测试Lock锁
public class TestLock {

}

class TestLock2 implements Runnable {
    public static void main(String[] args) {
        TestLock2 lock2 = new TestLock2();
        new Thread(lock2).start();
        new Thread(lock2).start();
        new Thread(lock2).start();

    }

    int ticketNums = 10;

    //定义Lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();//加锁
                if (ticketNums > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNums--);
                } else {
                    break;
                }
            }finally {
                //解锁
                lock.unlock();
            }
        }
    }
}
