package com.itheima.game.domain;

import com.itheima.game.inter.Blockable;
import com.itheima.game.inter.Destory;
import com.itheima.game.inter.Hitable;
import org.itheima.game.DrawUtils;

import java.io.IOException;

/**
 * @Author Duyuquan
 * @date 2018/8/1
 */

public class Wall extends Element implements Blockable,Hitable,Destory{
    private String srcPath = "TankeLianXi1\\src\\res\\img\\wall.gif";
    private int blood = 3;
    @Override
    public void draw() {
        try {
            DrawUtils.draw(srcPath,x,y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Wall(int x, int y) {
        super(x, y);
        try {
            int[] size = DrawUtils.getSize(srcPath);
            this.weight = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isDestory() {
        if(blood<0) {
            return true;
        }
        return false;
    }

    @Override
    public Blast showBlast() {
        blood--;
        return new Blast(x,y,weight, height, blood);
    }
}
