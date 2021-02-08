package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

public class Day09 extends Day {
    int garbageCount = 0;

    public Day09() {
        this.dayNum = 9;
    }

    String cleanString(String str) {
        garbageCount = 0;
        StringBuilder sb = new StringBuilder();
        boolean skipNext = false;
        boolean isGarbage = false;
        for (char c : str.toCharArray()) {
            if (skipNext) {
                skipNext = false;
                continue;
            }
            if (c == '!') {
                skipNext = true;
                continue;
            }
            if (c == '<' && !isGarbage) {
                isGarbage = true;
                continue;
            }
            if (c == '>' && isGarbage) {
                isGarbage = false;
                continue;
            }
            if (!isGarbage) {
                sb.append(c);
            } else {
                garbageCount += 1;
            }

        }
        return sb.toString();
    }

    int scoreString(String str) {
        int score = 0;
        int level = 0;
        for (char c : str.toCharArray()) {
            if (c == '{') {
                level += 1;
            }
            if (c == '}') {
                score += level;
                level -= 1;
            }
        }
        return score;
    }


    @Override
    public void solve() {
        this.solution1 = scoreString(cleanString(getInputString()));
        this.solution2 = garbageCount;
    }

    public static void main(String[] args) {
        Day09 day = new Day09();
        day.solve();
        day.printSolutions();
    }
}
