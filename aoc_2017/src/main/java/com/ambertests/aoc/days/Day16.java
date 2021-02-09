package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;
import com.google.common.primitives.Chars;

import java.util.ArrayList;
import java.util.List;

public class Day16 extends Day {
    public Day16() {
        this.dayNum = 16;
    }

    char[] spin(char[] arr, int offset) {
        char[] spun = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int idx = i + offset;
            if (idx >= arr.length) {
                idx -= arr.length;
            }
            if (idx < 0) {
                idx += arr.length;
            }
            spun[idx] = arr[i];
        }
        return spun;
    }

    char[] exchange(char[] arr, int a, int b) {
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
        return arr;
    }

    char[] partner(char[] arr, char a, char b) {
        List<Character> list = Chars.asList(arr);
        return exchange(arr, list.indexOf(a), list.indexOf(b));
    }

    char[] dance(char[] dancers, ArrayList<int[]> steps) {
        for(int[] step:steps){
            char m = (char)step[0];
            if(m == 's'){
                dancers = spin(dancers, step[1]);
            }
            if(m == 'x'){
                dancers = exchange(dancers, step[1], step[2]);
            }
            if(m == 'p'){
                dancers = partner(dancers, (char)step[1], (char)step[2]);
            }
        }
        return dancers;
    }

    ArrayList<int[]> parseSteps(String[] steps){
        ArrayList<int[]> parsed = new ArrayList<>();
        for(String step:steps){
            if (step.startsWith("s")) {
                parsed.add(new int[]{(int)'s', Integer.parseInt(step.substring(1))});
            }
            if (step.startsWith("x")) {
                String[] pos = step.substring(1).split("/");
                parsed.add(new int[]{(int)'x', Integer.parseInt(pos[0]), Integer.parseInt(pos[1])});
            }
            if (step.startsWith("p")) {
                parsed.add(new int[]{(int)'p', (int)step.charAt(1), (int)step.charAt(3)});
            }
        }
        return parsed;
    }

    int findCycle(String start, ArrayList<int[]> steps){
        char[] dancers = start.toCharArray();
        int rounds = 0;
        do{
            rounds += 1;
            dancers = dance(dancers, steps);
        }while(!(new String(dancers)).equals(start));
        return rounds;
    }

    @Override
    public void solve() {
        String dancers = "abcdefghijklmnop";
        ArrayList<int[]> steps = parseSteps(getInputString().split(","));
        this.solution1 = new String(dance(dancers.toCharArray(), steps));

        int cycle = findCycle(dancers, steps);
        int rounds = 1000000000%cycle;
        char[] d = dancers.toCharArray();
        for(int i = 0; i < rounds; i++){
            d = dance(d, steps);
        }
        this.solution2 = new String(d);

    }

    public static void main(String[] args) {
        Day16 day = new Day16();
        day.solve();
        day.printSolutions();
    }
}
