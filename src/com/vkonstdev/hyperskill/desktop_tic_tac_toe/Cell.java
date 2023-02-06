package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Cell extends JButton {

    Cell(final String name, final ActionListener listener) {
        super(Mark.EMPTY.getMark());
        setName("Button" + name);
        setFont(new Font("Arial", Font.BOLD, 40));
        setBackground(Color.ORANGE);
        addActionListener(listener);
        setFocusPainted(false);
        setVisible(true);
        setEnabled(false);
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

    int getIndex() {
        final var notation = this.getName().substring(6);
        return ('3' - notation.charAt(1)) * 3 - 'A' + notation.charAt(0);
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
