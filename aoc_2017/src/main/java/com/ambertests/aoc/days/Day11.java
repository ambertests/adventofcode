package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;
import com.google.common.primitives.Ints;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class Day11 extends Day {
    public Day11(){
        this.dayNum = 11;
    }

    int reducePath(String[] path){
        HashMap<String, Integer> dirs = new HashMap<>();
        for(String d:new String[]{"n","s","ne","nw","se","sw"}){
            dirs.put(d, 0);
        }

        for(String p:path){
            dirs.put(p, dirs.get(p) + 1);
        }

        //remove paired opposites
        String[][] pairs = new String[][]{
            new String[]{"n","s"},
            new String[]{"nw","se"},
            new String[]{"ne","sw"},
        };

        Arrays.stream(pairs).forEach(pair -> {
            int rem = dirs.get(pair[0]) > dirs.get(pair[1]) ? dirs.get(pair[1]) : dirs.get(pair[0]);
            dirs.put(pair[0], dirs.get(pair[0]) - rem);
            dirs.put(pair[1], dirs.get(pair[1]) - rem);
        });

        //compress combos
        String[][] combos = new String[][]{
            new String[]{"ne", "s", "se"},
            new String[]{"nw", "s", "sw"},
            new String[]{"se", "n", "ne"},
            new String[]{"sw", "n", "nw"},
            new String[]{"se", "sw", "s"},
            new String[]{"ne", "nw", "n"}
        };

        Arrays.stream(combos).forEach(combo -> {
            int rem = dirs.get(combo[0]) > dirs.get(combo[1]) ? dirs.get(combo[1]) : dirs.get(combo[0]);
            dirs.put(combo[0], dirs.get(combo[0]) - rem);
            dirs.put(combo[1], dirs.get(combo[1]) - rem);
            dirs.put(combo[2], dirs.get(combo[2]) + rem);
        });

        return Arrays.stream(Ints.toArray(dirs.values())).sum();
    }

    @Override
    public void solve() {
        String[] path = getInputString().split(",");
        this.solution1 = reducePath(path);

        int max = 0;
        ArrayList<String> pathList = new ArrayList<>();
        for(String p:path){
            pathList.add(p);
            int d = reducePath(pathList.toArray(new String[0]));
            if(d > max){
                max = d;
            }
        }
        this.solution2 = max;
    }

    public static void main(String[] args) {
        Day11 day = new Day11();
        day.solve();
        day.printSolutions();
    }
}
