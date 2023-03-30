package io.github.gonefuture.interview.nutrilite;

import java.util.function.Supplier;

/**
 * 计算器客户端
 *
 * @author Qian Weijian
 * @version 2023-3-30 16:10
 */
public class CalculatorClient {


    public static void main(String[] args) {

        // 因为不在Spring容器中，所以这里手动new一下，把自己注册到基类的策略中
        // 同时这里的策略实现类是同一个对象来的，所以会有共享对象状态，后续优化的话可以让用每次生成策略对象的模式
        Calculator c = new Calculator();

        // 通过策略模式获取一个计算器,此处的枚举可以由调用方上下文提供，也可以通配置中心来实时变更
        AbstractCalculator calculator = AbstractCalculator.findCalculator(CalculatorType.IntegerCalculator);
        // 初始值是0，这里直接加10
        calculator.add(10);
        System.out.printf("current value is %d%n",calculator.getCurrent());
        // 减
        calculator.subtract(5);
        System.out.printf("current value is %d%n",calculator.getCurrent());
        // 乘
        calculator.multiply(8);
        System.out.printf("current value is %d%n",calculator.getCurrent());
        // 除
        calculator.divide(2);
        System.out.printf("current value is %d%n",calculator.getCurrent());
        // 回滚两次
        calculator.undo();
        calculator.undo();
        System.out.printf("current value is %d%n",calculator.getCurrent());
        // 重做
        calculator.redo();
        System.out.printf("current value is %d%n",calculator.getCurrent());




    }


    /**
     *  计算并打印
     */
    private static void calculateAndPrintln(Supplier<Calculator> operation) {


    }




}
