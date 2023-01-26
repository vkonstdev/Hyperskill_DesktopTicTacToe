package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicTacToe extends JFrame implements ActionListener {

    private static final Logger log = Logger.getLogger(TicTacToe.class.getName());
    private final Board board = new Board(this);
    private final StatusBar statusBar = new StatusBar(this::reset);
    private int currentPlayer;

    {
        log.info("TicTacToe is started.");
        add(board, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 450);
        setBackground(Color.WHITE);
        setTitle("Tic Tac Toe");
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        log.entering(TicTacToe.class.getName(), "actionPerformed", e);
        final var cell = (Cell) e.getSource();
        final var index = getIndex(cell.getName().substring(6));
        log.log(Level.INFO, "Index: {0}, Status: {1}",
                new Object[]{index, board.getGameState().getMessage()});

        if (cell.isEmpty() && board.isPlaying()) {
            cell.setMark(currentPlayer == 0 ? Cell.Mark.X : Cell.Mark.O);
            statusBar.setMessage(board.getGameState());
            currentPlayer = 1 - currentPlayer;
        }
    }

    public void reset(final ActionEvent e) {
        board.clear();
        currentPlayer = 0;
        statusBar.setMessage(Board.State.NOT_STARTED);
    }

    private static int getIndex(final String notation) {
        log.info(notation);
        return ('3' - notation.charAt(1)) * 3 - 'A' + notation.charAt(0);
    }
}
