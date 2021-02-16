class Fighter():
    def __init__(self, id, race, x, y):
        self.id = id
        self.race = race
        self.loc = (x,y)
        self.hit_points = 200

    def __eq__(self, o: object) -> bool:
        if isinstance(o, tuple):
            return self.loc[0] == o[0] and self.loc[1] == o[1]
        elif isinstance(o, Fighter):
            return self.id == o.id and self.race == o.race
        else:
            return False

    def adjacent(self):
        return [
            (self.loc[0], self.loc[1] - 1),
            (self.loc[0] - 1, self.loc[1]),
            (self.loc[0] + 1, self.loc[1]),
            (self.loc[0], self.loc[1] + 1)
        ]

class Battle():
    walls = []
    fighters = []
    size = 0
    rounds = 0

    def __init__(self, map):
        self.walls.clear()
        self.fighters.clear()
        self.size = len(map)
        self.rounds = 0
        for y in range(self.size):
            for x in range(self.size):
                if map[y][x] == '#': self.walls.append((x,y))
                elif map[y][x] != '.': self.fighters.append(Fighter(len(self.fighters), map[y][x], x, y))
        self.sort_fighters
    
    def sort_fighters(self):
        self.fighters = sorted(self.fighters, key=lambda k: (k.loc[1], k.loc[0]))

    def get_opponents(self, race):
        if race == "G": return [f for f in self.fighters if f.race == "E"]
        else: return [f for f in self.fighters if f.race == "G"]

    def get_targets(self, race):
        return sorted([o for o in self.get_opponents(race) if self.has_openings(o)], 
                            key=lambda k: (k.loc[1], k.loc[0]))
    
    def print(self):
        print("Rounds: ", self.rounds)
        for y in range(self.size):
            row = ""
            for x in range(self.size):
                if (x,y) in self.walls:
                    row += "#"
                elif (x,y) in self.fighters:
                    row += self.get_fighter((x,y)).race
                else: 
                    row += "."
            print(row)
        print(["%s(%d)" % (f.race, f.hit_points) for f in self.fighters])

    def is_open(self, pos):
        return pos not in self.walls and pos not in self.fighters

    def get_fighter(self, pos):
        if pos in self.fighters:
            return self.fighters[self.fighters.index(pos)]
        else: return None

    def count_fighters(self, race):
        return len([f for f in self.fighters if f.race == race])

    def can_move(self, fighter):
        for a in fighter.adjacent():
            adj = self.get_fighter(a)
            if adj is not None:
                if fighter.race == "G" and adj.race == "E":
                    return False
                elif fighter.race == "E" and adj.race == "G":
                    return False
            elif self.is_open(a):
                return True
        return False

    def foes(self, fighter):
        foes = []
        for a in fighter.adjacent():
            foe = self.get_fighter(a)
            if foe is not None:
                if (fighter.race == "G" and foe.race == "E") or \
                (fighter.race == "E" and foe.race == "G"):
                    foes.append(foe)
        return sorted(foes, key=lambda k: (k.hit_points, k.loc[1], k.loc[0]))
                
    def openings(self, fighter):
        open = []
        for a in fighter.adjacent():
            if self.is_open(a):
                open.append(a)
        return open
    
    def has_openings(self, fighter):
        return len(self.openings(fighter)) > 0

    def attack(self, fighter):
        attacked = False
        foes = self.foes(fighter)
        if(foes):
            foe = foes[0]
            foe.hit_points -= 3
            if foe.hit_points <= 0:
                self.fighters.remove(foe)
            attacked = True
        return attacked

    def manhattan(self, a, b):
        return abs(a[0] - b[0]) + abs(a[1] - b[1])

    def move(self, fighter):
        paths = []
        min_len = self.size * self.size
        if self.get_opponents(fighter.race):
            if not self.attack(fighter) and self.can_move(fighter):
                for t in self.get_targets(fighter.race):
                    for o in self.openings(t):
                        if self.manhattan(fighter.loc, o) <= min_len:
                            path = self.bfs_shortest_path(fighter.loc, o)
                            if len(path):
                                min_len = min([min_len, len(path)])
                                if len(path) == min_len:
                                    paths.append(path)
                if paths:
                    shortest = [p for p in paths if len(p) == min_len]
                    next_steps = sorted([p[1] for p in shortest], key=lambda k: (k[1], k[0]))
                    fighter.loc = next_steps[0]
                    self.sort_fighters()
                    self.attack(fighter)
  
    # finds shortest path between 2 nodes of a graph using BFS
    def bfs_shortest_path(self, start, goal):
        # keep track of explored nodes
        explored = []
        # keep track of all the paths to be checked
        queue = [[start]]
    
        # return path if start is goal
        if start == goal:
            return []
    
        # keeps looping until all possible paths have been checked
        while queue:
            # pop the first path from the queue
            path = queue.pop(0)
            # get the last node from the path
            node = path[-1]
            if node not in explored:
                neighbours = [
                    (node[0], node[1] - 1),
                    (node[0] - 1, node[1]),
                    (node[0] + 1, node[1]),
                    (node[0], node[1] + 1),
                    ]
                # go through all neighbour nodes, construct a new path and
                # push it into the queue
                for neighbour in neighbours:
                    if self.is_open(neighbour):
                        new_path = list(path)
                        new_path.append(neighbour)
                        queue.append(new_path)
                        # return path if neighbour is goal
                        if neighbour == goal:
                            return new_path
    
                # mark node as explored
                explored.append(node)
    
        # in case there's no path between the 2 nodes
        return []
    



    def score(self):
        return self.rounds * sum([f.hit_points for f in self.fighters])

    def is_complete(self):
        return self.count_fighters("E") == 0 or self.count_fighters("G") == 0

    def do_round(self):
        for f in self.fighters:
            self.move(f)
            if self.is_complete(): 
                break
        if not self.is_complete(): 
            self.sort_fighters()
            self.rounds += 1





input = [list(row.strip()) for row in open("input/input15.txt").readlines()]
test_input1 = [
    list("#######"),  
    list("#.G...#"),
    list("#...EG#"),
    list("#.#.#G#"),
    list("#..G#E#"),
    list("#.....#"),  
    list("#######")
]
test_input2 = [
    list("#######"),
    list("#G..#E#"),
    list("#E#E.E#"),
    list("#G.##.#"),
    list("#...#E#"),
    list("#...E.#"),
    list("#######")
]
test_input3 = [
    list("#######"),
    list("#E..EG#"),
    list("#.#G.E#"),
    list("#E.##E#"),
    list("#G..#.#"),
    list("#..E#.#"),
    list("#######"),
]
test_input4 = [
    list("#######"),
    list("#E.G#.# "),
    list("#.#G..#"),
    list("#G.#.G#"),
    list("#G..#.#"),
    list("#...E.#"),
    list("#######")
]
test_input5 = [
    list("#######"),
    list("#.E...#"),
    list("#.#..G#"),
    list("#.###.#"),
    list("#E#G#G#"),
    list("#...#G#"),
    list("#######")
]
test_input6 = [
    list("#########"),
    list("#G......#"),
    list("#.E.#...#"),
    list("#..##..G#"),
    list("#...##..#"),
    list("#...#...#"),
    list("#.G...G.#"),
    list("#.....G.#"),
    list("#########"),
]

def do_battle(bf):
    battle = Battle(bf)
    #battle.print()
    while not battle.is_complete():
        battle.do_round()
        #print("round:", battle.rounds)
    battle.print()
    return battle.score()

# assert(do_battle(test_input1) == 27730)
# assert(do_battle(test_input2) == 36334)
assert(do_battle(test_input3) == 39514)
# assert(do_battle(test_input4) == 27755)
assert(do_battle(test_input5) == 28944)
# assert(do_battle(test_input6) == 18740)




    
