package com.company;

import javax.swing.*;

public class Frame extends JFrame {


    public static void main(String[] args) {
        // write your code here
        Frame frame = new Frame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel panel = new Panel();
        frame.add(panel);//自動でコンテナに張り付けてくれる

        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

    }
}
