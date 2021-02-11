package com.ambertests.aoc;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

public class AoC2017 {

    public static void main(String[] args) throws IOException {
        System.out.println("======Running Advent of Code 2017======");
        long s = System.currentTimeMillis();
        ClassPath.from(AoC2017.class.getClassLoader())
                .getTopLevelClasses("com.ambertests.aoc.days")
                .stream().map(ClassPath.ClassInfo::load)
                .sorted(Comparator.comparing(Class::getName))
                .forEach(c -> {
                    try {
                        System.out.println("\n**** " + c.getSimpleName() + " ****");
                        long start = System.currentTimeMillis();
                        c.getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
                        String time = (System.currentTimeMillis() - start) + "ms";
                        System.out.println("**** Completed in " + time + " ****");

                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                });
        String total = (System.currentTimeMillis() - s) + "ms";
        System.out.println("Time to complete: " + total);
    }

}
