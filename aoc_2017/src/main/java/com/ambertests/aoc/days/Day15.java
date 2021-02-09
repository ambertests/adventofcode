package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

public class Day15 extends Day {

    long A_FACTOR = 16807;
    long B_FACTOR = 48271;

    public Day15() {
        this.dayNum = 15;
    }

    int judge(long a, long b, int pairs) {
        int matches = 0;
        for (int i = 0; i < pairs; i++) {
            a = (a * A_FACTOR) % 2147483647;
            b = (b * B_FACTOR) % 2147483647;
            if ((a & 0xffff) == (b & 0xffff)) {
                matches += 1;
            }
        }
        return matches;
    }

    int judge2(long a, long b, int pairs) {
        int matches = 0;
        while (pairs > 0) {
            do {
                a = (a * A_FACTOR) % 2147483647;
            } while (a % 4 != 0);

            do {
                b = (b * B_FACTOR) % 2147483647;
            } while (b % 8 != 0);

            if ((a & 0xffff) == (b & 0xffff)) {
                matches += 1;
            }
            pairs -= 1;
        }
        return matches;
    }


    @Override
    public void solve() {
        /*
        Generator A starts with 289
        Generator B starts with 629
         */
        this.solution1 = judge(289, 629, 40000000);
        this.solution2 = judge2(289, 629, 5000000);
    }

    public static void main(String[] args) {
        Day15 day = new Day15();
        day.solve();
        day.printSolutions();
    }
}
