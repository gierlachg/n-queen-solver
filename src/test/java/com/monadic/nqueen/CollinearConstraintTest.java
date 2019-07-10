package com.monadic.nqueen;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CollinearConstraintTest {

    private static CollinearConstraint constraint;

    @BeforeAll
    static void setUp() {
        constraint = new CollinearConstraint();
    }

    @Test
    void shouldDetectCollinearity() {
        // when
        boolean result = constraint.isValid(List.of(location(0, 0), location(1, 2)), 2, 4);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldNotComplainAboutLackOfCollinearity() {
        // when
        boolean result = constraint.isValid(List.of(location(0, 0), location(1, 2)), 3, 4);

        // then
        assertThat(result).isTrue();
    }

    private static Location location(int row, int column) {
        return new Location(row, column);
    }
}