package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerChooser extends JButton implements ActionListener {

    {
        setText(Type.Human.name());
        addActionListener(this);
        setEnabled(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setText(getText().equals(Type.Human.name()) ? Type.Robot.name() : Type.Human.name());
    }

    public enum Type {
        Human, Robot
    }
}
