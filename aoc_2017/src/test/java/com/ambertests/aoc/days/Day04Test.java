package com.ambertests.aoc.days;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day04Test {
    static Day04 day = new Day04();

    @BeforeClass
    static public void beforeClass() {
        day.solve();
    }

    @Test
    public void solvesPart1() {
        assertEquals(337, day.solution1);
    }

    @Test
    public void solvesPart2() {
        assertEquals(231, day.solution2);
    }
}
