package com.ambertests.aoc.common;

public class Coordinate extends Object {
    public int x;
    public int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        boolean eq = false;
        if(o instanceof Coordinate){
            Coordinate c = (Coordinate)o;
            eq = (this.x == c.x && this.y == c.y);
        }
        return eq;
    }

    @Override
    public String toString(){
        return String.format("(%d,%d)", x, y);
    }

    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

}
