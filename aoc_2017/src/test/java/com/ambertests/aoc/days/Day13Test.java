package com.ambertests.aoc.days;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class Day13Test {
    String[] input = new String[]{
            "0: 3",
            "1: 2",
            "4: 4",
            "6: 4"
    };

    @Test
    public void testParseInput() {
        Day13 day = new Day13();
        HashMap<Integer, Integer> firewall = day.parseInput(input);
        assertEquals(4, firewall.size());
        assertEquals(3, firewall.get(0).intValue());
        assertEquals(2, firewall.get(1).intValue());
        assertEquals(4, firewall.get(4).intValue());
        assertEquals(4, firewall.get(6).intValue());
    }

    @Test
    public void testGetScore() {
        Day13 day = new Day13();
        HashMap<Integer, Integer> firewall = day.parseInput(input);
        assertEquals(24, day.getScore(firewall, 0, false));
    }

    @Test
    public void testFindDelay() {
        Day13 day = new Day13();
        HashMap<Integer, Integer> firewall = day.parseInput(input);
        assertEquals(10, day.findDelay(firewall));
    }

}
