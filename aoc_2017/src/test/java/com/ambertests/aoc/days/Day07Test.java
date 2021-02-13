package com.ambertests.aoc.days;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day07Test {
    String[] input = new String[]{
            "pbga (66)",
            "xhth (57)",
            "ebii (61)",
            "havc (66)",
            "ktlj (57)",
            "fwft (72) -> ktlj, cntj, xhth",
            "qoyq (66)",
            "padx (45) -> pbga, havc, qoyq",
            "tknk (41) -> ugml, padx, fwft",
            "jptl (61)",
            "ugml (68) -> gyxo, ebii, jptl",
            "gyxo (61)",
            "cntj (57)"
    };

    @Test
    public void testParseInputNameToWeight() {
        Day07 day = new Day07();
        day.parseInput(input);
        assertNotNull(day.nameToWeight);
        assertEquals(57, day.nameToWeight.get("xhth").intValue());
    }

    @Test
    public void testParseInputChildToParent() {
        Day07 day = new Day07();
        day.parseInput(input);
        assertNotNull(day.childToParent);
        assertEquals("padx", day.childToParent.get("pbga"));
    }

    @Test
    public void testParseInputParentToChildren() {
        Day07 day = new Day07();
        day.parseInput(input);
        assertNotNull(day.parentToChildren);
        assertArrayEquals(new String[]{"gyxo", "ebii", "jptl"},
                day.parentToChildren.get("ugml"));
    }

    @Test
    public void testGetBottom() {
        Day07 day = new Day07();
        day.parseInput(input);
        String bottom = day.getBottom();
        assertEquals("tknk", bottom);
    }

    @Test
    public void testFindBadWeight() {
        Day07 day = new Day07();
        day.parseInput(input);
        int newWeight = day.findBadWeight();
        assertEquals(60, newWeight);

    }

    @Test
    public void solvesPart1() {
        Day07 day = new Day07();
        day.parseInput(day.getInputStringArray());
        assertEquals("airlri", day.getBottom());
    }

    @Test
    public void solvesPart2() {
        Day07 day = new Day07();
        day.parseInput(day.getInputStringArray());
        assertEquals(1206, day.findBadWeight());
    }

}
