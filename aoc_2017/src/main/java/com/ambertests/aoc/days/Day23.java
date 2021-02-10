package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.*;

public class Day23 extends Day{
    public Day23(){
        this.dayNum = 23;
    }
    long getValue(String str, HashMap<String, Long> registers) {
        if (Character.isLetter(str.charAt(0))) {
            return registers.getOrDefault(str, 0L);
        } else {
            return Long.parseLong(str);
        }
    }
    long run(String[] program, long a){
        int idx = 0;
        long muls = 0;
        HashMap<String, Long> registers = new HashMap<>();
        for(char c:"abcdefgh".toCharArray()){
            registers.put(Character.toString(c), 0L);
        }
        registers.put("a", a);
        while(idx < program.length){
            String[] inst = program[idx].split(" ");
            String cmd = inst[0];
            String x = inst[1];
            long y = inst.length == 3 ? getValue(inst[2], registers) : 0;
            if (Character.isLetter(x.charAt(0))) {
                registers.putIfAbsent(x, 0L);
            }
            switch (cmd) {
                case ("set"):
                    registers.put(x, y);
                    idx += 1;
                    break;
                case ("sub"):
                    registers.put(x, registers.get(x) - y);
                    idx += 1;
                    break;
                case ("mul"):
                    registers.put(x, registers.get(x) * y);
                    idx += 1;
                    muls += 1;
                    break;
                case ("jnz"):
                    if (getValue(x, registers) != 0) {
                        idx += y;
                    } else {
                        idx += 1;
                    }
                
            }
        }
        return a == 0 ? muls : registers.get("h");
    }
    @Override
    public void solve() {
        this.solution1 = run(getInputStringArray(), 0L);
        //this.solution2 = run(getInputStringArray(), 1L);
    }
    public static void main(String[] args) {
        Day23 day = new Day23();
        day.solve();
        day.printSolutions();
    }
}
