package com.ambertests.aoc.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day05Test {
    @Test
    public void solvesPart1() {
        Day05 day = new Day05();
        assertEquals(358309, day.escapeArray(day.getInputInts(), 1));
    }

    @Test
    public void solvesPart2() {
        Day05 day = new Day05();
        assertEquals(28178177, day.escapeArray(day.getInputInts(), 2));
    }
}
