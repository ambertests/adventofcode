package com.ambertests.aoc.days;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Day12Test {
    String[] input = new String[]{
            "0 <-> 2",
            "1 <-> 1",
            "2 <-> 0, 3, 4",
            "3 <-> 2, 4",
            "4 <-> 2, 3, 6",
            "5 <-> 6",
            "6 <-> 4, 5"
    };

    @Test
    public void testParseInput() {
        Day12 day = new Day12();
        day.parseInput(input);
        assertEquals(7, day.connections.size());
        assertArrayEquals(new String[]{"2", "3", "6"}, day.connections.get("4"));
    }

    @Test
    public void testGetConnections() {
        Day12 day = new Day12();
        day.parseInput(input);
        HashSet<String> start = new HashSet<>(Collections.singletonList("0"));
        HashSet<String> connections = day.getConnections("0", start);
        assertEquals(6, connections.size());
    }

    @Test
    public void testCountGroups() {
        Day12 day = new Day12();
        day.parseInput(input);
        assertEquals(2, day.countGroups());
    }

}
