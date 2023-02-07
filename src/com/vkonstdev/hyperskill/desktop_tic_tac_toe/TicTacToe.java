package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class TicTacToe extends JFrame implements ActionListener {
    private static final Logger log = Logger.getLogger(TicTacToe.class.getName());
    private static final Pattern PLAYERS = Pattern.compile(
            "(?<Player1>Human|Robot).+(?<Player2>Human|Robot)");
    private static final long ROBOT_DELAY = 1000;

    private final Board board = new Board(this);
    private final StatusBar statusBar = new StatusBar();
    private final ToolBar toolbar = new ToolBar(this);
    private Timer robotTimer;

    private int currentPlayer;

    public TicTacToe() {
        log.info("TicTacToe is started.");
        setJMenuBar(new AppMenu(this::processMenu));
        add(board, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);
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
        final var button = (JButton) e.getSource();
        if (button.getText().equals("Reset")) {
            reset();
        } else if (button.getText().equals("Start")) {
            start();
        } else {
            humanMove((Cell) button);
        }
        log.exiting(TicTacToe.class.getName(), "actionPerformed", board.getGameState());
    }

    void processMenu(final ActionEvent e) {
        log.entering(TicTacToe.class.getName(), "processMenu", e.getSource());
        final var item = (JMenuItem) e.getSource();
        final var matcher = PLAYERS.matcher(item.getText());
        if (matcher.matches()) {
            reset();
            toolbar.players[0].setText(matcher.group("Player1"));
            toolbar.players[1].setText(matcher.group("Player2"));
            start();
        } else {
            this.dispose();
        }
        log.exiting(TicTacToe.class.getName(), "processMenu", board.getGameState());
    }

    public void humanMove(final Cell cell) {
        log.entering(TicTacToe.class.getName(), "humanMove", cell);
        if (!cell.isEmpty() || !board.isPlaying() || isRobotsTurn()) {
            log.warning("An illegal move from the Human");
            return;
        }
        move(cell);
        log.exiting(TicTacToe.class.getName(), "humanMove", board.getGameState());
    }

    public void robotMove(final Cell cell) {
        log.entering(TicTacToe.class.getName(), "robotMove", cell);
        if (!cell.isEmpty() || !board.isPlaying() || !isRobotsTurn()) {
            log.warning("An illegal move from the Robot");
            return;
        }
        move(cell);
        log.exiting(TicTacToe.class.getName(), "robotMove", board.getGameState());
    }

    private void move(final Cell cell) {
        cell.setMark(currentPlayer == 0 ? Cell.Mark.X : Cell.Mark.O);
        if (board.getGameState() != Board.State.PLAYING) {
            board.setPlaying(false);
        } else {
            currentPlayer = 1 - currentPlayer;
        }
        final var mark = currentPlayer == 0 ? Cell.Mark.X : Cell.Mark.O;
        statusBar.setMessage(board.getGameState(), currentPlayer(), mark.getMark());
        checkRobot();

    }

    public void start() {
        toolbar.startGame();
        statusBar.setMessage(Board.State.PLAYING, currentPlayer(), Cell.Mark.X.getMark());
        board.setPlaying(true);
        robotTimer = new Timer("Robot", true);
        checkRobot();
    }

    private void checkRobot() {
        if (isRobotsTurn() && board.isPlaying()) {
            robotTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    robotMove(board.getRandomFreeCell());
                }
            }, ROBOT_DELAY);
        }
    }

    private String currentPlayer() {
        return toolbar.players[currentPlayer].getText();
    }

    private boolean isRobotsTurn() {
        return toolbar.players[currentPlayer].getText().equals("Robot");
    }

    public void reset() {
        robotTimer.cancel();
        board.clear();
        currentPlayer = 0;
        toolbar.resetGame();
        statusBar.setMessage(Board.State.EMPTY);
        board.setPlaying(false);
    }
}