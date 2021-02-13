package com.ambertests.aoc.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day17Test {
    @Test
    public void solvesPart1() {
        Day17 day = new Day17();
        assertEquals(180, day.spinLock(2017, 1));
    }

    @Test
    public void solvesPart2() {
        Day17 day = new Day17();
        assertEquals(13326437, day.spinLock(50000000, 2));
    }
}
