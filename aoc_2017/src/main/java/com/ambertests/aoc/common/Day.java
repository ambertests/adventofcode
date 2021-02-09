package com.ambertests.aoc.common;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

import static org.apache.commons.io.FileUtils.readFileToString;

public abstract class Day {
    private static final String DEFAULT_DELIMITER = System.lineSeparator();

    protected Integer dayNum = 0;
    protected Object solution1 = "";
    protected Object solution2 = "";

    public void printSolutions() {
        System.out.printf("Solution %d.1: %s%n", dayNum, solution1.toString());
        System.out.printf("Solution %d.2: %s%n", dayNum, solution2.toString());
    }

    protected String getResourceAsString(String resource) {
        try {
            URL resUrl = Objects.requireNonNull(Day.class.getClassLoader().getResource(resource));
            return readFileToString(new File(resUrl.getFile()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected String getInputString() {
        return getResourceAsString(String.format("day%02d.txt", dayNum));
    }


    protected String[] getInputStringArray() {
        return Arrays.stream(getInputString().split(DEFAULT_DELIMITER)).toArray(String[]::new);
    }

    protected int[] getInputInts() {
        return Arrays.stream(getInputStringArray()).mapToInt(Integer::parseInt).toArray();
    }

    protected char[][] getInputGrid(){
        return Arrays.stream(getInputStringArray()).map(String::toCharArray).toArray(char[][]::new);
    }

    public abstract void solve();
}
