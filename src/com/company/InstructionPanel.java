package com.company;

import javax.swing.*;
import java.awt.*;

public class InstructionPanel extends JPanel {
    public InstructionPanel() {
        setPreferredSize(new Dimension(480, 480));
        setBackground(Color.DARK_GRAY);
        JLabel instructionPanel = new JLabel("<html><body>やり方：<br/>スペースキーで射撃<br/>矢印キーで移動できます<br>100点とるとクリアです！</body></html>");
        instructionPanel.setPreferredSize(new Dimension(480, 480));
        instructionPanel.setForeground(Color.WHITE);
        instructionPanel.setFont(new Font("Aeial", Font.BOLD, 30));
        instructionPanel.setHorizontalAlignment(JLabel.CENTER);

        add(instructionPanel);
    }
}
