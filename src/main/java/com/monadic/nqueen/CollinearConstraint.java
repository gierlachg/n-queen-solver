package com.monadic.nqueen;

import java.util.List;

public class CollinearConstraint implements Constraint {

    @Override
    public boolean isValid(List<Location> existingLocations, int row, int column) {
        for (int i = 0; i < existingLocations.size() - 1; i++) {
            for (int j = i + 1; j < existingLocations.size(); j++) {
                Location firstLocation = existingLocations.get(i);
                Location secondLocation = existingLocations.get(j);
                if (areCollinear(firstLocation.getRow(), firstLocation.getColumn(),
                        secondLocation.getRow(), secondLocation.getColumn(),
                        row, column)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean areCollinear(int firstRow, int firstColumn,
                                        int secondRow, int secondColumn,
                                        int thirdRow, int thirdColumn) {
        return (firstRow - secondRow) * (firstColumn - thirdColumn) == (firstRow - thirdRow) * (firstColumn - secondColumn);
    }
}
