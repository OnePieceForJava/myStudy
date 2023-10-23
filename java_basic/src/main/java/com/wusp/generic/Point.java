package com.wusp.generic;

public class Point <T>{
    private T x; //坐标x
    private T y; //坐标y

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }
}
