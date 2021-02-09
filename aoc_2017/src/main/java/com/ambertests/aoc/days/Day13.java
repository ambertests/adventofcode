package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.Arrays;
import java.util.HashMap;

public class Day13 extends Day {

    public Day13() {
        this.dayNum = 13;
    }

    int getScore(HashMap<Integer, Integer> firewall, int delay, boolean stopOnCaught) {
        boolean caught = false;
        int score = 0;
        for (int level : firewall.keySet()) {
            int range = firewall.get(level);
            if ((delay + level) % ((range - 1) * 2) == 0) {
                caught = true;
                score += (level * range);
                if (stopOnCaught) {
                    break;
                }
            }
        }
        if (caught && score == 0) {
            score = 1;
        }
        return score;
    }

    int findDelay(HashMap<Integer, Integer> firewall) {
        int delay = 0;
        int score = getScore(firewall, delay, true);
        while (score != 0) {
            delay += 1;
            score = getScore(firewall, delay, true);
        }
        return delay;
    }

    HashMap<Integer, Integer> parseInput(String[] input) {
        HashMap<Integer, Integer> firewall = new HashMap<>();
        for (String s : input) {
            //0: 3
            int[] lr = Arrays.stream(s.split(": "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            firewall.put(lr[0], lr[1]);
        }
        return firewall;
    }

    @Override
    public void solve() {
        HashMap<Integer, Integer> firewall = parseInput(getInputStringArray());
        this.solution1 = getScore(firewall, 0, false);
        this.solution2 = findDelay(firewall);
    }

    public static void main(String[] args) {
        Day13 day = new Day13();
        day.solve();
        day.printSolutions();
    }
}
