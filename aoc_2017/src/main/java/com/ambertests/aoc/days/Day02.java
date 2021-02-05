package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;
import java.util.Arrays;

public class Day02 extends Day {
    String[] input;
    public Day02(){
        this.dayNum = 2;
        input = this.dayStrings();
    }
    @Override
    public void solve() {
        int sum1 = 0;
        int sum2 = 0;
        for (String s:input) {
            int min = Integer.MAX_VALUE;
            int max = 0;
            int[] arr = Arrays.stream(s.split("\t")).mapToInt(Integer::parseInt).toArray();
            for(int i:arr){
                if(i < min){
                    min = i;
                }
                if(i > max){
                    max = i;
                }
                for(int j:arr){
                    if(i == j){
                        continue;
                    }
                    if(i%j == 0){
                        sum2 += (i/j);
                    }
                }
            }
            sum1 += (max - min);
        }
        this.solution1 = sum1;
        this.solution2 = sum2;
    }

    public static void main(String[] args) {
        Day02 day2 = new Day02();
        day2.solve();
        day2.printSolutions();
    }
}
