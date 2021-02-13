package com.ambertests.aoc.days;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01Test {
    static Day01 day = new Day01();

    @BeforeClass
    static public void beforeClass() {
        day.solve();
    }

    @Test
    public void solvesPart1() {
        assertEquals(1251, day.solution1);
    }

    @Test
    public void solvesPart2() {
        assertEquals(1244, day.solution2);
    }
}
