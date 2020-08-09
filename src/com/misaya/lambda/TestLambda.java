package com.misaya.lambda;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-05 09:34
 **/
public class TestLambda {
    //3.静态内部类
    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println("I Like Lambda2");
        }
    }

    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();
        like = new Like2();
        like.lambda();


        //4.局部内部类
        class Like3 implements ILike {
            @Override
            public void lambda() {
                System.out.println("I Like Lambda3");
            }
        }

        like = new Like3();
        like.lambda();

        //5. 匿名内部类 没有累的名称 必须借助接口或者父类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("I Like Lambda4");
            }
        };
        like.lambda();

        //6. lambda简化
        like = () -> {
            System.out.println("I Like Lambda5");
        };
        like.lambda();
    }
}

//1.定义一个函数式接口
interface ILike {
    void lambda();
}

//2. 实现类
class Like implements ILike {

    @Override
    public void lambda() {
        System.out.println("I Like Lambda");
    }
}
