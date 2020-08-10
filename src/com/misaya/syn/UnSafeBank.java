package com.misaya.syn;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-10 22:26
 **/

//不安全的取钱
public class UnSafeBank {
    public static void main(String[] args) {
        Account account = new Account(1000, "零花钱");

        Drawing you = new Drawing(account, 50, "你");
        Drawing gf = new Drawing(account, 100, "gf");
        you.start();
        gf.start();
    }
}

//账户
class Account {
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//银行
class Drawing extends Thread {

    //账户
    Account account;

    //取了多少钱
    int drawingMoney;

    //现在手里有多少钱
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱
    //synchronized 默认锁是this
    @Override
    public void run() {
        //锁的对象是变化的两 需要增删改
        synchronized (account) {
            //判断有没有钱
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够了，取不了");
                return;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //卡内余额 = 余额 - 取的钱
            account.money = account.money - drawingMoney;

            //你手里的钱
            nowMoney = nowMoney + drawingMoney;
            System.out.println(account.name + "余额为：" + account.money);

            //Thread.currentThread().getName() = this.getName()
            System.out.println(this.getName() + "手里的钱" + nowMoney);
        }
    }
}