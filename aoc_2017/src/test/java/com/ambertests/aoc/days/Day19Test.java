package com.ambertests.aoc.days;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day19Test {
    char[][] path = new char[][]{
            "     |          ".toCharArray(),
            "     |  +--+    ".toCharArray(),
            "     A  |  C    ".toCharArray(),
            " F---|----E|--+ ".toCharArray(),
            "     |  |  |  D ".toCharArray(),
            "     +B-+  +--+ ".toCharArray()
    };
    @Test
    public void testFollowPath(){
        Day19 day = new Day19();
        assertEquals("ABCDEF", day.followPath(path));
        assertEquals(38, day.stepCount);
    }
}
