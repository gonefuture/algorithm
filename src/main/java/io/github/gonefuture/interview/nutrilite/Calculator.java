package io.github.gonefuture.interview.nutrilite;


import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * 计算器
 * 写一个计算器类（Calculator），可以实现两个数的加、减、乘、除运算，并可以进行undo和redo操作，侯选人可在实现功能的基础上发挥最优设计，编写后请提供github地址
 * <p/>
 * 这个题主要难点应该是怎么去实现undo和redo操作。undo可以将最近一次运算进行操作。redo可以通undo记录去重新进行一遍操作。
 *
 * 如果这两个数可以是小数，那么使用{@link BigDecimal} 来实现。
 *
 * @author Qian Weijian
 * @version 2023-3-30 15:53
 */
public class Calculator extends AbstractCalculator{
    /**
     * 记录历史操作的列表
     */
    private final ArrayList<Operation> history;


    /**
     *  记录回退的历史
     */
    private final ArrayList<Operation> undoHistory;

    /**
     * 当前结果
     */
    private int current;

    public Calculator() {
        history = new ArrayList<>();
        undoHistory = new ArrayList<>();
        current = 0;
    }


    @Override
    public CalculatorType getType() {
        return CalculatorType.IntegerCalculator;
    }

    /**
     *  加法
     */
    public void add(int num) {
        history.add(new Operation('+', num));
        current += num;
    }


    /**
     *  减法
     */
    public void subtract(int num) {
        history.add(new Operation('-', num));
        current -= num;
    }

    /**
     *  乘法
     */
    public void multiply(int num) {
        history.add(new Operation('*', num));
        current *= num;
    }

    /**
     *  除法
     */
    public void divide(int num) {
        if (num == 0) {
            throw new RuntimeException("不可以除以0");
        }
        history.add(new Operation('/', num));
        current /= num;
    }


    /**
     *  回退
     */
    public void undo() {
        if (history.size() > 0) {
            // 从操作历史中获取最新加入的一个操作，然后进行反操作
            Operation lastOp = history.remove(history.size() - 1);
            undoHistory.add(lastOp);
            switch (lastOp.getOperator()) {
                case '+':
                    current -= lastOp.getNumber();
                    break;
                case '-':
                    current += lastOp.getNumber();
                    break;
                case '*':
                    current /= lastOp.getNumber();
                    break;
                case '/':
                    current *= lastOp.getNumber();
                    break;
                default:
                    throw new RuntimeException("暂不支持该操作");
            }
        }
    }


    /**
     *  重做
     */
    public void redo() {
        if (undoHistory.size() > 0) {
            // 从回退中历史获取最新加入的一个操作，然后进行恢复操作
            Operation lastOp = undoHistory.get(undoHistory.size() - 1);
            history.add(lastOp);
            switch (lastOp.getOperator()) {
                case '+':
                    current += lastOp.getNumber();
                    break;
                case '-':
                    current -= lastOp.getNumber();
                    break;
                case '*':
                    current *= lastOp.getNumber();
                    break;
                case '/':
                    current /= lastOp.getNumber();
                    break;
                default:
                    throw new RuntimeException("暂不支持该操作");
            }
        }
    }


    /**
     * 获取当前的值
     */
    public int getCurrent() {
        return current;
    }


    /**
     *  算法操作，包含了操作符和数字
     */
    private  class Operation {
        /**
         *  操作符
         */
        private final char operator;

        /**
         *  数字
         */
        private final int number;

        public Operation(char operator, int number) {
            this.operator = operator;
            this.number = number;
        }

        public char getOperator() {
            return operator;
        }

        public int getNumber() {
            return number;
        }
    }
}