package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Coordinate;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class Day14Test {
    @Test
    public void testGetActiveSquares(){
        Day14 day = new Day14();
        assertEquals(8108, day.getActiveSquares("flqrgnkx").size());
    }
    @Test
    public void testGetRegionCount(){
        Day14 day = new Day14();
        ArrayList<Coordinate> active = day.getActiveSquares("flqrgnkx");
        assertEquals(1242, day.getRegionCount(active));
    }
    
}
