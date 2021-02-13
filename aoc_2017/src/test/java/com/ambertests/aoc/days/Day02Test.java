package com.ambertests.aoc.days;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02Test {
    static Day02 day = new Day02();

    @BeforeClass
    static public void beforeClass() {
        day.solve();
    }

    @Test
    public void solvesPart1() {
        assertEquals(47136, day.solution1);
    }

    @Test
    public void solvesPart2() {
        assertEquals(250, day.solution2);
    }
}
