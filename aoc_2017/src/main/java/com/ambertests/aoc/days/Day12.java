package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.*;

public class Day12 extends Day {
    HashMap<String, String[]> connections = new HashMap<>();
    public Day12(){
        this.dayNum = 12;
    }

    void parseInput(String[] input){
        //2 <-> 0, 3, 4
        for(String s:input){
            String[] kv = s.split(" <-> ");
            String[] vals = kv[1].split(", ");
            this.connections.put(kv[0], vals);
        }
    }

    HashSet<String> getConnections(String key, HashSet<String> counted){
        for(String v:this.connections.get(key)){
            if(!counted.contains(v)){
                counted.add(v);
                getConnections(v, counted);
            }
        }
        return counted;
    }

    int countGroups(){
        int groupCount = 0;
        ArrayList<String> keys = new ArrayList<>(this.connections.keySet());
        while(keys.size() > 0){
            String key = keys.get(0);
            HashSet<String> start = new HashSet<>(Collections.singletonList(key));
            HashSet<String> connections = getConnections(key, start);
            keys.removeAll(connections);
            groupCount += 1;
        }
        return groupCount;
    }

    @Override
    public void solve() {
        parseInput(getInputStringArray());
        this.solution1 = getConnections("0", new HashSet<>(Collections.singletonList("0"))).size();
        this.solution2 = countGroups();
    }

    public static void main(String[] args) {
        Day12 day = new Day12();
        day.solve();
        day.printSolutions();
    }
}
