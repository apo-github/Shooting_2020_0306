package com.company;

import javax.swing.*;
import java.awt.*;

public class Player {
    private int width;
    private int height;
    private int x, y;
    private final int SPEED = 7;
    private PlayPanel panel;
    private Image image;


    public Player(int x, int y, String filename, PlayPanel panel) {
        this.x = x;
        this.y = y;
        this.panel = panel;
        loadImage(filename);

    }

    public void loadImage(String filename) {
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        image = icon.getImage();
        width = image.getWidth(panel);
        height = image.getHeight(panel);
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, panel);
    }

    public void move(int dir) {
        //20はimgの大きさ(px)
        if (x + 20 >= panel.getWidth()) {
            x = panel.getWidth() - 20;
        }
        if (x <= 0) {
            x = 0;
        }
        switch (dir) {
            case 0:
                this.x = x - SPEED;
                break;
            case 1:
                this.x = x + SPEED;
                break;
        }
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}






