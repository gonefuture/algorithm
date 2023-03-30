package io.github.gonefuture.interview.nutrilite;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 计算接口
 *
 * @author Qian Weijian
 * @version 2023-3-30 18:01
 */
public abstract class AbstractCalculator {



    /**
     * 计算器的类型，为了方便是实现的策略模式
     * @return 计算器的类型
     */
    abstract CalculatorType getType();

    private static final Map<CalculatorType, AbstractCalculator> calculatorMap = new HashMap<>();


    /**
     *  初始化时，放入处理策略，自己注册自己
     *  在Spring换进可以使用 {@InitializingBean}或者  @PostConstruct来初始化
     */
    {
        calculatorMap.put(getType(), this);

    }


    public static AbstractCalculator findCalculator(CalculatorType type) {
        AbstractCalculator calculator = calculatorMap.get(type);
        if (Objects.isNull(calculator)) {
            throw new RuntimeException("暂不支持该计算器");
        }
        return calculator;
    }




    /**
     * 加法
     * @param num 参与计算的数字
     */
    abstract void add(int num);


    /**
     * 减法
     * @param num 参与计算的数字
     */
    abstract void subtract(int num);

    /***
     * 乘法
     * @param num 参与计算的数字
     */
    abstract void multiply(int num);


    /**
     *  除法
     *  @param num 参与计算的数字
     */
    abstract void divide(int num);


    /**
     * 重做
     * @ num 参与计算的数字
     */
    abstract void redo();

    /**
     * 回退
     * @ num 参与计算的数字
     */
    abstract void undo();





    /**
     * 获取当前的值
     */
    abstract int getCurrent();

}
