package com.bankqueue;

/**
 * 系统中有三种类型的客户，所以用定义一个枚举类，其中定义三个成员分别表示三种类型的客户。
 * 重写toString方法，返回类型的中文名称。这是在后面编码时重构出来的，刚开始不用考虑。
 * @author Administrator
 * @create 2018/5/3
 * @since 1.0.0
 */
public enum CustomerType {              //三个成员
    COMMON,EXPRESS,VIP;

    public String toString(){
        switch (this){
            case COMMON:
                return "普通";
            case EXPRESS:
                return "快速";
            case VIP:
                return name();
        }
        return null;
    }

}