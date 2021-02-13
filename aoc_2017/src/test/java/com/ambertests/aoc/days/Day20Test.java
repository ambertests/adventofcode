package com.ambertests.aoc.days;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Day20Test {
    @Test
    public void solvesPart1() {
        Day20 day = new Day20();
        day.particleList(day.getInputStringArray());
        assertEquals(258, day.slowestParticle);
    }

    @Test
    public void solvesPart2() {
        Day20 day = new Day20();
        ArrayList<int[]> particles = day.particleList(day.getInputStringArray());
        for (int i = 0; i < 40; i++) {
            day.update(particles);
            day.removeCollisions(particles);
        }
        assertEquals(707, particles.size());
    }
}
