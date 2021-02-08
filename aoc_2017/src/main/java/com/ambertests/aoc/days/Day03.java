package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Coordinate;
import com.ambertests.aoc.common.Day;

import java.util.HashMap;

public class Day03 extends Day {
    public Day03() {
        this.dayNum = 3;
    }

    private Coordinate getCoordinate(int square, int side, int val) {
        //The largest value in the square is in the
        //bottom right corner. Compute the other corners
        //and work back around to the target number.
        /*
        17  16  15  14  13
        18   5   4   3  12
        19   6   1   2  11
        20   7   8   9  10
        21  22  23---> ...
        */
        int x = square;
        int y = -(square);
        int pos = side * side;
        int bl = pos - (side - 1);
        int tl = bl - (side - 1);
        int tr = tl - (side - 1);
        while (pos > val) {
            pos -= 1;
            if (pos >= bl) {
                x -= 1;
            }
            if (pos < bl && pos >= tl) {
                y += 1;
            }
            if (pos < tl && pos >= tr) {
                x += 1;
            }
            if (pos < tr) {
                y -= 1;
            }
        }
        return new Coordinate(x, y);
    }

    public int getDistance(int num) {
        int square = 0;
        int a = 1;
        //first figure out how many squares there are
        while ((a * a) < num) {
            a += 2;
            square += 1;
        }

        Coordinate c = getCoordinate(square, a, num);
        return (Math.abs(c.x) + Math.abs(c.y));
    }

    public int createGrid(int target) {
        int val = 1;
        HashMap<Coordinate, Integer> grid = new HashMap<>();
        grid.put(new Coordinate(0, 0), val);
        int square = 0;
        int side = 1;
        while (val <= target) {
            if (grid.size() == side * side) {
                square += 1;
                side += 2;
            }
            Coordinate c = getCoordinate(square, side, grid.size() + 1);

            val = grid.getOrDefault(new Coordinate(c.x + 1, c.y + 1), 0) +
                    grid.getOrDefault(new Coordinate(c.x + 1, c.y), 0) +
                    grid.getOrDefault(new Coordinate(c.x + 1, c.y - 1), 0) +
                    grid.getOrDefault(new Coordinate(c.x, c.y + 1), 0) +
                    grid.getOrDefault(new Coordinate(c.x, c.y - 1), 0) +
                    grid.getOrDefault(new Coordinate(c.x - 1, c.y + 1), 0) +
                    grid.getOrDefault(new Coordinate(c.x - 1, c.y), 0) +
                    grid.getOrDefault(new Coordinate(c.x - 1, c.y - 1), 0);

            grid.put(c, val);
        }
        return val;
    }

    @Override
    public void solve() {
        this.solution1 = getDistance(325489); //552
        this.solution2 = createGrid(325489); //330785
    }

    public static void main(String[] args) {
        Day03 day3 = new Day03();
        day3.solve();
        day3.printSolutions();
    }
}
