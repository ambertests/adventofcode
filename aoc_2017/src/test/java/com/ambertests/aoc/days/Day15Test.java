package com.ambertests.aoc.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15Test {
    @Test
    public void testJudgePart1() {
        Day15 day = new Day15();
        assertEquals(1, day.judge(65, 8921, 5));
    }

    @Test
    public void testJudgePart2() {
        Day15 day = new Day15();
        assertEquals(1, day.judge2(65, 8921, 1056));
    }

    @Test
    public void testJudgePart2_Long() {
        Day15 day = new Day15();
        assertEquals(309, day.judge2(65, 8921, 5000000));
    }

    @Test
    public void solvesPart1() {
        Day15 day = new Day15();
        assertEquals(638, day.judge(289, 629, 40000000));
    }

    @Test
    public void solvesPart2() {
        Day15 day = new Day15();
        assertEquals(343, day.judge2(289, 629, 5000000));
    }
}
