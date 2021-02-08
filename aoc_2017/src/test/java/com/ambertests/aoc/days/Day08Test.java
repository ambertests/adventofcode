package com.ambertests.aoc.days;

import org.junit.Test;

import static org.junit.Assert.*;

public class Day08Test {
    @Test
    public void testGetMaxRegister() {
        String[] input = new String[]{
                "b inc 5 if a > 1",
                "a inc 1 if b < 5",
                "c dec -10 if a >= 1",
                "c inc -20 if c == 10"
        };
        Day08 day = new Day08();
        assertEquals(1, day.getMaxRegister(input));
    }

    @Test
    public void testParseModIncPos() {
        Day08 day = new Day08();
        day.registers.put("a", 0);
        String mod = "a inc 1";
        day.parseMod(mod);
        assertEquals(1, day.registers.get("a").intValue());
    }

    @Test
    public void testParseModIncNeg() {
        Day08 day = new Day08();
        day.registers.put("a", 0);
        String mod = "a inc -1";
        day.parseMod(mod);
        assertEquals(-1, day.registers.get("a").intValue());
    }

    @Test
    public void testParseModDecPos() {
        Day08 day = new Day08();
        day.registers.put("a", 0);
        String mod = "a dec 1";
        day.parseMod(mod);
        assertEquals(-1, day.registers.get("a").intValue());
    }

    @Test
    public void testParseModDecNeg() {
        Day08 day = new Day08();
        day.registers.put("a", 0);
        String mod = "a dec -1";
        day.parseMod(mod);
        assertEquals(1, day.registers.get("a").intValue());
    }

    @Test
    public void testParseConditionGTFalse() {
        Day08 day = new Day08();
        day.registers.put("a", 0);
        String cond = "a > 0";
        assertFalse(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionGTTrue() {
        Day08 day = new Day08();
        day.registers.put("a", 0);
        String cond = "a > -1";
        assertTrue(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionLTFalse() {
        Day08 day = new Day08();
        String cond = "a < 1";
        day.registers.put("a", 10);
        assertFalse(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionLTTrue() {
        Day08 day = new Day08();
        String cond = "a < 1";
        day.registers.put("a", -10);
        assertTrue(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionGTEFalse() {
        Day08 day = new Day08();
        String cond = "a >= 1";
        day.registers.put("a", -10);
        assertFalse(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionGTETrueE() {
        Day08 day = new Day08();
        String cond = "a >= 1";
        day.registers.put("a", 1);
        assertTrue(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionGTETrueG() {
        Day08 day = new Day08();
        String cond = "a >= 1";
        day.registers.put("a", 2);
        assertTrue(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionLTEFalse() {
        Day08 day = new Day08();
        String cond = "a <= 1";
        day.registers.put("a", 2);
        assertFalse(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionLTETrueL() {
        Day08 day = new Day08();
        String cond = "a <= 1";
        day.registers.put("a", -1);
        assertTrue(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionLTETrueE() {
        Day08 day = new Day08();
        String cond = "a <= 1";
        day.registers.put("a", 1);
        assertTrue(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionEqFalse() {
        Day08 day = new Day08();
        day.registers.put("a", 0);
        String cond = "a == 1";
        assertFalse(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionEqTrue() {
        Day08 day = new Day08();
        day.registers.put("a", 0);
        String cond = "a == 0";
        assertTrue(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionNEFalse() {
        Day08 day = new Day08();
        day.registers.put("a", 0);
        String cond = "a != 0";
        assertFalse(day.parseCondition(cond));
    }

    @Test
    public void testParseConditionNETrue() {
        Day08 day = new Day08();
        day.registers.put("a", 0);
        String cond = "a != 1";
        assertTrue(day.parseCondition(cond));
    }
}
