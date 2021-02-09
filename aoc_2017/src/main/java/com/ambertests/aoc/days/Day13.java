package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.Arrays;

public class Day13 extends Day {

    static class Scanner {
        int range;
        boolean increaseDepth;
        int depth;

        Scanner(int range) {
            this.range = range;
            this.increaseDepth = true;
            this.depth = 0;
        }

        void move() {
            if (this.range > 0) {
                if (this.increaseDepth) {
                    if (this.depth == range - 1) {
                        this.increaseDepth = false;
                    }
                } else {
                    if (this.depth == 0) {
                        this.increaseDepth = true;
                    }
                }
                if(this.increaseDepth){
                    this.depth += 1;
                }else{
                    this.depth -= 1;
                }
            }
        }
    }

    public Day13() {
        this.dayNum = 13;
    }

    int getMaxLayer(String[] input) {
        return Arrays.stream(input)
                .map(s -> Integer.parseInt(s.split(": ")[0]))
                .max(Integer::compare)
                .orElse(0);
    }

    Scanner[] initFirewall(String[] input) {
        Scanner[] firewall = new Scanner[getMaxLayer(input) + 1];
        Arrays.fill(firewall, new Scanner(0));
        for (String s : input) {
            //0: 3
            String[] lr = s.split(": ");
            firewall[Integer.parseInt(lr[0])] = new Scanner(Integer.parseInt(lr[1]));
        }
        return firewall;
    }

    int traverseFirewall(Scanner[] firewall){
        return traverseFirewall(firewall, 0, false);
    }

    int traverseFirewall(Scanner[] firewall, int delay, boolean stopOnCatch){
        for(int i = 0; i < delay; i++){
            Arrays.stream(firewall).forEach(Scanner::move);
        }
        int score = 0;
        int position = 0;
        boolean caught = false;
        while(position < firewall.length){
            Scanner scanner = firewall[position];
            if(scanner.depth == 0 && scanner.range > 0){
                caught = true;
                score += (position * scanner.range);
                if(stopOnCatch){
                    position = firewall.length;
                }
            }
            if(!stopOnCatch || !caught) {
                Arrays.stream(firewall).forEach(Scanner::move);
                position += 1;
            }
        }
        if(caught && score == 0){
            //penalize runs that were caught only on the first layer
            score += 1;
        }
        return score;
    }

    int findDelay(Scanner[] firewall){
        int delay = 0;
        int score = traverseFirewall(firewall, delay, true);
        while(score != 0){
            delay += 1;
            if(delay%10000 == 0){
                System.out.printf("delay: %d%n", delay);
            }
            Arrays.stream(firewall).forEach(scanner -> {
                scanner.depth = 0;
                scanner.increaseDepth = true;
            });
            score = traverseFirewall(firewall, delay, true);
        }
        return delay;
    }

    @Override
    public void solve() {
        Scanner[] firewall = initFirewall(getInputStringArray());
        this.solution1 = traverseFirewall(firewall);
        Arrays.stream(firewall).forEach(scanner -> {
            scanner.depth = 0;
            scanner.increaseDepth = true;
        });

        //this.solution2 = findDelay(firewall);
    }

    public static void main(String[] args) {
        Day13 day = new Day13();
        day.solve();
        day.printSolutions();
    }
}
