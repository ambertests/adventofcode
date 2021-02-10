package com.ambertests.aoc.days;

import com.ambertests.aoc.common.*;
import java.util.*;

public class Day22 extends Day {
    public Day22() {
        this.dayNum = 22;
    }

    HashSet<Coordinate> processGrid(char[][] grid){
        HashSet<Coordinate> infected = new HashSet<>();
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[y].length; x++){
                if(grid[y][x] == '#'){
                    infected.add(new Coordinate(x,y));
                }
            }
        }
        return infected;
    }

    char turnRight(char dir){
        char c = 'x';
        if(dir == 'n'){
            c = 'e';
        }
        if(dir == 's'){
            c = 'w';
        }
        if(dir == 'w'){
            c = 'n';
        }
        if(dir == 'e'){
            c = 's';
        }
        return c;
    }

    char turnLeft(char dir){
        return turnRight(turnRight(turnRight(dir)));
    }

    char reverse(char dir){
        return turnRight(turnRight(dir));
    }

    void move(Coordinate c, char dir){
        if(dir == 'n'){
            c.y -= 1;
        }
        if(dir == 's'){
            c.y += 1;
        }
        if(dir == 'e'){
            c.x += 1;
        }
        if(dir == 'w'){
            c.x -= 1;
        }
    }

    int infect (Coordinate virus, HashSet<Coordinate> infected, int bursts){
        int causedInfection = 0;
        char direction = 'n';
        for(int i = 0; i < bursts; i++){
            if(infected.contains(virus)){
                direction = turnRight(direction);
                infected.remove(virus);
            }else{
                direction = turnLeft(direction);
                infected.add(virus.clone());
                causedInfection += 1;
            }
            move(virus, direction);
        }
        
        return causedInfection;
    }

    int evolvedInfect (Coordinate virus, HashSet<Coordinate> infected, int bursts){
        HashSet<Coordinate> weakened = new HashSet<>();
        HashSet<Coordinate> flagged = new HashSet<>();
        int causedInfection = 0;
        char direction = 'n';
        for(int i = 0; i < bursts; i++){
            if(infected.contains(virus)){
                direction = turnRight(direction);
                infected.remove(virus);
                flagged.add(virus.clone());
            }else{
                if(weakened.contains(virus)){
                    weakened.remove(virus);
                    infected.add(virus.clone());
                    causedInfection += 1;
                }else{
                    if(flagged.contains(virus)){
                        flagged.remove(virus);
                        direction = reverse(direction);
                    }else{
                        weakened.add(virus.clone());
                        direction = turnLeft(direction);
                    }
                }
            }
            move(virus, direction);
        }
        return causedInfection;
    }

    @Override
    public void solve() {
        char[][] grid = getInputGrid();
        int middle = grid.length/2;
        Coordinate virus = new Coordinate(middle, middle);
        HashSet<Coordinate> infected = processGrid(grid);
        this.solution1 = infect(virus, infected, 10000);


        virus = new Coordinate(middle, middle);
        infected = processGrid(grid);
        this.solution2 = evolvedInfect(virus, infected, 10000000);
    }

    public static void main(String[] args) {
        Day22 day = new Day22();
        day.solve();
        day.printSolutions();
    }
}
