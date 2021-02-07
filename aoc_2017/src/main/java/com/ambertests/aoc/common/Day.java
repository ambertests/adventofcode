package com.ambertests.aoc.common;

import java.io.IOException;
import java.io.File;
import java.util.Arrays;

import static org.apache.commons.io.FileUtils.readFileToString;

public abstract class Day {
    private static final String DEFAULT_DELIMITER = System.lineSeparator();

    protected Integer dayNum = 0;
    protected Object solution1 = "";
    protected Object solution2 = "";
    public void printSolutions(){
        System.out.println(String.format("Solution %d.1: %s", dayNum, solution1.toString()));
        System.out.println(String.format("Solution %d.2: %s", dayNum, solution2.toString()));
    }

    protected String getResourceAsString(String resource) {
        try {
            return readFileToString(new File(Day.class.getClassLoader().getResource(resource).getFile()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected String day() {
        return getResourceAsString(String.format("day%02d.txt", dayNum));
    }


    protected String[] dayStrings() {
        return dayStrings(DEFAULT_DELIMITER);
    }

    protected String[] dayStrings(String delimiter) {
        return Arrays.stream(day().split(delimiter)).toArray(String[]::new);
    }

    protected int[] dayInts(){
        return Arrays.stream(dayStrings()).mapToInt(Integer::parseInt).toArray();
    }
    public abstract void solve();
}
