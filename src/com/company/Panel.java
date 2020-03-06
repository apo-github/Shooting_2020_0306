package com.company;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener {
    public AudioClip bgm;
    private int scene;
    private PlayPanel playPanel;
    private StartPanel startPanel;
    private InstructionPanel instructionPanel;
    private GameOverPanel gameOverPanel;
    private EndPanel endPanel;


    private final int NOT_PRESS = 3;
    private final int ENTER = 4;
    private int enter_keyCount = 0;

    private int key = NOT_PRESS;


    public Panel() {

        StartPanel startPanel = new StartPanel();
        this.add(startPanel);

        setFocusable(true);
        addKeyListener(this);


        bgm = Applet.newAudioClip(getClass().getResource("sound/bgm.wav"));
        bgm.play();


    }

    public void changePanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.validate();//検証
        repaint();
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

        if (key == KeyEvent.VK_ENTER || enter_keyCount == 0) {
            key = ENTER;
            playPanel = new PlayPanel(this);
            changePanel(playPanel);

            enter_keyCount++;//Enterが一回押されたのでプラスしとく
        }
        if (key == KeyEvent.VK_ENTER || enter_keyCount == 1) {
            key = ENTER;
            instructionPanel = new InstructionPanel();
            changePanel(instructionPanel);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
