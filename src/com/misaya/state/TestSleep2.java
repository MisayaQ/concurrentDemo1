package com.misaya.state;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-09 19:36
 **/
//模拟网络延时:方法问题的放生性
public class TestSleep2 implements Runnable {

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        Date startTime = new Date(System.currentTimeMillis());//获取当前系统时间
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("hh:mm:ss").format(startTime));
                startTime = new Date(System.currentTimeMillis());//更新时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void tenDown() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <= 0) {
                break;
            }
        }
    }
}
