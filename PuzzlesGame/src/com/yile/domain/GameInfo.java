package com.yile.domain;

import java.io.Serializable;

//此javabean类用于存储游戏的主要信息
//注意：此类需实现序列化标记接口
public class GameInfo implements Serializable {
    //存储游戏数据的二维数组
    private int[][] arr;
    //空白方块的位置
    int x;
    int y;
    //游戏图片的路径
    String path;
    //游戏步数
    int step;

    public GameInfo() {
    }

    public GameInfo(int[][] arr, int x, int y, String path, int step) {
        this.arr = arr;
        this.x = x;
        this.y = y;
        this.path = path;
        this.step = step;
    }

    /**
     * 获取
     * @return arr
     */
    public int[][] getArr() {
        return arr;
    }

    /**
     * 设置
     * @param arr
     */
    public void setArr(int[][] arr) {
        this.arr = arr;
    }

    /**
     * 获取
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * 设置
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 获取
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * 设置
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 获取
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取
     * @return step
     */
    public int getStep() {
        return step;
    }

    /**
     * 设置
     * @param step
     */
    public void setStep(int step) {
        this.step = step;
    }

    public String toString() {
        return "GameInfo{arr = " + arr + ", x = " + x + ", y = " + y + ", path = " + path + ", step = " + step + "}";
    }
}
