package com.monadic.nqueen;

import java.util.Optional;

public interface NQueenSolver {

    Optional<Solution> solve(int size);
}
