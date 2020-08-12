package com.misaya.tongxin;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-12 13:52
 **/
public class Customer extends Thread {
    //1. 需要在成员位置创建一个包子变量
    private BaoZi baoZi;

    // 2. 使用带参数构造方法 为这个包子变量赋值
    public Customer(BaoZi baoZi) {
        this.baoZi = baoZi;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (baoZi) {
                if (baoZi.flag == false) {
                    try {
                        baoZi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //被唤醒之后执行的代码
                System.out.println("顾客正在吃："+ baoZi.pi + baoZi.xian + "的包子...");
                try {
                    //顾客吃包子需要3秒
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //顾客吃完包子修改包子的状态 改为false
                baoZi.flag = false;
                //吃货唤醒包子铺线程，生产包子
                baoZi.notify();
                System.out.println("吃货已经把"+ baoZi.pi + baoZi.xian + "的包子吃完了，包子铺开始生产包子");
                System.out.println("------------------------------");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
