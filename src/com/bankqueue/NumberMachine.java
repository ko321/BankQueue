package com.bankqueue;

/**
 * 定义三个成员变量分别指向三个NumberManager对象，分别表示普通、快速和VIP客户的号码管理器，定义三个对应的方法来返回这三个NumberManager对象。
 * 将NumberMachine类设计成单例。
 * @author Administrator
 * @create 2018/5/3
 * @since 1.0.0
 */
public class NumberMachine {
    private NumberManager commonManager = new NumberManager();
    private NumberManager expressManager = new NumberManager();
    private NumberManager vipManager = new NumberManager();

    public NumberManager getCommonManager() {
        return commonManager;
    }

    public NumberManager getExpressManager() {
        return expressManager;
    }

    public NumberManager getVipManager() {
        return vipManager;
    }

    /*
    单例
     */
    private NumberMachine(){}

    public static NumberMachine getInstance(){
        return instance;
    }
    private static NumberMachine instance = new NumberMachine();
}