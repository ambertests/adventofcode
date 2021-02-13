package com.ambertests.aoc.days;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class Day21Test {
    char[][] grid = new char[][]{
            ".#.".toCharArray(),
            "..#".toCharArray(),
            "###".toCharArray()
    };

    @Test
    public void testFlip() {
        char[][] expected = new char[][]{
                ".#.".toCharArray(),
                "#..".toCharArray(),
                "###".toCharArray()
        };
        Day21 day = new Day21();
        assertArrayEquals(expected, day.flip(grid));
    }

    @Test
    public void testRotate() {
        char[][] expected = new char[][]{
                ".##".toCharArray(),
                "#.#".toCharArray(),
                "..#".toCharArray()
        };
        Day21 day = new Day21();
        assertArrayEquals(expected, day.rotate(grid));
    }

    @Test
    public void testGridToRule() {
        String expected = ".#./..#/###";
        Day21 day = new Day21();
        assertEquals(expected, day.gridToRule(grid));
    }

    @Test
    public void testMatchesRule() {
        String rule = ".#./..#/###";
        Day21 day = new Day21();
        assertTrue(day.matchesRule(rule, grid));
    }

    @Test
    public void testMatchesRuleFlipped() {
        String rule = ".#./..#/###";
        Day21 day = new Day21();
        assertTrue(day.matchesRule(rule, day.flip(grid)));
    }

    @Test
    public void testMatchesRuleRotated() {
        String rule = ".#./..#/###";
        Day21 day = new Day21();
        assertTrue(day.matchesRule(rule, day.rotate(day.rotate(grid))));
    }

    @Test
    public void testMatchesRuleRotatedFlipped() {
        String rule = ".#./..#/###";
        Day21 day = new Day21();
        assertTrue(day.matchesRule(rule, day.rotate(day.flip(day.rotate(day.rotate(grid))))));
    }

    @Test
    public void testMatchesRule_DiffHashCount() {
        String rule = ".#./..#/#.#";
        Day21 day = new Day21();
        assertFalse(day.matchesRule(rule, grid));
    }

    @Test
    public void testMatchesRule_Mismatch() {
        String rule = ".#./.#./###";
        Day21 day = new Day21();
        assertFalse(day.matchesRule(rule, grid));
    }

    @Test
    public void testMatchesRule_WrongSize() {
        String rule = ".#/##";
        Day21 day = new Day21();
        assertFalse(day.matchesRule(rule, grid));
    }

    @Test
    public void testApplyRules() {
        HashMap<String, String> ruleBook = new HashMap<>();
        ruleBook.put("../.#", "##./#../...");
        ruleBook.put(".#./..#/###", "#..#/..../..../#..#");
        Day21 day = new Day21();
        char[][] updated = day.applyRules(grid, ruleBook);
        assertTrue(day.matchesRule("#..#/..../..../#..#", updated));
    }

    @Test
    public void testApplyRulesWithSplit() {
        HashMap<String, String> ruleBook = new HashMap<>();
        ruleBook.put("../.#", "##./#../...");
        ruleBook.put(".#./..#/###", "#..#/..../..../#..#");
        Day21 day = new Day21();
        char[][] grid = day.enhancementToGrid("#..#/..../..../#..#");
        char[][] updated = day.applyRules(grid, ruleBook);
        assertEquals(6, updated.length);
        assertEquals(12, day.countHashes(updated));

    }

    @Test
    public void solvesPart1() {
        Day21 day = new Day21();
        HashMap<String, String> rules = day.createRuleBook(day.getInputStringArray());
        assertEquals(150, day.transformGrid(day.startingGrid.clone(), rules, 5));
    }

    @Test
    public void solvesPart2() {
        Day21 day = new Day21();
        HashMap<String, String> rules = day.createRuleBook(day.getInputStringArray());
        assertEquals(2606275, day.transformGrid(day.startingGrid.clone(), rules, 18));
    }
}
