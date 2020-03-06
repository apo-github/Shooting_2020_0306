package com.company;

import javax.swing.*;
import java.awt.*;

public class Shot {
    private int x, y;//弾の位置
    private static final int SPEED = 10;//弾丸のスピード
    private static final Point SHOT_STORAGE = new Point(500, 500);//保管庫
    private int width;
    private int height;
    private Image image;
    private PlayPanel panel;
    private Player player;

    public Shot(String filename, Player player, PlayPanel panel) {
        this.x = SHOT_STORAGE.x;
        this.y = SHOT_STORAGE.y;
        this.panel = panel;
        this.player = player;
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


    public void move(boolean storage) {
        //プレイヤーから発射されるようにする
        if (isInStrage()) {//保管庫に弾が入るまで打てないようにする
            if (storage) {
                //真ん中から発射するように調整
                this.x = player.getX() + 9;
                this.y = player.getY() - 13;
            }
        } else {

            this.y -= SPEED;

            if (y < 0) {//画面外にでたら保管庫へ
                this.x = SHOT_STORAGE.x;
                this.y = SHOT_STORAGE.y;
            }
        }

    }

    public boolean isInStrage() {
        if (this.x == SHOT_STORAGE.x || this.y == SHOT_STORAGE.y) {
            return true;
        }
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
