package com.monadic.nqueen;

import java.util.List;

public interface Constraint {

    boolean isValid(List<Location> existingLocations, int row, int column);
}
