package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

public class Day01 extends Day {
    String input;
    public Day01(){
        this.dayNum = 1;
        input = this.day();
    }

    @Override
    public void solvePart1() {
        int sum = 0;
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            char next = i == chars.length - 1 ? chars[0]:chars[i+1];
            if(c == next){
                sum += (int)c - 48; //48 is the ascii value of 0
            }
        }
        this.solution1 = sum;

    }

    @Override
    public void solvePart2() {
        int sum = 0;
        char[] chars = input.toCharArray();
        int half = chars.length/2;
        for (int i = 0; i < chars.length; i++) {
            int j = i + half < chars.length ? i+half : (i+half)-chars.length;
            if(chars[i] == chars[j]){
                sum += (int)chars[i] - 48;
            }
        }
        this.solution2 = sum;
    }

    public static void main(String[] args) {
        Day01 day1 = new Day01();
        day1.solvePart1();
        day1.solvePart2();
        day1.printSolutions();
    }
}
