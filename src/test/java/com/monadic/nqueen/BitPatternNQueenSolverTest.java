package com.monadic.nqueen;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BitPatternNQueenSolverTest {

    @Test
    void shouldValidateSize() {
        // given
        NQueenSolver solver = new BitPatternNQueenSolver(new NoOpConstraint());

        // when
        // then
        assertThatThrownBy(() -> solver.solve(3)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> solver.solve(32)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldSolve4x4BoardWithoutAdditionalConstraints() {
        // given
        NQueenSolver solver = new BitPatternNQueenSolver(new NoOpConstraint());

        // when
        Optional<Solution> result = solver.solve(4);

        // then
        assertThat(result)
                .isPresent()
                .hasValue(new Solution(location(0, 1), location(1, 3), location(2, 0), location(3, 2)));
    }

    @Test
    void shouldSolve5x5BoardWithoutAdditionalConstraints() {
        // given
        NQueenSolver solver = new BitPatternNQueenSolver(new NoOpConstraint());

        // when
        Optional<Solution> result = solver.solve(5);

        // then
        assertThat(result)
                .isPresent()
                .hasValue(new Solution(location(0, 0), location(1, 2), location(2, 4), location(3, 1), location(4, 3)));
    }

    @Test
    void shouldSolve8x8BoardWithoutAdditionalConstraints() {
        // given
        NQueenSolver solver = new BitPatternNQueenSolver(new NoOpConstraint());

        // when
        Optional<Solution> result = solver.solve(8);

        // then
        assertThat(result)
                .isPresent()
                .hasValue(new Solution(location(0, 0), location(1, 4), location(2, 7), location(3, 5), location(4, 2), location(5, 6), location(6, 1), location(7, 3)));
    }

    @Test
    void shouldSolve4x4BoardWithCollinearConstraint() {
        // given
        NQueenSolver solver = new BitPatternNQueenSolver(new CollinearConstraint());

        // when
        Optional<Solution> result = solver.solve(4);

        // then
        assertThat(result)
                .isPresent()
                .hasValue(new Solution(location(0, 1), location(1, 3), location(2, 0), location(3, 2)));
    }

    @Test
    void shouldNotSolve5x5BoardWithCollinearConstraint() {
        // given
        NQueenSolver solver = new BitPatternNQueenSolver(new CollinearConstraint());

        // when
        Optional<Solution> result = solver.solve(5);

        // then
        assertThat(result)
                .isNotPresent();
    }

    @Test
    void shouldSolve8x8BoardWithCollinearConstraint() {
        // given
        NQueenSolver solver = new BitPatternNQueenSolver(new CollinearConstraint());

        // when
        Optional<Solution> result = solver.solve(8);

        // then
        assertThat(result)
                .isPresent()
                .hasValue(new Solution(location(0, 2), location(1, 4), location(2, 7), location(3, 3), location(4, 0), location(5, 6), location(6, 1), location(7, 5)));
    }

    private static Location location(int row, int column) {
        return new Location(row, column);
    }
}