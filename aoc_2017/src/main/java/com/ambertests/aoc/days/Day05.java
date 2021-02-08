package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

public class Day05 extends Day {
    public Day05() {
        this.dayNum = 5;
    }

    private int escapeArray(int[] arr, int part) {
        int index = 0;
        int moves = 0;
        while (index < arr.length) {
            moves += 1;
            int val = arr[index];
            if (part == 1 || val < 3) {
                arr[index] += 1;
            } else {
                arr[index] -= 1;
            }
            index += val;
        }
        return moves;
    }

    @Override
    public void solve() {
        this.solution1 = escapeArray(getInputInts(), 1); //358309
        this.solution2 = escapeArray(getInputInts(), 2); //28178177
    }

    public static void main(String[] args) {
        Day05 day5 = new Day05();
        day5.solve();
        day5.printSolutions();
    }
}
