package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends JFrame {

    TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setResizable(false);
        setSize(450, 450);
        Board board = new Board();
        //board.setBackground(Color.BLUE);
        add(board);
        setVisible(true);
    }

}
