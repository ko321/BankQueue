package com.bankqueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个用于存储上一个客户号码的成员变量和用于存储所有等待服务的客户号码的队列集合
 * 定义一个产生新号码的方法和获取马上要为之服务的号码的方法，这两个方法被不同的线程操作了相同的数据，所以要进行同步
 * @author Administrator
 * @create 2018/5/3
 * @since 1.0.0
 */
public class NumberManager {
    private int lastNumber = 1;             //上一个的的号码
    private List<Integer> queueNumber = new ArrayList<Integer>();           //排队的队列
    public synchronized Integer generateNewManager(){           //客户来了和取号是不同的线程访问相同的数据
        /**
         * 产生号码
         */
        queueNumber.add(lastNumber);
        return lastNumber++;
    }

    public  synchronized Integer fetchServiceNumber(){
        /**
         * 取要服务的号
         */
        Integer number = null;
        if(queueNumber.size() > 0) {
            number = queueNumber.remove(0);
        }
        return number;
    }
}