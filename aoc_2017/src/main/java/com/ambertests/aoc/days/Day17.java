package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.ArrayList;

public class Day17 extends Day {
    public Day17() {
        this.dayNum = 17;
    }

    int spinLock(int target, int part){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        int idx = 0;
        int val = 1;
        int part2 = 0;
        while(val <= target){
            idx += 316;
            while(idx >= val){
                idx -= val;
            }
            idx += 1;
            if(part == 1) {
                list.add(idx, val);
            }else{
                // For part 2 we only need to know the eventual
                // value of index(1), so no need to waste time
                // actually updating the list
                if(idx == 1){
                    part2 = val;
                }
            }
            val += 1;
        }
        return part == 1 ? list.get(idx + 1) : part2;
    }

    @Override
    public void solve() {
        this.solution1 = spinLock(2017, 1);
        this.solution2 = spinLock(50000000, 2);
    }

    public static void main(String[] args) {
        Day17 day = new Day17();
        day.solve();
        day.printSolutions();
    }
}
