package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board extends JPanel {

    private final List<Cell> cells;
    private static final int[][] TRIPS = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};

    Board(final ActionListener listener) {
        super();
        setSize(450, 450);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(3, 3));
        cells = Stream.of("A3", "B3", "C3", "A2", "B2", "C2", "A1", "B1", "C1")
                .map(name -> new Cell(name, listener))
                .peek(this::add)
                .toList();
        setVisible(true);
    }

    void clear() {
        cells.forEach(Cell::clear);
    }

    public enum State {
        NOT_STARTED("Game is not started"),
        PLAYING("Game in progress"),
        DRAW("Draw"),
        X_WINS("X wins"),
        O_WINS("O wins");

        final String message;

        State(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public State getGameState() {
        if (isEmpty()) {
            return State.NOT_STARTED;
        }
        if (hasTrips(Cell.Mark.X)) {
            return State.X_WINS;
        }
        if (hasTrips(Cell.Mark.O)) {
            return State.O_WINS;
        }
        if (isFull()) {
            return State.DRAW;
        }
        return State.PLAYING;
    }

    private boolean isEmpty() {
        return cells.stream().map(JButton::getText).allMatch(String::isBlank);
    }

    private boolean isEmpty(final int index) {
        return cells.get(index).getText().isBlank();
    }

    private boolean isFull() {
        return cells.stream().map(JButton::getText).noneMatch(String::isBlank);
    }

    private boolean hasTrips(Cell.Mark mark) {
        Predicate<int[]> threeInRow = line -> Arrays.stream(line)
                .mapToObj(cells::get)
                .map(JButton::getText)
                .allMatch(mark.getMark()::equals);
        return Arrays.stream(TRIPS).anyMatch(threeInRow);
    }

    public boolean isPlaying() {
        final var state = getGameState();
        return state == State.NOT_STARTED || state == State.PLAYING;
    }

    public int[] getFreeCells() {
        return IntStream.range(0, 9).filter(this::isEmpty).toArray();
    }

    public Cell getRandomFreeCell() {
        final var freeCells = cells.stream()
                .filter(cell -> cell.getText().equals(Cell.Mark.EMPTY.getMark()))
                .collect(Collectors.toList());
        Collections.shuffle(freeCells);
        return freeCells.get(0);
    }

    void setPlaying(final boolean isPlaying) {
        cells.forEach(cell -> cell.setEnabled(isPlaying));

    }

}

