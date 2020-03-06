package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayPanel extends JPanel implements ActionListener, KeyListener {
    private static final int GAME_OVER = 3;

    private JLabel scoreLabel;
    private JLabel damageLabel;
    private final int WIDTH = 480;
    private final int HEIGHT = 480;

    private Player player;
    private Shot shot;
    private Enemy[] enemys;
    private static final int ENEMY_NUM = 10;

    private final int LEFT = 0;
    private final int RIGHT = 1;
    private final int SPACE = 2;
    private final int NOT_PRESS = 3;
    private final int ENTER = 4;

    int key = NOT_PRESS;

    private Timer panelTimer;//一定時間毎にactionPerformedの処理を行う
    private Thread thread;//一時停止

    private int dieCount = 0;//倒したカウント
    private int damageCount = 100;//ダメージカウント
    private int storageCount = 0;

    public PlayPanel() {
        //SCORE表示Label

        setPreferredSize(new Dimension(480, 480));
        setBackground(Color.BLACK);

        this.setFocusable(true);
        this.addKeyListener(this);

        scoreLabel = new JLabel();//点数表示
        scoreLabel.setFont(new Font("MS ゴシック", Font.BOLD, 15));
        scoreLabel.setPreferredSize(new Dimension(100, 20));
        scoreLabel.setHorizontalAlignment(10);
        scoreLabel.setForeground(Color.WHITE);
        add(scoreLabel);

        damageLabel = new JLabel();//点数表示
        damageLabel.setFont(new Font("MS ゴシック", Font.BOLD, 15));
        damageLabel.setPreferredSize(new Dimension(100, 20));
        damageLabel.setForeground(Color.WHITE);
        add(damageLabel);

        player = new Player(240, 400, "img/player.gif", this);
        shot = new Shot("img/shot.gif", player, this);

        //敵を10体生成
        enemys = new Enemy[ENEMY_NUM];
        for (int i = 0; i < ENEMY_NUM; i++) {//敵をランダムに出現させる
            enemys[i] = new Enemy((int) (Math.random() * 480) + 1, -(int) (Math.random() * 100) + 1, "img/enemy.gif", shot, player, this);
        }


        panelTimer = new Timer(16, this);//16ms毎にした
        panelTimer.start();//タイマーをスタートさせる


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //プレイヤーとか敵とか描いていく
        player.draw(g);//プレイヤー描画
        shot.draw(g);//弾描画
        for (int i = 0; i < ENEMY_NUM; i++) {
            enemys[i].draw(g);//敵描画
        }
        requestFocusInWindow();//フォーカスをこのwindowにする//キー入力を受け付けなかったのでおかしいと思ったら、こんなのが必要だった。(初期化処理コンストラクタなどでは反応しない)
    }


    @Override
    public void actionPerformed(ActionEvent e) {//ActionEvent eが派生したとき自動で呼び出される

        shot.move(false);

        //キー入力actionListenerでmove呼び出したほうがカクつかない。

        switch (key) {
            case LEFT:
                player.move(LEFT);
                break;
            case RIGHT:
                player.move(RIGHT);
                break;
            case SPACE:
                shot.move(true);
                break;
        }


        //敵と弾当たり判定
        for (int i = 0; i < ENEMY_NUM; i++) {
            if (enemys[i].collideWith()) {
                enemys[i].GoStorage();
                dieCount++;
                break;
            }
        }

        //playerと敵当たり判定
        for (int i = 0; i < ENEMY_NUM; i++) {
            if (enemys[i].collideWithPlayer()) {
                enemys[i].GoStorage();
                damageCount = damageCount - 10;
                break;
            }
        }


        //次の敵生成とスレッドの処理
        for (int i = 0; i < ENEMY_NUM; i++) {
            enemys[i].move();
            if (enemys[i].isInStorage()) {
                storageCount++;//全敵が出撃したかをカウント
            }
        }

        if (storageCount == 10) {
            try {//次の敵が出撃するまでのtimeスレッド
                thread.sleep(1000);
                for (int i = 0; i < ENEMY_NUM; i++) {//敵をランダムに出現させる
                    enemys[i] = new Enemy((int) (Math.random() * 480) + 1, -(int) (Math.random() * 100) + 1, "img/enemy.gif", shot, player, this);
                }
            } catch (InterruptedException ex) {
                //なんもしない
            }
        }

        storageCount = 0;//敵の保管庫カウントをリセットする

        scoreLabel.setText("SCORE" + dieCount);
        damageLabel.setText("HP" + damageCount);

        //もしHPが0になったらゲームオーバー
        if (damageCount == 0) {

        }
        //もし10体倒せたらゲームクリア
        if (dieCount == 10) {

        }

        repaint();//16ms毎に描画しなおし
    }


/*TODO
プレイヤー操作
 * */

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                this.key = LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                this.key = RIGHT;
                break;
            case KeyEvent.VK_SPACE:
                this.key = SPACE;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                this.key = NOT_PRESS;
                break;
            case KeyEvent.VK_RIGHT:
                this.key = NOT_PRESS;
                break;
            case KeyEvent.VK_SPACE:
                this.key = NOT_PRESS;
                break;
        }
        repaint();
    }


    public int getEnemyNum() {
        return ENEMY_NUM;
    }

    public int getDieCount() {
        return dieCount;
    }

    public void setDieCount(int dieCount) {
        this.dieCount = dieCount;
    }

}
