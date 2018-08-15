package com.itheima.game.domain;

import com.itheima.game.inter.Blockable;
import org.itheima.game.DrawUtils;

import java.io.IOException;

/**
 * @Author Duyuquan
 * @date 2018/8/1
 */

public class Water extends Element implements Blockable{
    private String srcPath = "D:\\IdeaProjects5\\TankeWarPeng\\TankeLianXi1\\src\\res\\img\\water.gif";
    @Override
    public void draw() {
        try {
            DrawUtils.draw(srcPath,x,y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Water(int x,int y) {
        super(x,y);
        try {
            int[] size = DrawUtils.getSize(srcPath);
            this.weight = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
