from itertools import permutations

def process_ducts(file):
    numbers = {}
    walls = set()
    for y, line in enumerate([line.strip() for line in open("input/%s.txt" % file).readlines()]):
        for x in range(len(line)):
            char = line[x]
            if char == '#':
                walls.add((x,y))
            elif char.isdigit():
                numbers[int(char)] = (x,y)
    return numbers, walls

def get_num_from_pos(numbers, pos):
    for n in numbers:
        if numbers[n] == pos:
            return n

def bfs(numbers, walls):
    # breadth first search: the first path to reach another number is 
    # by definition the shortest path between those numbers
    lengths = {}
    for n in numbers:
        loc = numbers[n]
        paths = [[loc]]
        seen = set()
        seen.add(loc)
        while paths:
            curr_path = paths.pop(0)
            x,y = curr_path[-1]
            if (x, y) in numbers.values() and len(curr_path) > 1:
                lengths[(n, get_num_from_pos(numbers, (x,y)))] = len(curr_path) - 1
                continue
            moves = [(x+1, y),(x-1, y),(x, y+1),(x, y-1)]
            for dx, dy in moves:
                if (dx,dy) not in walls and (dx,dy) not in seen:
                    paths.append(curr_path + [(dx, dy)])
                    seen.add((dx, dy))
    return lengths

def solve(file):
    lengths = {}
    distances1 = []
    distances2 = []
    numbers, walls = process_ducts(file)
    lengths =  bfs(numbers, walls)
    for path in permutations(range(1, 8)):
        path = (0,) + path + (0,)
        distance = 0
        for i in range(len(path) - 2):
            distance += lengths[(path[i], path[i+1])]
        distances1.append(distance)
        distances2.append(distance + lengths[(path[-2], path[-1])])
    print("Part 1:", min(distances1))
    print("Part 2:", min(distances2))

solve('day24')