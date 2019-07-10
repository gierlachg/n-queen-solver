package com.monadic.nqueen;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

public class BitPatternNQueenSolverBenchmark {

    @State(Scope.Benchmark)
    public static class Solvers {

        @Param({"8", "16"})
        private int size;

        private BitPatternNQueenSolver noConstraintsSolver;
        private BitPatternNQueenSolver collinearSolver;

        @Setup(Level.Trial)
        public void setUp() {
            noConstraintsSolver = new BitPatternNQueenSolver(new NoOpConstraint());
            collinearSolver = new BitPatternNQueenSolver(new CollinearConstraint());
        }

        private int getSize() {
            return size;
        }

        private BitPatternNQueenSolver getNoConstraintsSolver() {
            return noConstraintsSolver;
        }

        private BitPatternNQueenSolver getCollinearSolver() {
            return collinearSolver;
        }
    }

    @Benchmark
    public void noConstraints(Solvers solvers, Blackhole blackhole) {
        blackhole.consume(solvers.getNoConstraintsSolver().solve(solvers.getSize()));
    }

    @Benchmark
    public void collinearConstraints(Solvers solvers, Blackhole blackhole) {
        blackhole.consume(solvers.getCollinearSolver().solve(solvers.getSize()));
    }
}