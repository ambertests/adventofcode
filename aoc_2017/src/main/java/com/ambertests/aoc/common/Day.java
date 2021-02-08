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

    protected String getInputString() {
        return getResourceAsString(String.format("day%02d.txt", dayNum));
    }


    protected String[] getInputStringArray() {
        return getInputStringArray(DEFAULT_DELIMITER);
    }

    protected String[] getInputStringArray(String delimiter) {
        return Arrays.stream(getInputString().split(delimiter)).toArray(String[]::new);
    }

    protected int[] getInputInts(){
        return Arrays.stream(getInputStringArray()).mapToInt(Integer::parseInt).toArray();
    }
    public abstract void solve();
}
