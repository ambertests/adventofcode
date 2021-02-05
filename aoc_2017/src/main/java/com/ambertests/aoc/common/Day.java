package com.ambertests.aoc.common;

import java.io.IOException;
import java.io.File;
import static org.apache.commons.io.FileUtils.readFileToString;

public abstract class Day {
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
    public abstract void solvePart1();
    public abstract void solvePart2();
}
