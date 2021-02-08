package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import com.google.common.primitives.Ints;

public class Day10 extends Day {
    public Day10(){
        this.dayNum = 10;
    }

    int[] shift(int[] arr, int offset){
        if(offset == 0){
            return arr;
        }
        int[] shifted = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            int idx = i + offset;
            if(idx >= arr.length){
                idx -= arr.length;
            }
            if(idx < 0){
                idx += arr.length;
            }
            shifted[idx] = arr[i];
        }
        return shifted;
    }

    int[] reverseSubArray(int[] arr, int start, int len){
        if(len <= 1){
            return arr;
        }
        int[] reversed = new int[arr.length];
        int offset = 0 - start;
        int[] shifted = shift(arr, offset);
        List<Integer> slice = Ints.asList(Arrays.copyOfRange(shifted, 0, len));
        Collections.reverse(slice);
        for(int i = 0; i < arr.length; i++){
            if(i < slice.size()){
                reversed[i] = slice.get(i);
            }else{
                reversed[i] = shifted[i];
            }
        }
        return shift(reversed, -(offset));
    }

    int[] processArray(int[] arr, int[] lengths, int loops){
        int start = 0;
        int skip = 0;
        for(int l = 0; l < loops; l++){
            for(int len:lengths){
                arr = reverseSubArray(arr, start, len);
                start += (len + skip);
                while(start >= arr.length){
                    start -= arr.length;
                }
                skip += 1;
            }
        }
        return arr;
    }

    int[] initializeArray(){
        int[] arr = new int[256];
        for(int i = 0; i < arr.length; i++){
            arr[i] = i;
        }
        return arr;
    }

    int[] getLengths(String str){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(char c:str.toCharArray()){
            list.add((int)c);
        }
        list.addAll(Ints.asList(17, 31, 73, 47, 23));
        return Ints.toArray(list);
    }

    String createHash(int[] arr){
        StringBuilder sb = new StringBuilder();
        int x = -1;
        for(int i = 0; i < arr.length; i++){
            if(i%16 == 0){
                if(i > 0){
                    String hex = Integer.toHexString(x);
                    if(hex.length() == 1){
                        sb.append("0");
                    }
                    sb.append(hex);
                }
                x = arr[i];
            }else{
                x ^= arr[i];
            }
        }
        String hex = Integer.toHexString(x);
        if(hex.length() == 1){
            sb.append("0");
        }
        sb.append(hex);
        return sb.toString();
    }

    public String knotHash(String str){
        int[] arr = initializeArray();
        int[] lengths = getLengths(str);
        int[] processed = processArray(arr, lengths, 64);
        return createHash(processed);
    }

    @Override
    public void solve() {
        int[] arr = initializeArray();
        int[] lengths = new int[]{31,2,85,1,80,109,35,63,98,255,0,13,105,254,128,33};
        int[] processed = processArray(arr, lengths, 1);
        this.solution1 = processed[0] * processed[1];

        String lenStr = "31,2,85,1,80,109,35,63,98,255,0,13,105,254,128,33";
        this.solution2 = knotHash(lenStr);
    }

    public static void main(String[] args) {
        Day10 day = new Day10();
        day.solve();
        day.printSolutions();        
    }
}
