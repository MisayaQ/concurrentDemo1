package com.misaya.state;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-10 21:57
 **/
public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);//默认是false表示是用户线程 正常的线程都是用户线程
        thread.start(); //守护线程启动

        new Thread(you).start();//用户线程启动
    }

}

class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("god bless you");
        }
    }
}


class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生都开心的活着");
        }
        System.out.println("goodbye world!!");
    }
}