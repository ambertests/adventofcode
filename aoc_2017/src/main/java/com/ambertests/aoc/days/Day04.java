package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;
import com.google.common.primitives.Chars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day04 extends Day {
    String[] input;

    public Day04() {
        this.dayNum = 4;
        this.input = getInputStringArray();
    }

    @Override
    public void solve() {
        int noDupes = 0;
        int noAnagrams = 0;
        for (String phrase : input) {
            List<String> list = Arrays.asList(phrase.split(" "));
            HashSet<String> set = new HashSet<>(list);
            if (list.size() == set.size()) {
                noDupes += 1;
                boolean hasAnagram = false;
                for (String a : set) {
                    for (String b : set) {
                        if (a.equals(b)) {
                            continue;
                        }
                        boolean isAnagram = true;
                        if (a.length() == b.length()) {
                            ArrayList<Character> aChar = new ArrayList<>(Chars.asList(a.toCharArray()));
                            for (char c : b.toCharArray()) {
                                if (!aChar.contains(c)) {
                                    isAnagram = false;
                                    break;
                                } else {
                                    aChar.remove((Character) c);
                                }
                            }
                        } else {
                            isAnagram = false;
                        }

                        if (isAnagram) {
                            hasAnagram = true;
                            break;
                        }
                    }
                    if (hasAnagram) {
                        break;
                    }

                }
                if (!hasAnagram) {
                    noAnagrams += 1;
                }
            }
        }
        this.solution1 = noDupes;
        this.solution2 = noAnagrams;
    }

    public static void main(String[] args) {
        Day04 day4 = new Day04();
        day4.solve();
        day4.printSolutions();
    }
}
