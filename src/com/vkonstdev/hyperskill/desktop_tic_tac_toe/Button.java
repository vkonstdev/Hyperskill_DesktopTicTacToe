package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button(String text) {
        super(text);
        this.setName("Button" + text);
        this.setFont(new Font("Arial", Font.BOLD, 40));
        this.setBackground(Color.ORANGE);
    }
}
