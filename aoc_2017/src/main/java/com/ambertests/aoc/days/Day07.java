package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;
import com.google.common.primitives.Ints;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Day07 extends Day {
    HashMap<String, Integer> nameToWeight;
    HashMap<String, String> childToParent;
    HashMap<String, String[]> parentToChildren;

    public Day07(){
        this.dayNum = 7;
    }

    void parseInput(String[] input){
        nameToWeight = new HashMap<String, Integer>();
        childToParent = new HashMap<String, String>();
        parentToChildren = new HashMap<String, String[]>();
        //sppgjfx (530) -> bszaj, ozzxjr, ivfffiz, cdqkuuw
        for(String s:input){
            String[] pc = s.split(" -> ");
            String nameAndWeight = pc[0];
            String[] nw = nameAndWeight.split(" \\(");
            String name = nw[0];
            Integer weight = Integer.parseInt(nw[1].substring(0, nw[1].length() - 1));
            nameToWeight.put(name, weight);
            if(pc.length == 2){
                String[] children = pc[1].split(", ");
                parentToChildren.put(name, children);
                for (String child:children){
                    childToParent.put(child, name);
                }
            }
        }
    }

    String getBottom(){
        String bottom = "";
        for(String parent: parentToChildren.keySet()){
            if(!childToParent.containsKey(parent)){
                bottom = parent;
                break;
            }
        }
        return bottom;
    }

    ArrayList<String> getLeaves(){
        ArrayList<String> leaves = new ArrayList<String>();
        for(String name:nameToWeight.keySet()){
            if(!parentToChildren.containsKey(name)){
                leaves.add(name);
            }
        }
        return leaves;
    }

    HashSet<String> getParents(ArrayList<String> children){
        HashSet<String> parents = new HashSet<String>();
        for(String child:children){
            parents.add(childToParent.get(child));
        }
        return parents;
    }

    int findBadWeight(){
        int diff = 0;
        HashSet<String> parents = getParents(getLeaves());
        HashMap<String, Integer> totalChildWeights = new HashMap<String, Integer>();
        while(diff == 0){
            for(String p:parents){
                
                
            }
        }
        return diff;
    }


    @Override
    public void solve() {
        parseInput(dayStrings());
        this.solution1 = getBottom();
    }

    public static void main(String[] args) {
        Day07 day = new Day07();
        day.solve();
        day.printSolutions();
    }
}
