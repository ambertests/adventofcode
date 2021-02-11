package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.HashMap;
import java.util.HashSet;


public class Day25 extends Day {

    static class TuringRule {
        int zeroWrite;
        int zeroMove;
        char zeroNext;
        int oneWrite;
        int oneMove;
        char oneNext;

        TuringRule(int zeroWrite, int zeroMove, char zeroNext,
                   int oneWrite, int oneMove, char oneNext) {
            this.zeroWrite = zeroWrite;
            this.zeroMove = zeroMove;
            this.zeroNext = zeroNext;
            this.oneWrite = oneWrite;
            this.oneMove = oneMove;
            this.oneNext = oneNext;
        }

    }

    static class Turing {
        private final HashSet<Integer> ones = new HashSet<>();
        private final HashMap<Character, TuringRule> rules = new HashMap<>();

        private Integer pos = 0;
        private Character state = 'A';

        public void addRule(char state, TuringRule rule) {
            rules.put(state, rule);
        }

        public int getOnesCount() {
            return this.ones.size();
        }

        public void step() {
            TuringRule rule = rules.get(state);
            if (!ones.contains(pos)) {
                if (rule.zeroWrite == 1) {
                    ones.add(pos);
                }
                pos += rule.zeroMove;
                state = rule.zeroNext;
            } else {
                if (rule.oneWrite == 0) {
                    ones.remove(pos);
                }
                pos += rule.oneMove;
                state = rule.oneNext;
            }
        }
    }

    public Day25() {
        this.dayNum = 25;
    }

    @Override
    public void solve() {
        Turing T = new Turing();
        T.addRule('A', new TuringRule(1, 1, 'B', 0, 1, 'F'));
        T.addRule('B', new TuringRule(0, -1, 'B', 1, -1, 'C'));
        T.addRule('C', new TuringRule(1, -1, 'D', 0, 1, 'C'));
        T.addRule('D', new TuringRule(1, -1, 'E', 1, 1, 'A'));
        T.addRule('E', new TuringRule(1, -1, 'F', 0, -1, 'D'));
        T.addRule('F', new TuringRule(1, 1, 'A', 0, -1, 'E'));
        for (int i = 0; i < 12964419; i++) {
            T.step();
        }
        this.solution1 = T.getOnesCount();
        this.solution2 = "Advent of Code 2017 Completed!!!";
    }

    public static void main(String[] args) {
        Day25 day = new Day25();
        day.solve();
        day.printSolutions();
    }
}
