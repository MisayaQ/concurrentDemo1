package com.misaya.tongxin;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-12 10:29
 **/
//测试：生产者 消费者模型 ---利用缓冲区解决：管程法
//生产者 消费者 产品 缓冲区
public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Productor(container).start();
        new Consumer(container).start();

    }
}

class Productor extends Thread {
    SynContainer container = new SynContainer();

    public Productor(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        //生产
        for (int i = 1; i < 100; i++) {
            System.out.println("生产-->" + i + "个馒头");
            container.push(new Steamedbun(i));
        }
    }
}

class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    public void run() {
        //消费
        for (int i = 0; i < 100; i++) {
            System.out.println("消费-->" + container.pop().id + "个馒头");
        }
    }

}

//馒头
class Steamedbun {
    int id;

    public Steamedbun(int id) {
        this.id = id;
    }
}

class SynContainer {
    Steamedbun[] buns = new Steamedbun[10]; //存储容器
    int count = 0;//计数器

    //生产馒头
    public synchronized void push(Steamedbun bun) {
        //何时等待生产 容量存储空间
        //不能生产 只能等待
        if (count == buns.length) {
            try {
                this.wait(); //线程阻塞  消费者通知生产解除
            } catch (InterruptedException e) {
            }
        }
        //存在空间 可以生产
        buns[count] = bun;
        count++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //存在数据了，可以通知消费了
        this.notifyAll();
    }

    //消费馒头
    public synchronized Steamedbun pop() {
        //没有数据 只能等待
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //存在数据可以消费
        count--;
        Steamedbun bun = buns[count];
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.notifyAll(); //存在空间了，可以唤醒对方生产了
        return bun;
    }
}