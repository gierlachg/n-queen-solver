package com.monadic.nqueen;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class BitPatternNQueenSolver implements NQueenSolver {

    private static final int MIN_BOARD_SIZE = 4;
    private static final int MAX_BOARD_SIZE = Integer.BYTES * 8 - 1;

    private final Constraint constraint;

    public BitPatternNQueenSolver(Constraint constraint) {
        this.constraint = constraint;
    }

    @Override
    public Optional<Solution> solve(int size) {
        if (size < MIN_BOARD_SIZE || size > MAX_BOARD_SIZE) {
            throw new IllegalArgumentException(format("Board size should be >= %s and <= %s", MIN_BOARD_SIZE, MAX_BOARD_SIZE));
        }

        int solvedMask = (int) (Math.pow(2, size) - 1);
        return solve(solvedMask, new Board(size), 0, 0, 0).map(Board::toSolution);
    }

    private Optional<Board> solve(int solvedMask, Board board, int leftDiagonalMask, int columnMask, int rightDiagonalMask) {
        if (columnMask == solvedMask) {
            return Optional.of(board);
        }

        int availableColumns = ~(leftDiagonalMask | columnMask | rightDiagonalMask);
        while ((availableColumns & solvedMask) > 0) {
            int chosenColumnMask = availableColumns & -availableColumns;
            int chosenColumn = Integer.numberOfTrailingZeros(chosenColumnMask);

            if (constraint.isValid(board.locations(), board.row(), chosenColumn)) {
                board.push(chosenColumn);

                int nextRowLeftDiagonalMask = (leftDiagonalMask | chosenColumnMask) >> 1;
                int nextRowColumnMask = columnMask | chosenColumnMask;
                int nextRowRightDiagonalMask = (rightDiagonalMask | chosenColumnMask) << 1;
                Optional<Board> solution = solve(solvedMask, board, nextRowLeftDiagonalMask, nextRowColumnMask, nextRowRightDiagonalMask);
                if (solution.isPresent()) {
                    return solution;
                }

                board.pop();
            }

            availableColumns -= chosenColumnMask;
        }

        return Optional.empty();
    }

    private class Board {

        private final List<Location> locations;

        private Board(int size) {
            this.locations = new ArrayList<>(size);
        }

        private int row() {
            return locations.size();
        }

        private void push(int column) {
            locations.add(new Location(locations.size(), column));
        }

        private void pop() {
            locations.remove(locations.size() - 1);
        }

        private List<Location> locations() {
            return locations;
        }

        private Solution toSolution() {
            return new Solution(locations);
        }
    }
}
