package com.itheima.game.domain;

import com.itheima.game.inter.Destory;
import org.itheima.game.DrawUtils;

import java.io.IOException;

/**
 * @Author Duyuquan
 * @date 2018/8/2
 */

public class Blast extends Element implements Destory {
    String[] arr = {"TankeLianXi1/src/res/img/blast_1.gif", "TankeLianXi1/src/res/img/blast_2.gif",
            "TankeLianXi1/src/res/img/blast_3.gif", "TankeLianXi1/src/res/img/blast_4.gif",
            "TankeLianXi1/src/res/img/blast_5.gif", "TankeLianXi1/src/res/img/blast_6.gif",
            "TankeLianXi1/src/res/img/blast_7.gif", "TankeLianXi1/src/res/img/blast_8.gif"};
    int len = arr.length;
    int index = 0;
    boolean flag = false;

    @Override
    public void draw() {
        if(index>len-1) {
            index=0;
            flag=true;
        }
        try {
            DrawUtils.draw(arr[index],x,y);
        } catch (IOException e) {
            e.printStackTrace();
        }
        index++;
    }

    public Blast() {
    }

    public Blast(int qx, int qy, int qw, int qh, int blood) {
        try {
            int[] size = DrawUtils.getSize(arr[0]);
            this.weight = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ToDo
        this.x = qx - (this.weight/2-qw/2);
        this.y = qy - (this.height/2-qh/2);

        if(blood>0) {
            len = 4;
        }
    }

    @Override
    public boolean isDestory() {
        return false;
    }
}
