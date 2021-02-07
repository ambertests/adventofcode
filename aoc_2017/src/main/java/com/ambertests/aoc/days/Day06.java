package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;
import java.util.ArrayList;
import java.util.Arrays;

public class Day06 extends Day {
    public Day06(){
        this.dayNum = 6;
    }

    void redistribute(int[] arr){
        int amt = 0;
        int index = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > amt){
                amt = arr[i];
                index = i;
            }
        }
        arr[index] = 0;
        while(amt > 0){
            index += 1;
            if(index == arr.length){
                index = 0;
            }
            arr[index] += 1;
            amt -= 1;
        }
    }

    int findLoop(int[] arr){
        ArrayList<String> arrs = new ArrayList<String>();
        int loops = 0;
        while(!arrs.contains(Arrays.toString(arr))){
            arrs.add(Arrays.toString(arr));
            redistribute(arr);
            loops += 1;
        }
        return loops;
    }

    @Override
    public void solve() {
        int[] nums = Arrays.stream(day().split("\t")).mapToInt(Integer::parseInt).toArray();
        this.solution1 = findLoop(nums);
        this.solution2 = findLoop(nums);
    }

    public static void main(String[] args) {
        Day06 day = new Day06();
        day.solve();
        day.printSolutions();
    }
}
