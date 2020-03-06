package com.company;

import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel {
    private PlayPanel panel;

    public EndPanel(PlayPanel panel) {
        this.panel = panel;

        this.setBackground(new Color(60, 10, 100));
        this.setPreferredSize(new Dimension(480, 480));
        JLabel jLabel = new JLabel("<html><body>すごい！クリアです！！<br>あなたの点数：" + panel.getDieCount() + "です</body><html>");
        jLabel.setPreferredSize(new Dimension(480, 480));
        jLabel.setFont(new Font("Arial", Font.BOLD, 30));
        jLabel.setForeground(Color.WHITE);
        jLabel.setHorizontalAlignment(JLabel.CENTER);

        add(jLabel);

    }
}
