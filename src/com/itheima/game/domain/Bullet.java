package com.itheima.game.domain;

import com.itheima.game.inter.*;
import org.itheima.game.CollsionUtils;
import org.itheima.game.DrawUtils;

import java.io.IOException;

/**
 * @Author Duyuquan
 * @date 2018/8/1
 */

public class Bullet extends Element implements Attackable,Destory,Hitable {
    private String srcPath1 = "D:\\IdeaProjects5\\TankeWarPeng\\TankeLianXi1\\src\\res\\img\\bullet_u.gif";
    private String srcPath2 = "D:\\IdeaProjects5\\TankeWarPeng\\TankeLianXi1\\src\\res\\img\\bullet_d.gif";
    private String srcPath3 = "D:\\IdeaProjects5\\TankeWarPeng\\TankeLianXi1\\src\\res\\img\\bullet_l.gif";
    private String srcPath4 = "D:\\IdeaProjects5\\TankeWarPeng\\TankeLianXi1\\src\\res\\img\\bullet_r.gif";
    private Direction direction;
    private MyTank mt;
    private int blood = 1;
    private int speed = 8;

    @Override
    public void draw() {
        String srcPath = "";
        switch (this.direction) {
            case UP:
                srcPath = srcPath1;
                break;
            case DOWN:
                srcPath = srcPath2;
                break;
            case LEFT:
                srcPath = srcPath3;
                break;
            case RIGHT:
                srcPath = srcPath4;
                break;
        }
        try {
            DrawUtils.draw(srcPath,x,y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bullet(MyTank mt) {
        int mtx = mt.x;
        int mty = mt.y;
        int mtw = mt.weight;
        int mth = mt.height;
        this.direction = mt.direction;
        switch (this.direction) {
            case UP:
                try {
                    int[] size = DrawUtils.getSize(srcPath1);
                    this.weight = size[0];
                    this.height = size[1];
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.x=mtx+(mtw/2-this.weight/2);
                this.y=mty-this.height;
                break;
            case DOWN:
                try {
                    int[] size = DrawUtils.getSize(srcPath2);
                    this.weight = size[0];
                    this.height = size[1];
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.x=mtx+(mtw/2-this.weight/2);
                this.y=mty+mth;
                break;
            case LEFT:
                try {
                    int[] size = DrawUtils.getSize(srcPath3);
                    this.weight = size[0];
                    this.height = size[1];
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.x=mtx-this.weight;
                this.y=mty+(mth/2-this.height/2);
                break;
            case RIGHT:
                try {
                    int[] size = DrawUtils.getSize(srcPath4);
                    this.weight = size[0];
                    this.height = size[1];
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.x=mtx+this.weight;
                this.y=mty+(mth/2-this.height/2);
                break;
        }
    }
    public void move() {
        switch (this.direction) {
            case UP:
                this.y-=speed;
            case DOWN:
                this.y+=speed;
            case LEFT:
                this.x-=speed;
            case RIGHT:
                this.y+=speed;
        }
    }
    public boolean isDestory() {
        if(x<0||y<0||x> Config.WEIGHT||y>Config.HEIGHT||blood<1) {
            return true;
        }else {
            return false;
        }
    }
    public boolean checkAttack(Hitable hitable) {
        Element e = (Element)hitable;
        int x1 = this.x;
        int y1 = this.y;
        int w1 = this.weight;
        int h1 = this.height;

        int x2 = e.x;
        int y2 = e.y;
        int w2 = e.weight;
        int h2 = e.height;

        return CollsionUtils.isCollsionWithRect(x1,y1,w1,h1,x2,y2,w2,h2);
    }

    @Override
    public Blast showBlast() {
        blood--;
        return new Blast(x,y,weight,height,blood);
    }
}
