package com.ambertests.aoc.days;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day06Test {
    @Test
    public void testRedistrubute(){
        int[] arr = new int[]{0,2,7,0};
        Day06 day = new Day06();
        day.redistribute(arr);
        assertArrayEquals(new int[]{2, 4, 1, 2}, arr);
    }

    @Test
    public void testFindLoops(){
        int[] arr = new int[]{0,2,7,0};
        Day06 day = new Day06();
        int loops = day.findLoop(arr);
        assertEquals(5, loops);
    }

    @Test
    public void testFindLoops2(){
        int[] arr = new int[]{0,2,7,0};
        Day06 day = new Day06();
        int loops = day.findLoop(arr);
        loops = day.findLoop(arr);
        assertEquals(4, loops);
    }
    
}
