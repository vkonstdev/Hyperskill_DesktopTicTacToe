package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Cell extends JButton {

    private static final String EMPTY_CELL = " ";

    Cell(final String name, final ActionListener listener) {
        super(EMPTY_CELL);
        setName("Button" + name);
        setFont(new Font("Arial", Font.BOLD, 40));
        setBackground(Color.ORANGE);
        addActionListener(listener);
        setFocusPainted(false);
        setVisible(true);
    }

    void setMark(final Mark mark) {
        setText(mark.getMark());
    }

    boolean isEmpty() {
        return getText().equals(Mark.EMPTY.getMark());
    }

    void clear() {
        setText(Mark.EMPTY.getMark());
    }

    public enum Mark {

        EMPTY(" "), X("X"), O("O");

        private final String mark;

        Mark(String mark) {
            this.mark = mark;
        }

        public String getMark() {
            return mark;
        }


    }
}
