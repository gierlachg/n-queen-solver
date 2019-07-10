package com.monadic.nqueen;

import java.util.List;

public class NoOpConstraint implements Constraint {

    @Override
    public boolean isValid(List<Location> existingLocations, int row, int column) {
        return true;
    }
}
