package com.misaya.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @program: concurrentDemo1
 * @description: 实现多线程同步下载图片
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-07-31 10:02
 **/
public class TestThread2 extends Thread {
    private String url; //网络图片地址
    private String name; //保存的文件名

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    //下载图片线程的执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downLoader(url, name);
        System.out.println("下载的文件名为"+name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://upload-images.jianshu.io/upload_images/9070317-667ffcc6ed135166.JPEG?imageMogr2/auto-orient/strip|imageView2/2", "1.jpg");
        TestThread2 t2 = new TestThread2("https://upload-images.jianshu.io/upload_images/7190121-750d12d141de3e5c.JPEG?imageMogr2/auto-orient/strip|imageView2/2/w/640", "2.jpg");
        TestThread2 t3 = new TestThread2("https://upload-images.jianshu.io/upload_images/9070317-667ffcc6ed135166.JPEG?imageMogr2/auto-orient/strip|imageView2/2", "3.jpg");

        t1.start();
        t2.start();
        t3.start();
    }
}

class WebDownloader {
    //下载方法
    public void downLoader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常 downLoader方法出现了问题");
        }
    }
}
