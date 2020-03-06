package com.company;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
    private PlayPanel panel;

    public GameOverPanel(PlayPanel panel) {
        this.panel = panel;

        this.setBackground(new Color(180, 10, 15));
        this.setPreferredSize(new Dimension(480, 480));
        JLabel jLabel = new JLabel("<html><body>ゲームオーバー!zakko<br>あなたの点数は：" + panel.getDieCount() + "点です</body></html>");
        jLabel.setPreferredSize(new Dimension(480, 480));
        jLabel.setFont(new Font("Arial", Font.BOLD, 30));
        jLabel.setForeground(Color.BLACK);
        jLabel.setHorizontalAlignment(JLabel.CENTER);

        add(jLabel);
    }
}
