package com.ambertests.aoc.days;

import com.ambertests.aoc.common.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day14 extends Day {
    public Day14(){
        this.dayNum = 14;
    }

    ArrayList<Coordinate> getActiveSquares(String str){
        ArrayList<Coordinate> ones = new ArrayList<>();
        Day10 hasher = new Day10();
        for(int y = 0; y < 128; y++){
            String hash = hasher.knotHash(String.format("%s-%d", str, y));
            StringBuilder sb = new StringBuilder();
            for(char c:hash.toCharArray()){
                int dec = Integer.parseInt(Character.toString(c),16);
                String bin = String.format("%4s", Integer.toBinaryString(dec))
                                   .replace(' ', '0');
                sb.append(bin);
            }
            char[] binChars = sb.toString().toCharArray();
            for(int x = 0; x < binChars.length; x++){
                if(binChars[x] == '1'){
                    ones.add(new Coordinate(x, y));
                }
            }
        }
        return ones;
    }

    List<Coordinate> getRegion(Coordinate c, ArrayList<Coordinate> region, List<Coordinate> active){
        Coordinate[] neighbors = new Coordinate[]{
            new Coordinate(c.x+1, c.y),
            new Coordinate(c.x-1, c.y),
            new Coordinate(c.x, c.y+1),
            new Coordinate(c.x, c.y-1)
        };
        for(Coordinate n:neighbors){
            if(active.contains(n) && !region.contains(n)){
                region.add(n);
                getRegion(n, region, active);
            }
        }
        return region;
    }

    int getRegionCount(ArrayList<Coordinate> active){
        int regions = 0;
        while(active.size() > 0){
            Coordinate c = active.get(0);
            List<Coordinate> region = getRegion(active.get(0), 
                                                new ArrayList<Coordinate>(Arrays.asList(c)), 
                                                active);
            active.removeAll(region);
            regions += 1;
        }
        return regions;
    }

    @Override
    public void solve() {
        String str = "stpzcrnm";
        ArrayList<Coordinate> ones = getActiveSquares(str);
        this.solution1 = ones.size();
        this.solution2 = getRegionCount(ones);

    }

    public static void main(String[] args) {
        Day14 day = new Day14();
        day.solve();
        day.printSolutions();
    }
}
