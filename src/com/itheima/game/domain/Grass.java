package com.itheima.game.domain;

import org.itheima.game.DrawUtils;

import java.io.IOException;

/**
 * @Author Duyuquan
 * @date 2018/8/1
 */

public class Grass extends Element {
    private String srcPath = "D:\\IdeaProjects5\\TankeWarPeng\\TankeLianXi1\\src\\res\\img\\grass.gif";
    @Override
    public void draw() {
        try {
            DrawUtils.draw(srcPath,x,y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Grass(int x,int y) {
        super(x,y);
        try {
            int[] size = DrawUtils.getSize(srcPath);
            this.weight = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getLevel() {
        return 1;
    }
}
