package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

public class Day19 extends Day {

    public Day19() {
        this.dayNum = 19;
    }

    boolean canMoveSouth(int x, int y, char[][] path) {
        return (y < path.length - 1) && (path[y + 1].length >= x) && (path[y + 1][x] != ' ');
    }

    boolean canMoveNorth(int x, int y, char[][] path) {
        return (y > 0) && (path[y - 1].length >= x) && (path[y - 1][x] != ' ');
    }

    boolean canMoveEast(int x, int y, char[][] path) {
        return (x < path[y].length - 1) && (path[y][x + 1] != ' ');
    }

    boolean canMoveWest(int x, int y, char[][] path) {
        return (x > 0) && (path[y][x - 1] != ' ');
    }

    boolean canMove(char d, int x, int y, char[][] path) {
        if (d == 's') {
            return canMoveSouth(x, y, path);
        }
        if (d == 'n') {
            return canMoveNorth(x, y, path);
        }
        if (d == 'e') {
            return canMoveEast(x, y, path);
        }
        if (d == 'w') {
            return canMoveWest(x, y, path);
        }
        return false;
    }

    char opposite(char d) {
        if (d == 'n') {
            return 's';
        }
        if (d == 's') {
            return 'n';
        }
        if (d == 'e') {
            return 'w';
        }
        if (d == 'w') {
            return 'e';
        }
        return 'x';
    }

    int stepCount = 0;

    String followPath(char[][] path) {
        StringBuilder sb = new StringBuilder();
        int x = 0;
        int y = 0;
        while (path[y][x] == ' ') {
            x += 1;
        }
        boolean blocked = false;
        char currentDirection = 's';
        char val = path[y][x];
        while (!blocked) {
            stepCount += 1;
            if (Character.isLetter(val)) {
                sb.append(val);
            }
            if (val == '+') {
                char newDir = 'x';
                for (char d : "news".toCharArray()) {
                    if (d != opposite(currentDirection)) {
                        if (canMove(d, x, y, path)) {
                            newDir = d;
                            break;
                        }
                    }
                }
                if (newDir == 'x') {
                    blocked = true;
                } else {
                    currentDirection = newDir;
                }
            }
            if (!blocked && canMove(currentDirection, x, y, path)) {
                switch (currentDirection) {
                    case ('n'):
                        y -= 1;
                        break;
                    case ('s'):
                        y += 1;
                        break;
                    case ('e'):
                        x += 1;
                        break;
                    case ('w'):
                        x -= 1;
                        break;
                }
                val = path[y][x];
            } else {
                blocked = true;
            }
        }
        return sb.toString();
    }

    @Override
    public void solve() {
        this.solution1 = followPath(getInputGrid());
        this.solution2 = stepCount;
    }

    public static void main(String[] args) {
        Day19 day = new Day19();
        day.solve();
        day.printSolutions();
    }
}
