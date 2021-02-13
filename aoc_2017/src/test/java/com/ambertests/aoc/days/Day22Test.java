package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Coordinate;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Day22Test {
    char[][] grid = new char[][]{
            "..#".toCharArray(),
            "#..".toCharArray(),
            "...".toCharArray()
    };

    @Test
    public void testProcessGrid() {
        Day22 day = new Day22();
        HashSet<Coordinate> infected = day.processGrid(grid);
        assertEquals(2, infected.size());
        assertTrue(infected.contains(new Coordinate(2, 0)));
        assertTrue(infected.contains(new Coordinate(0, 1)));
    }

    @Test
    public void testInfect7() {
        Day22 day = new Day22();
        HashSet<Coordinate> infected = day.processGrid(grid);
        Coordinate virus = new Coordinate(1, 1);
        assertEquals(5, day.infect(virus, infected, 7));
    }

    @Test
    public void testInfect70() {
        Day22 day = new Day22();
        HashSet<Coordinate> infected = day.processGrid(grid);
        Coordinate virus = new Coordinate(1, 1);
        assertEquals(41, day.infect(virus, infected, 70));
    }

    @Test
    public void testInfect10k() {
        Day22 day = new Day22();
        HashSet<Coordinate> infected = day.processGrid(grid);
        Coordinate virus = new Coordinate(1, 1);
        assertEquals(5587, day.infect(virus, infected, 10000));
    }

    @Test
    public void testEvolvedInfect100() {
        Day22 day = new Day22();
        HashSet<Coordinate> infected = day.processGrid(grid);
        Coordinate virus = new Coordinate(1, 1);
        assertEquals(26, day.evolvedInfect(virus, infected, 100));
    }

    @Test
    public void testEvolvedInfect10M() {
        Day22 day = new Day22();
        HashSet<Coordinate> infected = day.processGrid(grid);
        Coordinate virus = new Coordinate(1, 1);
        assertEquals(2511944, day.evolvedInfect(virus, infected, 10000000));
    }

    @Test
    public void solvesPart1() {
        Day22 day = new Day22();
        char[][] grid = day.getInputGrid();
        int middle = grid.length / 2;
        Coordinate virus = new Coordinate(middle, middle);
        HashSet<Coordinate> infected = day.processGrid(grid);
        assertEquals(5322, day.infect(virus, infected, 10000));
    }

    @Test
    public void solvesPart2() {
        Day22 day = new Day22();
        char[][] grid = day.getInputGrid();
        int middle = grid.length / 2;
        Coordinate virus = new Coordinate(middle, middle);
        HashSet<Coordinate> infected = day.processGrid(grid);
        assertEquals(2512079, day.evolvedInfect(virus, infected, 10000000));
    }

}
