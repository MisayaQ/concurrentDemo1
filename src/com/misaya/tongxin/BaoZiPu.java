package com.misaya.tongxin;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-12 13:45
 **/
public class BaoZiPu extends Thread {
    //1. 需要在成员位置创建一个包子变量
    private BaoZi baoZi;

    // 2. 使用带参数构造方法 为这个包子变量赋值
    public BaoZiPu(BaoZi baoZi) {
        this.baoZi = baoZi;
    }

    @Override
    public void run() {
        //定义一个变量
        int count = 0;
        //让包子铺一直生产包子
        while (true) {
            //必须使用同步技术保证两个线程只能有一个在执行
            synchronized (baoZi) {
                //对包子状态进行判断，如果已经做完包子，进入等待
                if (baoZi.flag == true) {
                    try {
                        baoZi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //被唤醒之后执行，包子铺生产包子，交替生产两种包子
                if (count % 2 == 0) {
                    //生产 薄皮三鲜馅包子
                    baoZi.pi = "薄皮";
                    baoZi.xian = "三鲜馅";
                } else {
                    //生产 冰皮牛肉大葱馅包子
                    baoZi.pi = "冰皮";
                    baoZi.xian = "牛肉大葱馅";
                }
                count++;
                System.out.println("包子铺正在生茶" + baoZi.pi + baoZi.xian + "包子");
                try {
                    //生产包子需要3秒钟
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //生产完包子修改状态 通知顾客
                baoZi.flag = true;
                //唤醒顾客
                baoZi.notify();
                System.out.println("包子铺已经生产好了" + baoZi.pi + baoZi.xian + "的包子，吃货可以开始吃了");
            }
        }
    }
}
