package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.Collections;
import java.util.HashMap;

public class Day08 extends Day {
    HashMap<String, Integer> registers = new HashMap<String, Integer>();
    public Day08(){
        this.dayNum = 8;
    }

    boolean parseCondition(String condition){
        String[] cond = condition.split(" ");
        String reg = cond[0];
        String op = cond[1];
        int val = Integer.parseInt(cond[2]);
        registers.putIfAbsent(reg, 0);
        boolean isTrue = false;
        switch(op){
            case("<"):
                isTrue = registers.get(reg) < val;
                break;
            case(">"):
                isTrue = registers.get(reg) > val;
                break;
            case("<="):
                isTrue = registers.get(reg) <= val;
                break;
            case(">="):
                isTrue = registers.get(reg) >= val;
                break;
            case("=="):
                isTrue = registers.get(reg) == val;
                break;
            case("!="):
                isTrue = registers.get(reg) != val;
                break;
        }
        return isTrue;
    }

    void parseMod(String modify){
        String[] mod = modify.split(" ");
        String reg = mod[0];
        String op = mod[1];
        int val = Integer.parseInt(mod[2]);
        registers.putIfAbsent(reg, 0);
        if(op.equals("dec")){
            registers.put(reg, registers.get(reg) - val);
        }else{
            registers.put(reg, registers.get(reg) + val);
        }
    }

    void parseInstruction(String instruction){
        //a dec -511 if x >= -4
        String[] mc = instruction.split(" if ");
        if(parseCondition(mc[1])){
            parseMod(mc[0]);
        }
    }

    int getMaxRegister(String[] instructions){
        for(String s:instructions){
            parseInstruction(s);
        }
        
        return Collections.max(registers.values());
    }

    @Override
    public void solve() {
        int totalMax = 0;
        for(String s:getInputStringArray()){
            parseInstruction(s);
            int m = Collections.max(registers.values());
            if(m > totalMax){
                totalMax = m;
            }
        }
        this.solution1 = Collections.max(registers.values());
        this.solution2 = totalMax;
    }

    public static void main(String[] args) {
        Day08 day = new Day08();
        day.solve();
        day.printSolutions();
    }
}
