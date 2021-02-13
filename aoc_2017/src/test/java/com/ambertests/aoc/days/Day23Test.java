package com.ambertests.aoc.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day23Test {

    @Test
    public void solvesPart1() {
        Day23 day = new Day23();
        assertEquals(4225, day.run(day.getInputStringArray(), 0L));
    }

    @Test
    public void solvesPart2() {
        Day23 day = new Day23();
        assertEquals(905, day.calculate2());
    }

}
