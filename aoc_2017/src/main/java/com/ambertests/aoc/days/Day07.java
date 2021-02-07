package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;
import java.util.*;

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

    int getChildWeight(String parent, int weight){
        if(parentToChildren.containsKey(parent)){
            for(String child:parentToChildren.get(parent)){
                weight += getChildWeight(child, nameToWeight.get(child));
            }
        }
        return weight;
    }

    int findBadWeight(){
        int newWeight = 0;
        HashSet<String> parents = getParents(getLeaves());
        while(newWeight == 0){
            for(String p:parents){
                HashMap<String, Integer> childWeights = new HashMap<String, Integer>();
                for(String c: parentToChildren.get(p)){
                    childWeights.put(c, getChildWeight(c, nameToWeight.get(c)));
                }
                HashMap<Integer, Integer> weightCounts = new HashMap<Integer, Integer>();
                for(Integer w:childWeights.values()){
                    weightCounts.put(w, weightCounts.getOrDefault(w, 0) + 1);
                }
                if(weightCounts.size() == 2){
                    int target = 0;
                    int bad = 0;
                    for(Integer w:weightCounts.keySet()){
                        if(weightCounts.get(w) == 1){
                            bad = w;
                        }else{
                            target = w;
                        }
                    }
                    String badName = "";
                    for(String c:childWeights.keySet()){
                        if(childWeights.get(c) == bad){
                            badName = c;
                            break;
                        }
                    }
                    newWeight = nameToWeight.get(badName) + (target - bad);
                }
                
            }
            parents = getParents(new ArrayList<String>(parents));
        }
        return newWeight;
    }

    @Override
    public void solve() {
        parseInput(dayStrings());
        this.solution1 = getBottom();
        this.solution2 = findBadWeight();
    }

    public static void main(String[] args) {
        Day07 day = new Day07();
        day.solve();
        day.printSolutions();
    }
}
