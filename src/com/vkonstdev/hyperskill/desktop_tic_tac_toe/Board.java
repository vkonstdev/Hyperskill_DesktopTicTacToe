package com.vkonstdev.hyperskill.desktop_tic_tac_toe;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board extends JPanel  {
    private static final int[][] TRIPS = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
    private final List<Cell> cells;

    Board(final ActionListener listener) {
        super();
        setSize(450, 450);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(3, 3));
        cells = Stream.of("A3", "B3", "C3", "A2", "B2", "C2", "A1", "B1", "C1")
                .map(name -> new Cell(name, listener))
                .peek(this::add).toList();

        setVisible(true);
    }

    void clear() {
        cells.forEach(Cell::clear);
    }

    void setPlaying(final boolean isPlaying) {
        cells.forEach(cell -> cell.setEnabled(isPlaying));

    }

    public State getGameState() {
        if (isEmpty()) {
            return State.EMPTY;
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
        return state == State.EMPTY || state == State.PLAYING;
    }

    public Cell getRandomFreeCell() {
        final var freeCells = cells.stream()
                .filter(cell -> cell.getText().equals(Cell.Mark.EMPTY.getMark()))
                .collect(Collectors.toList());
        Collections.shuffle(freeCells);
        return freeCells.get(0);
    }

    public enum State {
        EMPTY("Game is not started"),
        PLAYING("The turn of {0} Player ({1})"),
        DRAW("Draw"),
        X_WINS("The {0} Player (X) wins"),
        O_WINS("The {0} Player (O) wins");

        final String message;

        State(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}

