package com.ambertests.aoc.days;

import org.junit.Test;
import static org.junit.Assert.*;

public class Day11Test {
    @Test
    public void testReducePath1(){
        Day11 day = new Day11();
        String[] path = "ne,ne,ne".split(",");
        assertEquals(3, day.reducePath(path));
    }
    @Test
    public void testReducePath2(){
        Day11 day = new Day11();
        String[] path = "ne,ne,sw,sw".split(",");
        assertEquals(0, day.reducePath(path));
    }
    @Test
    public void testReducePath3(){
        Day11 day = new Day11();
        String[] path = "ne,ne,s,s".split(",");
        assertEquals(2, day.reducePath(path));
    }
    @Test
    public void testReducePath4(){
        Day11 day = new Day11();
        String[] path = "se,sw,se,sw,sw".split(",");
        assertEquals(3, day.reducePath(path));
    }
    
}
