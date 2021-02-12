package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Coordinate;
import com.ambertests.aoc.common.Day;

import java.util.*;

public class Day24 extends Day {
    HashMap<Integer, Set<Coordinate>> prongsToPipes;
    HashSet<List<Coordinate>> allBridges = new HashSet<>();

    public Day24() {
        this.dayNum = 24;
    }

    void parseInput(String[] input) {
        prongsToPipes = new HashMap<>();
        Arrays.stream(input).forEach(p -> {
            String[] sides = p.split("/");
            Coordinate c = new Coordinate(Integer.parseInt(sides[0]), Integer.parseInt(sides[1]));
            prongsToPipes.putIfAbsent(c.x, new HashSet<>());
            prongsToPipes.putIfAbsent(c.y, new HashSet<>());
            prongsToPipes.get(c.x).add(c);
            prongsToPipes.get(c.y).add(c);
        });
    }

    int strength(List<Coordinate> bridge) {
        return bridge.stream().mapToInt(c -> c.x + c.y).sum();
    }

    int maxStrength = 0;
    int maxLength = 0;

    void buildBridges(Integer prongs, List<Coordinate> bridge, ArrayList<Coordinate> used) {
        for (Coordinate pipe : prongsToPipes.getOrDefault(prongs, new HashSet<>())) {
            if (!used.contains(pipe)) {
                ArrayList<Coordinate> newBridge = new ArrayList<>(bridge);
                newBridge.add(pipe);
                if (newBridge.size() >= maxLength) {
                    allBridges.add(newBridge);
                }

                ArrayList<Coordinate> newUsed = new ArrayList<>(used);
                newUsed.add(pipe);

                Integer newProngs = pipe.x == prongs ? pipe.y : pipe.x;
                buildBridges(newProngs, newBridge, new ArrayList<>(newUsed));
            }
        }
        maxStrength = Math.max(maxStrength, strength(bridge));
        maxLength = Math.max(maxLength, bridge.size());
    }

    @Override
    public void solve() {
        parseInput(getInputStringArray());
        buildBridges(0, new ArrayList<>(), new ArrayList<>());
        this.solution1 = maxStrength;
        this.solution2 = allBridges.stream().filter(b -> b.size() == maxLength)
                .mapToInt(this::strength).max().orElse(0);
    }

    public static void main(String[] args) {
        Day24 day = new Day24();
        day.solve();
        day.printSolutions();
    }

}
