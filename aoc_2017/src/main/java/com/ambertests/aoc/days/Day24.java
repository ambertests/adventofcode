package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.*;
import java.util.function.Predicate;

public class Day24 extends Day {
    HashMap<String, Set<String>> prongsToPipes;
    HashSet<String> allBridges = new HashSet<>();

    public Day24() {
        this.dayNum = 24;
    }

    void parseInput(String[] input) {
        prongsToPipes = new HashMap<>();
        Arrays.stream(input)
                .forEach(p -> Arrays.stream(p.split("/"))
                        .forEach(s -> {
                            prongsToPipes.putIfAbsent(s, new HashSet<>());
                            prongsToPipes.get(s).add(p);
                        }));
    }

    int strength(String bridge) {
        return Arrays.stream(bridge.split("#"))
                .filter(Predicate.not(String::isEmpty))
                .flatMapToInt(p -> Arrays.stream(p.split("/"))
                        .mapToInt(Integer::parseInt)).sum();
    }

    int length(String bridge) {
        return Arrays.stream(bridge.split("#"))
                .filter(Predicate.not(String::isEmpty)).toArray().length;
    }

    int maxStrength = 0;
    int maxLength = 0;

    void buildBridges(String prongs, String bridge, ArrayList<String> used) {
        for (String pipe : prongsToPipes.getOrDefault(prongs, new HashSet<>())) {
            if (!used.contains(pipe)) {
                String newBridge = bridge + "#" + pipe;
                allBridges.add(newBridge);
                ArrayList<String> newUsed = new ArrayList<>(used);
                newUsed.add(pipe);
                String[] sides = pipe.split("/");
                String newProngs = sides[0].equals(prongs) ? sides[1] : sides[0];
                buildBridges(newProngs, newBridge, new ArrayList<>(newUsed));
            }
        }
        maxStrength = Math.max(maxStrength, strength(bridge));
        maxLength = Math.max(maxLength, length(bridge));
    }

    @Override
    public void solve() {
        parseInput(getInputStringArray());
        buildBridges("0", "", new ArrayList<>());
        this.solution1 = maxStrength;
        this.solution2 = allBridges.stream().filter(b -> length(b) == maxLength)
                .map(this::strength).max(Integer::compareTo).orElse(0);
    }

    public static void main(String[] args) {
        Day24 day = new Day24();
        day.solve();
        day.printSolutions();
    }


}
