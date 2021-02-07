package com.ambertests.aoc.days;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Day03Test
{
    /**
Data from square 1 is carried 0 steps, since it's at the access port.
Data from square 12 is carried 3 steps, such as: down, left, left.
Data from square 23 is carried only 2 steps: up twice.
Data from square 1024 must be carried 31 steps.
     */

    @Test
    public void testGetDistance1(){
        Day03 day3 = new Day03();
        assertEquals(0, day3.getDistance(1));
    }

    @Test
    public void testGetDistance12(){
        Day03 day3 = new Day03();
        assertEquals(3, day3.getDistance(12));
    }

    @Test
    public void testGetDistance23(){
        Day03 day3 = new Day03();
        assertEquals(2, day3.getDistance(23));
    }

    @Test
    public void testGetDistance1024(){
        Day03 day3 = new Day03();
        assertEquals(31, day3.getDistance(1024));
    }
}