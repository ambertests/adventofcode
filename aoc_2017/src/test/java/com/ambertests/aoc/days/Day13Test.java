package com.ambertests.aoc.days;

import org.junit.Test;

import static com.ambertests.aoc.days.Day13.Scanner;
import static org.junit.Assert.*;

public class Day13Test {
    String[] input = new String[]{
            "0: 3",
            "1: 2",
            "4: 4",
            "6: 4"
    };

    @Test
    public void testScannerMove(){
        Scanner s = new Scanner(3);
        s.move();
        assertEquals(1, s.depth);
    }
    @Test
    public void testScannerMoveUp(){
        Scanner s = new Scanner(3);
        s.depth = 1;
        s.increaseDepth = false;
        s.move();
        assertEquals(0, s.depth);
    }
    @Test
    public void testScannerMoveBounceBottom(){
        Scanner s = new Scanner(3);
        s.depth = 2;
        s.move();
        assertEquals(1, s.depth);
        assertFalse(s.increaseDepth);
    }
    @Test
    public void testScannerMoveBounceTop(){
        Scanner s = new Scanner(3);
        s.increaseDepth = false;
        s.move();
        assertEquals(1, s.depth);
        assertTrue(s.increaseDepth);
    }
    @Test
    public void testGetMaxLayer() {
        Day13 day = new Day13();
        assertEquals(6, day.getMaxLayer(input));
    }

    @Test
    public void testInitFirewall(){
        Day13 day = new Day13();
        Scanner[] firewall = day.initFirewall(input);
        assertEquals(7, firewall.length);
        assertEquals(3, firewall[0].range);
        assertEquals(2, firewall[1].range);
        assertEquals(0, firewall[2].range);
        assertEquals(0, firewall[3].range);
        assertEquals(4, firewall[4].range);
        assertEquals(0, firewall[5].range);
        assertEquals(4, firewall[6].range);
    }

    @Test
    public void testTraverseFirewall(){
        Day13 day = new Day13();
        Scanner[] firewall = day.initFirewall(input);
        assertEquals(24, day.traverseFirewall(firewall));
    }

    @Test
    public void testTraverseFirewallWithDelay(){
        Day13 day = new Day13();
        Scanner[] firewall = day.initFirewall(input);
        assertEquals(0, day.traverseFirewall(firewall, 10, true));
    }

    @Test
    public void testFindDelay(){
        Day13 day = new Day13();
        Scanner[] firewall = day.initFirewall(input);
        assertEquals(10, day.findDelay(firewall));
    }

}
