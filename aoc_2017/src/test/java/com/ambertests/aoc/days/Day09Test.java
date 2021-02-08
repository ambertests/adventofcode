package com.ambertests.aoc.days;

import org.junit.Test;
import static org.junit.Assert.*;

public class Day09Test {
    /*
    <>, 0 characters.
<random characters>, 17 characters.
<<<<>, 3 characters.
<{!>}>, 2 characters.
<!!>, 0 characters.
<!!!>>, 0 characters.
<{o"i!a,<{i<a>, 10 characters.
    */
    @Test
    public void testCleanString1(){
        Day09 day = new Day09();
        String test = "<>";
        assertTrue(day.cleanString(test).isEmpty());
        assertEquals(0, day.garbageCount);
    }
    @Test
    public void testCleanString2(){
        Day09 day = new Day09();
        String test = "<random characters>";
        assertTrue(day.cleanString(test).isEmpty());
        assertEquals(17, day.garbageCount);
    }
    @Test
    public void testCleanString3(){
        Day09 day = new Day09();
        String test = "<<<<>";
        assertTrue(day.cleanString(test).isEmpty());
        assertEquals(3, day.garbageCount);
    }
    @Test
    public void testCleanString4(){
        Day09 day = new Day09();
        String test = "<{!>}>";
        assertTrue(day.cleanString(test).isEmpty());
        assertEquals(2, day.garbageCount);
    }
    @Test
    public void testCleanString5(){
        Day09 day = new Day09();
        String test = "<!!>";
        assertTrue(day.cleanString(test).isEmpty());
        assertEquals(0, day.garbageCount);
    }
    @Test
    public void testCleanString6(){
        Day09 day = new Day09();
        String test = "<!!!>>";
        assertTrue(day.cleanString(test).isEmpty());
        assertEquals(0, day.garbageCount);
    }
    @Test
    public void testCleanString7(){
        Day09 day = new Day09();
        String test = "<{o\"i!a,<{i<a>";
        assertTrue(day.cleanString(test).isEmpty());
        assertEquals(10, day.garbageCount);
    }
    @Test
    public void testCleanString8(){
        Day09 day = new Day09();
        String test = "{<a>,<a>,<a>,<a>}";
        assertEquals("{,,,}",day.cleanString(test));
    }
    @Test
    public void testCleanString9(){
        Day09 day = new Day09();
        String test = "{{<a>},{<a>},{<a>},{<a>}}";
        assertEquals("{{},{},{},{}}",day.cleanString(test));
    }
    @Test
    public void testCleanString10(){
        Day09 day = new Day09();
        String test = "{{<!>},{<!>},{<!>},{<a>}}";
        assertEquals("{{}}",day.cleanString(test));
    }
    @Test
    public void testCleanString11(){
        Day09 day = new Day09();
        String test = "{{<ab>},{<ab>},{<ab>},{<ab>}}";
        assertEquals("{{},{},{},{}}",day.cleanString(test));
    }
    @Test
    public void testCleanString12(){
        Day09 day = new Day09();
        String test = "{{<!!>},{<!!>},{<!!>},{<!!>}}";
        assertEquals("{{},{},{},{}}",day.cleanString(test));
    }
    @Test
    public void testCleanString13(){
        Day09 day = new Day09();
        String test = "{{<a!>},{<a!>},{<a!>},{<ab>}}";
        assertEquals("{{}}",day.cleanString(test));
    }

    @Test
    public void testScoreString1(){
        Day09 day = new Day09();
        String test = day.cleanString("{}");
        assertEquals(1, day.scoreString(test));
    }
    @Test
    public void testScoreString2(){
        Day09 day = new Day09();
        String test = day.cleanString("{{{}}}");
        assertEquals(6, day.scoreString(test));
    }
    @Test
    public void testScoreString3(){
        Day09 day = new Day09();
        String test = day.cleanString("{{},{}}");
        assertEquals(5, day.scoreString(test));
    }
    @Test
    public void testScoreString4(){
        Day09 day = new Day09();
        String test = day.cleanString("{{{},{},{{}}}}");
        assertEquals(16, day.scoreString(test));
    }
    @Test
    public void testScoreString5(){
        Day09 day = new Day09();
        String test = day.cleanString("{<a>,<a>,<a>,<a>}");
        assertEquals(1, day.scoreString(test));
    }
    @Test
    public void testScoreString6(){
        Day09 day = new Day09();
        String test = day.cleanString("{{<ab>},{<ab>},{<ab>},{<ab>}}");
        assertEquals(9, day.scoreString(test));
    }
    @Test
    public void testScoreString7(){
        Day09 day = new Day09();
        String test = day.cleanString("{{<!!>},{<!!>},{<!!>},{<!!>}}");
        assertEquals(9, day.scoreString(test));
    }
    @Test
    public void testScoreString8(){
        Day09 day = new Day09();
        String test = day.cleanString("{{<a!>},{<a!>},{<a!>},{<ab>}}");
        assertEquals(3, day.scoreString(test));
    }
    
}
