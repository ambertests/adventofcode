package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

public class Day01 extends Day {
    String input;
    public Day01(){
        this.dayNum = 1;
        input = this.getInputString();
    }

    @Override
    public void solve() {
        int sum1 = 0;
        int sum2 = 0;
        char[] chars = input.toCharArray();
        int half = chars.length/2;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int cInt = (int)c - 48; //48 is the ascii value of 0
            char next = i == chars.length - 1 ? chars[0]:chars[i+1];
            if(c == next){
                sum1 += cInt;
            }
            int j = i + half < chars.length ? i+half : (i+half)-chars.length;
            if(c == chars[j]){
                sum2 += cInt;
            }
        }
        this.solution1 = sum1;
        this.solution2 = sum2;
    }

    public static void main(String[] args) {
        Day01 day1 = new Day01();
        day1.solve();
        day1.printSolutions();
    }
}
