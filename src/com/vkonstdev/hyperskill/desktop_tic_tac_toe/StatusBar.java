package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {

    final JLabel status = new JLabel(Board.State.NOT_STARTED.getMessage());

    {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        status.setName("LabelStatus");
        status.setPreferredSize(new Dimension(350, 20));
        add(status);
    }

    void setMessage(Board.State state) {
        status.setText(state.getMessage());
    }
}
