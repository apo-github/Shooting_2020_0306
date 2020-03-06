package com.company;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    public StartPanel() {
        setPreferredSize(new Dimension(480, 480));
        setBackground(Color.darkGray);
        JLabel startlabel = new JLabel("ENTERを押してね!");
        startlabel.setPreferredSize(new Dimension(480, 480));
        startlabel.setForeground(Color.WHITE);
        startlabel.setFont(new Font("Aeial", Font.BOLD, 30));
        startlabel.setHorizontalAlignment(JLabel.CENTER);

        add(startlabel);
    }
}
