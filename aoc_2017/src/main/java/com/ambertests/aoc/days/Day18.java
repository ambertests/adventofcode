package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.HashMap;

public class Day18 extends Day {
    public Day18() {
        this.dayNum = 18;
    }

    long runProgram(String[] instructions){
        long lastSound = 0;
        HashMap<String, Long> registers = new HashMap<>();
        int index = 0;
        while(index < instructions.length){
            String[] inst = instructions[index].split(" ");
            String cmd = inst[0];
            String reg = inst[1];
            long val = inst.length == 3 ? getValue(inst[2], registers) : 0;
            registers.putIfAbsent(reg, 0L);
            switch(cmd){
                case("snd"):
                    lastSound = registers.get(reg);
                    index += 1;
                    break;
                case("set"):
                    registers.put(reg, val);
                    index += 1;
                    break;
                case("add"):
                    registers.put(reg, registers.get(reg) + val);
                    index += 1;
                    break;
                case("mul"):
                    registers.put(reg, registers.get(reg) * val);
                    index += 1;
                    break;
                case("mod"):
                    registers.put(reg, registers.get(reg) % val);
                    index += 1;
                    break;
                case("rcv"):
                    if(registers.get(reg) != 0){
                        //break the loop
                        index = instructions.length;
                    }else{
                        index += 1;
                    }
                    break;
                case("jgz"):
                    if(registers.get(reg) > 0){
                        index += val;
                    }else{
                        index += 1;
                    }
            
            }

        }
        return lastSound;
    }

    long getValue(String x, HashMap<String, Long> registers){
        if(Character.isLetter(x.charAt(0))){
            return registers.getOrDefault(x, 0L);
        }else{
            return Long.parseLong(x);
        }
    }

    @Override
    public void solve() {
        this.solution1 = runProgram(getInputStringArray());
    }

    public static void main(String[] args) {
        Day18 day = new Day18();
        day.solve();
        day.printSolutions();
    }
}
