package com.monadic.nqueen;

import lombok.Value;

import java.util.List;

import static java.util.Arrays.asList;

@Value
public class Solution {

    private final List<Location> locations;

    Solution(Location... locations) {
        this(asList(locations));
    }

    Solution(List<Location> locations) {
        this.locations = locations;
    }
}
