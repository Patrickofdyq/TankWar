package com.itheima.game.domain;

import com.itheima.game.inter.*;
import jdk.nashorn.api.tree.ReturnTree;
import org.itheima.game.CollsionUtils;
import org.itheima.game.DrawUtils;
import org.lwjgl.Sys;

import java.io.IOException;

/**
 * @Author Duyuquan
 * @date 2018/8/1
 */

public class MyTank extends Element implements Moveable, Hitable, Destory {
    private String srcPath1 = "D:\\IdeaProjects5\\TankeWarPeng\\TankeLianXi1\\src\\res\\img\\tank_u.gif";
    private String srcPath2 = "D:\\IdeaProjects5\\TankeWarPeng\\TankeLianXi1\\src\\res\\img\\tank_d.gif";
    private String srcPath3 = "D:\\IdeaProjects5\\TankeWarPeng\\TankeLianXi1\\src\\res\\img\\tank_l.gif";
    private String srcPath4 = "D:\\IdeaProjects5\\TankeWarPeng\\TankeLianXi1\\src\\res\\img\\tank_r.gif";

    public Direction direction = Direction.UP;
    private int blood = 2;
    private int speed = 32;
    private long lastTime;
    private int badSpeed;
    private Direction badDirection;

    @Override
    public void draw() {
        switch (this.direction) {
            case UP:
                try {
                    DrawUtils.draw(srcPath1, x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case DOWN:
                try {
                    DrawUtils.draw(srcPath2, x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case LEFT:
                try {
                    DrawUtils.draw(srcPath3, x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case RIGHT:
                try {
                    DrawUtils.draw(srcPath4, x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public MyTank(int x, int y) {
        super(x, y);
        String srcPath = "";
        switch (direction) {
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
            int[] size = DrawUtils.getSize(srcPath);
            this.weight = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(Direction direction) {
        if (this.direction != direction) {
            this.direction = direction;
        }
        if (this.badDirection == direction && this.badDirection != null) {
            switch (direction) {
                case UP:
                    this.y -= badSpeed;
                    break;
                case DOWN:
                    this.y += badSpeed;
                    break;
                case LEFT:
                    this.x -= badSpeed;
                    break;
                case RIGHT:
                    this.x += badSpeed;
                    break;
            }
        }
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x > Config.WEIGHT - 64) {
            x = Config.WEIGHT - 64;
        }
        if (y > Config.HEIGHT - 64) {
            y = Config.HEIGHT - 64;
        }
    }

    public Bullet shoot() {
        long nowTime = System.currentTimeMillis();
        if (nowTime - lastTime < 1000) {
            return null;
        } else {
            lastTime = nowTime;
            return new Bullet(this);
        }
    }


    @Override
    public boolean isDestory() {
        if (this.blood < 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Blast showBlast() {
        blood--;
        return new Blast(x, y, weight, height, blood);
    }

    @Override
    public boolean checkHit(Blockable blockable) {
        Element e = (Element) blockable;
        int x1 = this.x;
        int y1 = this.y;
        int w1 = this.weight;
        int h1 = this.height;
        switch (this.direction) {
            case UP:
                y1 -= speed;
                break;
            case DOWN:
                y1 += speed;
                break;
            case LEFT:
                x1 -= speed;
                break;
            case RIGHT:
                x1 += speed;
                break;
        }
        int x2 = e.x;
        int y2 = e.y;
        int w2 = e.weight;
        int h2 = e.height;

        boolean res = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
        if (res) {
            this.badDirection = direction;
            switch (this.direction) {
                //ToDo
                case UP:
                    badSpeed = this.y - y2 - h2;
                    break;
                case DOWN:
                    badSpeed = y2 - this.y - this.height;
                    break;
                case LEFT:
                    badSpeed = this.x - x2 - w2;
                    break;
                case RIGHT:
                    badSpeed = x2 - this.x - this.weight;
                    break;
            }
        } else {
            this.badDirection = null;
        }
        return res;
    }
}
