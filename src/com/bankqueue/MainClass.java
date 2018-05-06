package com.bankqueue;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 用for循环创建出4个普通窗口，再创建出1个快速窗口和一个VIP窗口。
 * 接着再创建三个定时器，分别定时去创建新的普通客户号码、新的快速客户号码、新的VIP客户号码。
 * @author Administrator
 * @create 2018/5/3
 * @since 1.0.0
 */
public class MainClass {
    public static void main(String[] args){
        for(int i = 1; i<5; i++){
            ServiceWindow commonWindow = new ServiceWindow();
            commonWindow.setWindowId(i);
            commonWindow.start();
        }

        ServiceWindow expressWindow = new ServiceWindow();
        expressWindow.setType(CustomerType.EXPRESS );
        expressWindow.start();

        ServiceWindow vipWindow = new ServiceWindow();
        vipWindow.setType(CustomerType.VIP );
        vipWindow.start();
        //普通用户
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        Integer number = NumberMachine.getInstance().getCommonManager().generateNewManager();
                        System.out.println(number + "号普通客户等待服务");
                    }},
                0,
                Constans.COMMON_CUSTOMER_INTERVAL_TIME,
                TimeUnit.SECONDS
        ); //定时线程池
        //vip客户
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        Integer number = NumberMachine.getInstance().getVipManager().generateNewManager();
                        System.out.println(number + "号vip客户等待服务");

                    }},
                0,
                Constans.COMMON_CUSTOMER_INTERVAL_TIME *6,
                TimeUnit.SECONDS
        ); //定时线程
        //快速客户
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        Integer number = NumberMachine.getInstance().getExpressManager().generateNewManager();
                        System.out.println(number + "号快速客户等待服务");

                    }},
                0,
                Constans.COMMON_CUSTOMER_INTERVAL_TIME *2,
                TimeUnit.SECONDS
        ); //定时线程
    }

}