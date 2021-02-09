package com.ambertests.aoc.days;

import com.ambertests.aoc.days.Day18.Program;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day18Test {

    @Test
    public void testRunProgram() {
        String[] instructions = {
                "set a 1",
                "add a 2",
                "mul a a",
                "mod a 5",
                "snd a",
                "set a 0",
                "rcv a",
                "jgz a -1",
                "set a 1",
                "jgz a -2"
        };
        Program p = new Program(instructions);
        long val = 0;
        p.run();
        while (p.hasSendItem()) {
            val = p.getNextSend();
        }
        assertEquals(4, val);
    }

    @Test
    public void testRunDualPrograms() {
        String[] instructions = new String[]{
                "snd 1",
                "snd 2",
                "snd p",
                "rcv a",
                "rcv b",
                "rcv c",
                "rcv d"
        };
        Program p0 = new Program(0, instructions);
        Program p1 = new Program(1, instructions);
        int p1Sends = 0;
        while (p0.canRun() && p1.canRun()) {
            p0.run();
            while (p0.hasSendItem()) {
                p1.receive(p0.getNextSend());
            }
            p1.run();
            while (p1.hasSendItem()) {
                p0.receive(p1.getNextSend());
                p1Sends += 1;
            }
        }
        assertEquals(3, p1Sends);
    }

}
