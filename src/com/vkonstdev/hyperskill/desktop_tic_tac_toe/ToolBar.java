package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel {
    final PlayerChooser[] players = new PlayerChooser[]{new PlayerChooser(), new PlayerChooser()};
    private final JButton startReset = new JButton("Start");

    ToolBar(final ActionListener listener) {
        setLayout(new GridLayout(1, 3));
        players[0].setName("ButtonPlayer1");
        players[1].setName("ButtonPlayer2");
        add(players[0]);
        add(startReset);
        add(players[1]);
        setPreferredSize(new Dimension(450, 30));
        setVisible(true);
        startReset.setName("ButtonStartReset");
        startReset.addActionListener(listener);
    }

    public void startGame() {
        players[0].setEnabled(false);
        players[1].setEnabled(false);
        startReset.setText("Reset");
    }

    public void resetGame() {
        players[0].setEnabled(true);
        players[1].setEnabled(true);
        startReset.setText("Start");
    }
}
