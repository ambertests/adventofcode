package com.ambertests.aoc.days;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class Day18Test {
    @Test
    public void testRunProgram(){
        String[] program = {
            "set a 1",
            "add a 2",
            "mul a a",
            "mod a 5",
            "snd a",
            "set a 0",
            "rcv a",
            "jgz a -1",
            "set a 1",
            "jgz a -2"
        };
        Day18 day = new Day18();
        assertEquals(4, day.runProgram(program));
    }
    
}
