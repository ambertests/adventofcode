package com.ambertests.aoc.days;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day25Test {
    @Test
    public void testTuring() {
        Day25.Turing T = new Day25.Turing();
        T.addRule('A', new Day25.TuringRule(1, 1, 'B', 0, -1, 'B'));
        T.addRule('B', new Day25.TuringRule(1, -1, 'A', 1, 1, 'A'));
        for (int i = 0; i < 6; i++) {
            T.step();
        }
        assertEquals(3, T.getOnesCount());
    }

    @Test
    public void solvesPart1() {
        Day25 day = new Day25();
        day.solve();
        assertEquals(3145, day.solution1);
    }


}
