package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.MessageFormat;

public class StatusBar extends JPanel {
    final JLabel status = new JLabel(Board.State.EMPTY.getMessage());

    {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        status.setName("LabelStatus");
        status.setPreferredSize(new Dimension(350, 20));
        add(status);
    }

    void setMessage(Board.State state, Object... args) {
        status.setText(MessageFormat.format(state.getMessage(), args));
    }
}
