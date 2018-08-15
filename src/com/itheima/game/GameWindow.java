package com.itheima.game;

import com.itheima.game.domain.*;
import com.itheima.game.inter.Config;
import com.itheima.game.inter.Direction;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.itheima.game.SoundUtils;
import org.itheima.game.Window;
import org.lwjgl.input.Keyboard;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author Duyuquan
 * @date 2018/8/1
 */

public class GameWindow extends Window {
    //    List<Element> list = new ArrayList<>();
    CopyOnWriteArrayList<Element> list;
    MyTank mt;

    public GameWindow(String title, int width, int height, int fps) {
        super(title, width, height, fps);
    }

    @Override
    protected void onCreate() {
        try {
            SoundUtils.play("TankeLianXi1\\src\\res\\snd\\GMRemix-FC_Tank.wav");
        } catch (IOException e) {
            e.printStackTrace();
        }
        list = readExcel("D:\\IdeaProjects5\\TankeWarPeng\\TankeLianXi1\\src\\res\\maps.xls");
        /*for (int i=0;i< Config.WEIGHT/64-1;i++) {
            list.add(new Wall(i*64,64));
        }
        for (int i=1;i< Config.WEIGHT/64;i++) {
            list.add(new Steel(i*64,64*3));
        }
        for (int i=0;i< Config.WEIGHT/64-1;i++) {
            list.add(new Water(i*64,64*5));
        }
        for (int i=1;i< Config.WEIGHT/64;i++) {
            list.add(new Grass(i*64,64*7));
        }
        *//*for (int i=1;i< Config.WEIGHT/64-1;i++) {
            list.add(new MyTank(i*64,64*9));
        }*//*
        mt = new MyTank(Config.WEIGHT/2-32,Config.HEIGHT-64);
        //ToDo 如果要获取坦克的weight和height怎么做
        list.add(mt);*/
    }

    public CopyOnWriteArrayList<Element> readExcel(String str) {
        CopyOnWriteArrayList<Element> list = new CopyOnWriteArrayList<>();
        try {
            Workbook wb = Workbook.getWorkbook(new FileInputStream(str));
            Sheet sheet = wb.getSheet(0);
            int element;
            for (int i = 0; i < sheet.getRows(); i++) {
                for (int j = 0; j < sheet.getColumns(); j++) {
                    //ToDo 这里很奇怪
                   /* if (Objects.equals(sheet.getCell(j,i).getContents(),"")) {
                        element = 0;
                    } else {
                        element = Integer.parseInt(sheet.getCell(j, i).getContents());
                    }*/
                    //element = Objects.equals(sheet.getCell(j, i).getContents(), "") ? 0 : Integer.parseInt(sheet.getCell(j, i).getContents());
                    element = "".equals(sheet.getCell(j,i).getContents()) ? 0 : Integer.parseInt(sheet.getCell(j, i).getContents());

                    switch (element) {
                        case 1:
                            Wall wall = new Wall(64 * j, 64 * i);
                            list.add(wall);
                            break;
                        case 2:
                            Steel steel = new Steel(64 * j, 64 * i);
                            list.add(steel);
                            break;
                        case 3:
                            Water water = new Water(64 * j, 64 * i);
                            list.add(water);
                            break;
                        case 4:
                            Grass grass = new Grass(64 * j, 64 * i);
                            list.add(grass);
                            break;
                        case 5:
                            mt = new MyTank(64 * j, 64 * i);
                            list.add(mt);
                            break;
                    }
                }
            }
            System.out.println("地图中的元素个数为："+list.size());
            Collections.sort(list, new Comparator<Element>() {
                @Override
                public int compare(Element o1, Element o2) {
                    return o1.getLevel()-o2.getLevel();
                }
            });
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onMouseEvent(int key, int x, int y) {

    }

    @Override
    protected void onKeyEvent(int key) {
        if (key == Keyboard.KEY_UP) {
            mt.move(Direction.UP);
        } else if (key == Keyboard.KEY_DOWN) {
            mt.move(Direction.DOWN);
        } else if (key == Keyboard.KEY_LEFT) {
            mt.move(Direction.LEFT);
        } else if (key == Keyboard.KEY_RIGHT) {
            mt.move(Direction.RIGHT);
        } else if (key == Keyboard.KEY_SPACE) {
            Bullet bullet = mt.shoot();
            list.add(bullet);
        }

    }

    @Override
    protected void onDisplayUpdate() {
        //if(mt.)
        for (Element e : list) {
            e.draw();
        }
    }
}
