package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Coordinate;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Day14Test {
    @Test
    public void testGetActiveSquares() {
        Day14 day = new Day14();
        assertEquals(8108, day.getActiveSquares("flqrgnkx").size());
    }

    @Test
    public void testGetRegionCount() {
        Day14 day = new Day14();
        ArrayList<Coordinate> active = day.getActiveSquares("flqrgnkx");
        assertEquals(1242, day.getRegionCount(active));
    }

    @Test
    public void solvePart1() {
        Day14 day = new Day14();
        assertEquals(8250, day.getActiveSquares("stpzcrnm").size());
    }

    @Test
    public void solvePart2() {
        Day14 day = new Day14();
        ArrayList<Coordinate> active = day.getActiveSquares("stpzcrnm");
        assertEquals(1113, day.getRegionCount(active));
    }

}
