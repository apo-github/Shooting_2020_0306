package com.company;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    public StartPanel() {
        setPreferredSize(new Dimension(480, 480));
        setBackground(Color.BLUE);
        JLabel startlabel = new JLabel("ENTERを押してね");
        add(startlabel);
    }
}
