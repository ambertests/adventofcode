package com.ambertests.aoc.days;

import com.ambertests.aoc.common.Day;

import java.util.HashMap;

public class Day21 extends Day {
    public Day21() {
        this.dayNum = 21;
    }

    char[][] flip(char[][] grid) {
        char[][] flipped = new char[grid.length][];
        for (int j = 0; j < grid.length; j++) {
            char[] c = grid[j];
            char[] reversed = new char[c.length];
            for (int i = 0; i < c.length; i++) {
                reversed[c.length - 1 - i] = c[i];
            }
            flipped[j] = reversed;
        }
        return flipped;
    }

    char[][] rotate(char[][] grid) {
        char[][] rotated = new char[grid.length][];
        for (int r = 0; r < rotated.length; r++) {
            char[] newRow = new char[grid.length];
            int c = grid.length - 1 - r;
            for (int cr = 0; cr < grid.length; cr++) {
                newRow[cr] = grid[cr][c];
            }
            rotated[r] = newRow;
        }
        return rotated;
    }

    String gridToRule(char[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append(row);
            sb.append("/");
        }
        sb.deleteCharAt(sb.lastIndexOf("/"));
        return sb.toString();
    }

    long countHashes(char[][] grid) {
        long hashes = 0;
        for (char[] r : grid) {
            for (char c : r) {
                if (c == '#') {
                    hashes += 1;
                }
            }
        }
        return hashes;
    }

    boolean matchesRule(String rule, char[][] grid) {
        boolean matches = false;
        int size = rule.indexOf("/");
        if (size == grid.length) {
            long ruleHashes = rule.chars().filter(ch -> ch == '#').count();
            long gridHashes = countHashes(grid);
            if (ruleHashes == gridHashes) {
                for (int i = 0; i < 4; i++) {
                    if (rule.equals(gridToRule(grid)) || rule.equals(gridToRule(flip(grid)))) {
                        matches = true;
                        break;
                    }
                    grid = rotate(grid);
                }
            }
        }
        return matches;
    }

    char[][] enhancementToGrid(String enhance) {
        String[] rows = enhance.split("/");
        char[][] grid = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            grid[i] = rows[i].toCharArray();
        }
        return grid;
    }

    void printGrid(char[][] grid) {
        System.out.println();
        for (char[] row : grid) {
            System.out.println(new String(row));
        }
    }

    char[][] applyRules(char[][] grid, HashMap<String, String> ruleBook) {
        int subSize = grid.length % 2 == 0 ? 2 : 3;
        int subCount = grid.length / subSize;
        int newSubSize = subSize + 1;

        char[][] newGrid = new char[newSubSize * subCount][];
        for (int i = 0; i < newGrid.length; i++) {
            newGrid[i] = new char[newSubSize * subCount];
        }

        for (int xOffset = 0; xOffset < subCount; xOffset++) {
            for (int yOffset = 0; yOffset < subCount; yOffset++) {
                char[][] subGrid = new char[subSize][];
                for (int sRow = 0; sRow < subSize; sRow++) {
                    char[] sr = new char[subSize];
                    for (int sCol = 0; sCol < subSize; sCol++) {
                        sr[sCol] = grid[sRow + (subSize * xOffset)][sCol + (subSize * yOffset)];
                    }
                    subGrid[sRow] = sr;
                }
                for (String rule : ruleBook.keySet()) {
                    if (matchesRule(rule, subGrid)) {
                        char[][] enhanced = enhancementToGrid(ruleBook.get(rule));
                        for (int nRow = 0; nRow < newSubSize; nRow++) {
                            for (int nCol = 0; nCol < newSubSize; nCol++) {
                                char ch = enhanced[nRow][nCol];
                                newGrid[nRow + (newSubSize * xOffset)][nCol + (newSubSize * yOffset)] = ch;
                            }
                        }
                    }
                }
            }
        }

        return newGrid;
    }

    HashMap<String, String> createRuleBook(String[] input){
        HashMap<String, String> ruleBook = new HashMap<>();
        for (String s : input) {
            String[] kv = s.split(" => ");
            ruleBook.put(kv[0], kv[1]);
        }
        return ruleBook;
    }

    long transformGrid(char[][] grid, HashMap<String, String> ruleBook, int count){
        for (int i = 0; i < count; i++) {
            grid = applyRules(grid, ruleBook);
        }
        return countHashes(grid);
    }

    char[][] startingGrid =  new char[][]{
            ".#.".toCharArray(),
            "..#".toCharArray(),
            "###".toCharArray()};

    @Override
    public void solve() {
        HashMap<String, String> ruleBook = createRuleBook(getInputStringArray());
        this.solution1 = transformGrid(startingGrid.clone(), ruleBook, 5);
        this.solution2 = transformGrid(startingGrid.clone(), ruleBook, 18);
    }

    public static void main(String[] args) {
        Day21 day = new Day21();
        day.solve();
        day.printSolutions();
    }
}
