package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    Board() {
        add(new Button("A3"));
        add(new Button("B3"));
        add(new Button("C3"));
        add(new Button("A2"));
        add(new Button("B2"));
        add(new Button("C2"));
        add(new Button("A1"));
        add(new Button("B1"));
        add(new Button("C1"));
        setLayout(new GridLayout(3, 3));
        setVisible(true);
    }
}

