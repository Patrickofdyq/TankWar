package com.itheima.game;

import com.itheima.game.inter.Config;

/**
 * @Author Duyuquan
 * @date 2018/8/1
 */

public class App13 {
    public static void main(String[] args) {
        GameWindow gw = new GameWindow(Config.TITLE,Config.WEIGHT,Config.HEIGHT,Config.FPS);
        gw.start();
    }
}
