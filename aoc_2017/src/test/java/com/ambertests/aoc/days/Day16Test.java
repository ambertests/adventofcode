package com.ambertests.aoc.days;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Day16Test {
    @Test
    public void testSpin1() {
        Day16 day = new Day16();
        char[] arr = "abcde".toCharArray();
        char[] expected = "cdeab".toCharArray();
        assertArrayEquals(expected, day.spin(arr, 3));
    }

    @Test
    public void testSpin2() {
        Day16 day = new Day16();
        char[] arr = "abcde".toCharArray();
        char[] expected = "eabcd".toCharArray();
        assertArrayEquals(expected, day.spin(arr, 1));
    }

    @Test
    public void testExchange() {
        Day16 day = new Day16();
        char[] arr = "eabcd".toCharArray();
        char[] expected = "eabdc".toCharArray();
        assertArrayEquals(expected, day.exchange(arr, 3, 4));
    }

    @Test
    public void testPartner() {
        Day16 day = new Day16();
        char[] arr = "eabdc".toCharArray();
        char[] expected = "baedc".toCharArray();
        assertArrayEquals(expected, day.partner(arr, 'e', 'b'));
    }

    @Test
    public void testDance() {
        Day16 day = new Day16();
        char[] dancers = "abcde".toCharArray();
        ArrayList<int[]> steps = day.parseSteps(new String[]{"s1", "x3/4", "pe/b"});
        String expected = "baedc";
        assertEquals(expected, new String(day.dance(dancers, steps)));
    }

    @Test
    public void solvesPart1() {
        Day16 day = new Day16();
        String dancers = "abcdefghijklmnop";
        ArrayList<int[]> steps = day.parseSteps(day.getInputString().split(","));
        assertEquals("ociedpjbmfnkhlga", new String(day.dance(dancers.toCharArray(), steps)));
    }

    @Test
    public void solvesPart2() {
        Day16 day = new Day16();
        String dancers = "abcdefghijklmnop";
        ArrayList<int[]> steps = day.parseSteps(day.getInputString().split(","));
        assertEquals("gnflbkojhicpmead", day.repeatDance(dancers, steps, 1000000000));
    }
}
