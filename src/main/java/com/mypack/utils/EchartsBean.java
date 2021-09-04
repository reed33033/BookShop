package com.mypack.utils;

/**
 * 图表转换实体类
 */
public class EchartsBean {
    String name;
    int value;

    public EchartsBean() {
    }

    public EchartsBean(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EchartsBean{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
