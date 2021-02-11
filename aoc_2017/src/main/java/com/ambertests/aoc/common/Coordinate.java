package com.ambertests.aoc.common;

public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate clone(){
        return new Coordinate(this.x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        boolean eq = false;
        if (o instanceof Coordinate) {
            Coordinate c = (Coordinate) o;
            eq = (this.x == c.x && this.y == c.y);
        }
        return eq;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }

    @Override
    public int hashCode() {
        // Szudzik pairing fuction which supports negative numbers
        // https://gist.github.com/TheGreatRambler/048f4b38ca561e6566e0e0f6e71b7739
        int xx = x >= 0 ? x * 2 : x * -2 - 1;
        int yy = y >= 0 ? y * 2 : y * -2 - 1;
        return (xx >= yy) ? (xx * xx + xx + yy) : (yy * yy + xx);
    }

}
