package com.ambertests.aoc.days;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Day24Test {
    String[] input = new String[]{
            "0/2",
            "2/2",
            "2/3",
            "3/4",
            "3/5",
            "0/1",
            "10/1",
            "9/10"
    };

    @Test
    public void testParseInput() {
        Day24 day = new Day24();
        day.parseInput(input);
        assertEquals(8, day.prongsToPipes.size());
        assertEquals(3, day.prongsToPipes.get("2").size());
    }

    @Test
    public void testBuildBridges() {
        Day24 day = new Day24();
        day.parseInput(input);
        day.buildBridges("0", "", new ArrayList<>());
        assertEquals(11, day.allBridges.size());
    }

    @Test
    public void testStrongestBridge() {
        Day24 day = new Day24();
        day.parseInput(input);
        day.buildBridges("0", "", new ArrayList<>());
        int strongest = day.allBridges.stream().map(day::strength).max(Integer::compareTo).orElse(0);
        assertEquals(31, strongest);
    }

}
