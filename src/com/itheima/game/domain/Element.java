package com.itheima.game.domain;

/**
 * @Author Duyuquan
 * @date 2018/8/1
 */

public abstract class Element {
    public abstract void draw();

    protected int x;
    protected int y;
    protected int weight;
    protected int height;

    public Element(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Element() {

    }
    public int getLevel() {
        return 0;
    }
}
