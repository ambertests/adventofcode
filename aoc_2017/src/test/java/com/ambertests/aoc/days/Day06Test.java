package com.ambertests.aoc.days;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Day06Test {
    @Test
    public void testRedistribute() {
        int[] arr = new int[]{0, 2, 7, 0};
        Day06 day = new Day06();
        day.redistribute(arr);
        assertArrayEquals(new int[]{2, 4, 1, 2}, arr);
    }

    @Test
    public void testFindLoops() {
        int[] arr = new int[]{0, 2, 7, 0};
        Day06 day = new Day06();
        int loops = day.findLoop(arr);
        assertEquals(5, loops);
    }

    @Test
    public void testFindLoops2() {
        int[] arr = new int[]{0, 2, 7, 0};
        Day06 day = new Day06();
        day.findLoop(arr);
        assertEquals(4, day.findLoop(arr));
    }

    @Test
    public void solvesPart1() {
        Day06 day = new Day06();
        int[] nums = Arrays.stream(day.getInputString().split("\t"))
                .mapToInt(Integer::parseInt).toArray();
        assertEquals(4074, day.findLoop(nums));
    }

    @Test
    public void solvesPart2() {
        Day06 day = new Day06();
        int[] nums = Arrays.stream(day.getInputString().split("\t"))
                .mapToInt(Integer::parseInt).toArray();
        day.findLoop(nums);
        assertEquals(2793, day.findLoop(nums));
    }

}
