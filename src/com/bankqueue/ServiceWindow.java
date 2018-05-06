package com.bankqueue;

import java.util.Random;
import java.util.concurrent.Executors;

/**
 * 定义一个start方法，内部启动一个线程，根据服务窗口的类别分别循环调用三个不同的方法。
 * 定义三个方法分别对三种客户进行服务，为了观察运行效果，应详细打印出其中的细节信息。
 * @author Administrator
 * @create 2018/5/3
 * @since 1.0.0
 */
public class ServiceWindow {
    private CustomerType type = CustomerType.COMMON;
    private  int windowId = 1;          //哪个窗口，第几个窗口

    public void setType(CustomerType type) {
        this.type = type;
    }

    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    public void start(){                            //线程
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                while(true) {            //不停取号
                    switch (type ) {
                        case COMMON:
                            commonService();
                            break;
                        case EXPRESS:
                            expressService();
                            break;
                        case VIP:
                            vipService();
                            break;
                    }

                }
            }
        });
    }
    private void commonService(){
        String windowName = "第" + windowId +"号" + type + "窗口";
        Integer number = NumberMachine.getInstance().getCommonManager().fetchServiceNumber();
        System.out.println(windowName + "正在获取任务");
        if(number != null){
            System.out.println(windowName + "为第" + number + "个"  + " 普通客户服务");
            long beginTime = System.currentTimeMillis();
            int maxRand = Constans.MAX_SERVICE_TIME - Constans.MIN_SERVICE_TIME;
            long serverTime = new Random().nextInt(maxRand) + 1 + Constans.MIN_SERVICE_TIME;        //生成随机值，一千到以往
            try {
                Thread.sleep(serverTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long costTime = System.currentTimeMillis() - beginTime;
            System.out.println(windowName + "为第" + number + "个" + " 普通客户完成服务，耗时" + costTime/1000 + "秒");

        }
        else{
            System.out.println(windowName + "没有取到任务,先休息一秒钟");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void expressService(){
        String windowName = "第" + windowId +"号" + type + "窗口";
        Integer number = NumberMachine.getInstance().getExpressManager().fetchServiceNumber();
        System.out.println(windowName + "正在获取任务");
        if(number != null){
            System.out.println(windowName + "为第" + number + "个" + type + " 客户服务");
            long beginTime = System.currentTimeMillis();
            //int maxRand = Constans.MAX_SERVICE_TIME - Constans.MIN_SERVICE_TIME;
            //long serverTime = new Random().nextInt(maxRand) + 1 + Constans.MIN_SERVICE_TIME;        //生成随机值，一千到以往
            try {
                Thread.sleep(Constans.MIN_SERVICE_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long costTime = System.currentTimeMillis() - beginTime;
            System.out.println(windowName + "为第" + number + "个" + type + " 客户完成服务，耗时" + costTime/1000 + "秒");

        }
        else{
            System.out.println(windowName + "没有取到任务!");
            commonService();
        }
    }

    private void vipService(){
        String windowName = "第" + windowId +"号" + type + "窗口";
        Integer number = NumberMachine.getInstance().getVipManager().fetchServiceNumber();
        System.out.println(windowName + "正在获取任务");
        if(number != null){
            System.out.println(windowName + "为第" + number + "个" + type + " 客户服务");
            long beginTime = System.currentTimeMillis();
            int maxRand = Constans.MAX_SERVICE_TIME - Constans.MIN_SERVICE_TIME;
            long serverTime = new Random().nextInt(maxRand) + 1 + Constans.MIN_SERVICE_TIME;        //生成随机值，一千到以往
            try {
                Thread.sleep(serverTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long costTime = System.currentTimeMillis() - beginTime;
            System.out.println(windowName + "为第" + number + "个" + type + " 客户完成服务，耗时" + costTime/1000 + "秒");

        }
        else{
            System.out.println(windowName + "没有取到任务!");
            commonService();

        }
    }

}