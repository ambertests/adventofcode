package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class Day18 extends Day {

    public static class Program {
        private final HashMap<String, Long> registers;
        private final String[] instructions;
        private final Queue<Long> inbox;
        private final Queue<Long> outbox;
        private final boolean soloMode;

        private int currentIndex;
        private boolean waiting;
        private boolean terminated;

        public Program(String[] instructions) {
            this(0, instructions, true);
        }

        public Program(long id, String[] instructions) {
            this(id, instructions, false);
        }

        private Program(long id, String[] instructions, boolean soloMode) {
            this.instructions = instructions;
            this.registers = new HashMap<>();
            this.registers.put("p", id);
            this.waiting = false;
            this.terminated = false;
            this.inbox = new ArrayDeque<>();
            this.outbox = new ArrayDeque<>();
            this.currentIndex = 0;
            this.soloMode = soloMode;
        }

        public void receive(long l) {
            this.inbox.add(l);
            this.waiting = false;
        }

        public boolean hasSendItem() {
            return !this.outbox.isEmpty();
        }

        public Long getNextSend() {
            return this.outbox.poll();
        }

        public boolean canRun() {
            return !(this.waiting || this.terminated);
        }

        private long getValue(String str) {
            if (Character.isLetter(str.charAt(0))) {
                return this.registers.getOrDefault(str, 0L);
            } else {
                return Long.parseLong(str);
            }
        }

        public void run() {
            while (canRun()) {
                if (this.currentIndex >= this.instructions.length) {
                    this.terminated = true;
                    continue;
                }
                String[] inst = instructions[this.currentIndex].split(" ");
                String cmd = inst[0];
                String x = inst[1];
                long y = inst.length == 3 ? getValue(inst[2]) : 0;
                if (Character.isLetter(x.charAt(0))) {
                    registers.putIfAbsent(x, 0L);
                }
                switch (cmd) {
                    case ("snd"):
                        this.outbox.add(getValue(x));
                        this.currentIndex += 1;
                        break;
                    case ("set"):
                        registers.put(x, y);
                        this.currentIndex += 1;
                        break;
                    case ("add"):
                        registers.put(x, registers.get(x) + y);
                        this.currentIndex += 1;
                        break;
                    case ("mul"):
                        registers.put(x, registers.get(x) * y);
                        this.currentIndex += 1;
                        break;
                    case ("mod"):
                        registers.put(x, registers.get(x) % y);
                        this.currentIndex += 1;
                        break;
                    case ("rcv"):
                        if (soloMode) {
                            if (registers.get(x) != 0) {
                                //break the loop
                                this.currentIndex = instructions.length;
                            } else {
                                this.currentIndex += 1;
                            }
                        } else {
                            if (!this.inbox.isEmpty()) {
                                registers.put(x, this.inbox.poll());
                                this.currentIndex += 1;
                            } else {
                                this.waiting = true;
                            }
                        }
                        break;
                    case ("jgz"):
                        if (getValue(x) > 0) {
                            this.currentIndex += y;
                        } else {
                            this.currentIndex += 1;
                        }
                }
            }
        }
    }

    public Day18() {
        this.dayNum = 18;
    }

    long part1(String[] instructions){
        long lastSent = 0;
        Program p = new Program(instructions);
        p.run();
        while (p.hasSendItem()) {
            lastSent = p.getNextSend();
        }
        return lastSent;
    }

    int part2(String[] instructions){
        Program p0 = new Program(0, instructions);
        Program p1 = new Program(1, instructions);
        int p1Sends = 0;
        while (p0.canRun() || p1.canRun()) {
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
        return p1Sends;
    }

    @Override
    public void solve() {
        String[] instructions = getInputStringArray();
        this.solution1 = part1(instructions);
        this.solution2 = part2(instructions);
    }

    public static void main(String[] args) {
        Day18 day = new Day18();
        day.solve();
        day.printSolutions();
    }
}
