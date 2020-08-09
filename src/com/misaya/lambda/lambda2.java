package com.misaya.lambda;

/**
 * @program: concurrentDemo1
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2020-08-05 09:49
 **/
public class lambda2 {


    public static void main(String[] args) {

        //lambda表示简化
        ILove love = (int a) -> {
            System.out.println("I love zyw" + a);
        };
        love.love(1);

        //简化1 参数类型
        love = (a) -> {
            System.out.println("I love zyw" + a);
        };
        love.love(2);

        //简化2 简化括号
        love = a -> {
            System.out.println("I love zyw" + a);
        };
        love.love(3);

        //简化3 去掉花括号
        love = a -> System.out.println("I love zyw" + a);
        love.love(4);

        /*
            总结
                lambda表达式只能有一行代码的情况下 才能简化成 如果有多行 那么就用代码块包裹
                接口是函函数式接口（只能有一个方法）
                多个参数也可以去掉参数类型 要去掉就都去掉

         */
    }

}

interface ILove {
    void love(int a);
}

