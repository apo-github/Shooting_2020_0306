package com.company;

import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;

public class Enemy {
    private int x, y;
    private int width;
    private int height;
    private double speed = 1;
    private static final Point ENEMY_STORAGE = new Point(-100, -100);
    private Image image;
    private Shot shot;
    private Player player;

    public AudioClip bgm;
    private PlayPanel panel;


    public Enemy(int x, int y, String filename, Shot shot, Player player, PlayPanel panel) {
        this.x = x;
        this.y = y;
        this.shot = shot;
        this.player = player;
        this.panel = panel;
        loadImage(filename);
    }

    public void loadImage(String filename) {
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        image = icon.getImage();

        //画像の幅、高さを読み取る//panelにその情報を引き渡している（当たり判定に必要）
        width = image.getWidth(panel);
        height = image.getHeight(panel);
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, panel);
    }

    public void move() {
        if (panel.getDieCount() == (int) (Math.random() * 200) + 1) {
            speed = speed + 8;
        }

        this.y += speed;

        if (y > 480) {//画面外にでたらオブジェクトを保管庫へ
            GoStorage();
        }
    }

    public boolean isInStorage() {
        if (this.x == ENEMY_STORAGE.x || this.y == ENEMY_STORAGE.y) {
            return true;
        }
        return false;
    }

    /**
     * @return 衝突していたらtrue
     */
    public boolean collideWith() {
        Rectangle rectEnemy = new Rectangle(x, y, width, height);//敵の大きさ
        Rectangle rectShot = new Rectangle(shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight());//弾の大きさ

        return rectEnemy.intersects(rectShot);//AとBの当たり判定(rectA.intersects(B))
    }

    public boolean collideWithPlayer() {
        Rectangle rectEnemy = new Rectangle(x, y, width, height);
        Rectangle rectPlayer = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());

        return rectEnemy.intersects(rectPlayer);
    }

    public void GoStorage() {
        this.x = ENEMY_STORAGE.x;
        this.y = ENEMY_STORAGE.y;
    }
}
