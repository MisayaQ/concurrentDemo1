package com.misaya.demo02;

import com.misaya.demo01.TestThread2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @program: concurrentDemo1
 * @description: 线程创建方式3 实现Callable接口
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-02 21:34
 **/
public class TestCallable implements Callable<Boolean> {

    private String url; //网络图片地址
    private String name; //保存的文件名

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downLoader(url, name);
        System.out.println("下载的文件名为" + name);
        return true;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        TestCallable t1 = new TestCallable("https://upload-images.jianshu.io/upload_images/9070317-667ffcc6ed135166.JPEG?imageMogr2/auto-orient/strip|imageView2/2", "1.jpg");
        TestCallable t2 = new TestCallable("https://upload-images.jianshu.io/upload_images/7190121-750d12d141de3e5c.JPEG?imageMogr2/auto-orient/strip|imageView2/2/w/640", "2.jpg");
        TestCallable t3 = new TestCallable("https://upload-images.jianshu.io/upload_images/9070317-667ffcc6ed135166.JPEG?imageMogr2/auto-orient/strip|imageView2/2", "3.jpg");

        //创建执行服务：
        ExecutorService service = Executors.newFixedThreadPool(3);

        //提交执行：
        Future<Boolean> future1 = service.submit(t1);
        Future<Boolean> future2 = service.submit(t2);
        Future<Boolean> future3 = service.submit(t3);

        //获取结果
        Boolean r1 = future1.get();
        Boolean r2 = future2.get();
        Boolean r3 = future3.get();

        //关闭服务
        service.shutdown();
    }
}

//下载器
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

