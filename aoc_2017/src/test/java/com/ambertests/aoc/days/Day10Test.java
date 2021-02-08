package com.ambertests.aoc.days;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Day10Test {
    @Test
    public void testShiftArrayRight() {
        Day10 day = new Day10();
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] shifted = day.shift(arr, 1);
        int[] expected = new int[]{5, 1, 2, 3, 4};
        assertArrayEquals(expected, shifted);
    }

    @Test
    public void testShiftArrayLeft() {
        Day10 day = new Day10();
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] shifted = day.shift(arr, -1);
        int[] expected = new int[]{2, 3, 4, 5, 1};
        assertArrayEquals(expected, shifted);
    }

    @Test
    public void testReverseSubArray1() {
        Day10 day = new Day10();
        int[] arr = new int[]{0, 1, 2, 3, 4};
        int[] reversed = day.reverseSubArray(arr, 0, 3);
        int[] expected = new int[]{2, 1, 0, 3, 4};
        assertArrayEquals(expected, reversed);
    }

    @Test
    public void testReverseSubArray2() {
        Day10 day = new Day10();
        int[] arr = new int[]{2, 1, 0, 3, 4};
        int[] reversed = day.reverseSubArray(arr, 3, 4);
        int[] expected = new int[]{4, 3, 0, 1, 2};
        assertArrayEquals(expected, reversed);
    }

    @Test
    public void testReverseSubArray3() {
        Day10 day = new Day10();
        int[] arr = new int[]{4, 3, 0, 1, 2};
        int[] reversed = day.reverseSubArray(arr, 4, 1);
        int[] expected = new int[]{4, 3, 0, 1, 2};
        assertArrayEquals(expected, reversed);
    }

    @Test
    public void testReverseSubArray4() {
        Day10 day = new Day10();
        int[] arr = new int[]{4, 3, 0, 1, 2};
        int[] reversed = day.reverseSubArray(arr, 1, 5);
        int[] expected = new int[]{3, 4, 2, 1, 0};
        assertArrayEquals(expected, reversed);
    }

    @Test
    public void testProcessArray() {
        Day10 day = new Day10();
        int[] arr = new int[]{0, 1, 2, 3, 4};
        int[] lengths = new int[]{3, 4, 1, 5};
        int[] expected = new int[]{3, 4, 2, 1, 0};
        assertArrayEquals(expected, day.processArray(arr, lengths, 1));
    }

    @Test
    public void testGetLengths() {
        Day10 day = new Day10();
        String str = "1,2,3";
        int[] expected = new int[]{49, 44, 50, 44, 51, 17, 31, 73, 47, 23};
        assertArrayEquals(expected, day.getLengths(str));
    }

    @Test
    public void testCreateHash() {
        Day10 day = new Day10();
        int[] arr = new int[]{65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22};
        assertEquals("40", day.createHash(arr));
    }

    @Test
    public void testKnotHash1() {
        Day10 day = new Day10();
        String str = "";
        String expected = "a2582a3a0e66e6e86e3812dcb672a272";
        assertEquals(expected, day.knotHash(str));
    }

    @Test
    public void testKnotHash2() {
        Day10 day = new Day10();
        String str = "AoC 2017";
        String expected = "33efeb34ea91902bb2f59c9920caa6cd";
        assertEquals(expected, day.knotHash(str));
    }

    @Test
    public void testKnotHash3() {
        Day10 day = new Day10();
        String str = "1,2,3";
        String expected = "3efbe78a8d82f29979031a4aa0b16a9d";
        assertEquals(expected, day.knotHash(str));
    }

    @Test
    public void testKnotHash4() {
        Day10 day = new Day10();
        String str = "1,2,4";
        String expected = "63960835bcdc130f0b66d7ff4f6a5a8e";
        assertEquals(expected, day.knotHash(str));
    }
}
