package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day20 extends Day {
    public Day20() {
        this.dayNum = 20;
    }

    void update(ArrayList<int[]> particles) {
        particles.forEach(p -> {
            //update velocities
            p[3] += p[6];
            p[4] += p[7];
            p[5] += p[8];

            //update positions
            p[0] += p[3];
            p[1] += p[4];
            p[2] += p[5];
        });
    }

    void removeCollisions(ArrayList<int[]> particles) {
        HashSet<int[]> toRemove = new HashSet<>();
        for (int[] p1 : particles) {
            if (toRemove.contains(p1)) {
                continue;
            }
            for (int[] p2 : particles) {
                if (Arrays.equals(p1, p2)) {
                    continue;
                }
                if (p1[0] == p2[0] && p1[1] == p2[1] && p1[2] == p2[2]) {
                    toRemove.add(p1);
                    toRemove.add(p2);
                }
            }
        }
        toRemove.forEach(particles::remove);
    }

    int slowestParticle = -1;
    ArrayList<int[]> particleList(String[] particles){
        ArrayList<int[]> particleList = new ArrayList<>();
        Pattern p = Pattern.compile("-?\\d+");

        int minAcc = Integer.MAX_VALUE;
        for (int i = 0; i < particles.length; i++) {
            int[] particle = p.matcher(particles[i]).results()
                    .map(MatchResult::group)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int acc = Math.abs(particle[6]) + Math.abs(particle[7]) + Math.abs(particle[8]);
            if (acc < minAcc) {
                minAcc = acc;
                slowestParticle = i;
            }
            particleList.add(particle);
        }
        return particleList;
    }

    @Override
    public void solve() {
        ArrayList<int[]> particles = particleList(getInputStringArray());
        this.solution1 = this.slowestParticle;

        for (int i = 0; i < 40; i++) {
            update(particles);
            removeCollisions(particles);
        }
        this.solution2 = particles.size();
    }

    public static void main(String[] args) {
        Day20 day = new Day20();
        day.solve();
        day.printSolutions();
    }
}
