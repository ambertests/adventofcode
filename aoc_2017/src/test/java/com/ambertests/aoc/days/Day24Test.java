package com.ambertests.aoc.days;

import org.junit.BeforeClass;
import org.junit.Ignore;
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
        assertEquals(3, day.prongsToPipes.get(2).size());
    }

    @Ignore("no longer valid test after perf optimization")
    @Test
    public void testBuildBridges() {
        Day24 day = new Day24();
        day.parseInput(input);
        day.buildBridges(0, new ArrayList<>(), new ArrayList<>());
        assertEquals(11, day.allBridges.size());
    }

    @Test
    public void testStrongestBridge() {
        Day24 day = new Day24();
        day.parseInput(input);
        day.buildBridges(0, new ArrayList<>(), new ArrayList<>());
        assertEquals(31, day.maxStrength);
    }

    static Day24 day = new Day24();

    @BeforeClass
    public static void beforeClass() {
        day.parseInput(day.getInputStringArray());
        day.buildBridges(0, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void solvesPart1() {
        assertEquals(2006, day.maxStrength);
    }

    @Test
    public void solvesPart2() {
        assertEquals(1994, day.getMaxStrengthForSize());
    }

}
