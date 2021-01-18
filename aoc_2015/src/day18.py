input = [line.strip() for line in open("input/day18.txt").readlines()]
SIZE = len(input)
corners = [(0, 0), (0, SIZE-1), (SIZE-1, SIZE-1), (SIZE-1, 0)]

def get_neighbors(light):
    x, y = light
    neighbors = [
        (x-1,y-1),
        (x-1,y),
        (x-1,y+1),
        (x,y-1),
        (x,y+1),
        (x+1,y-1),
        (x+1,y),
        (x+1,y+1)
    ]
    return set([(x,y) for x, y in neighbors 
                if x in range(SIZE) and y in range(SIZE)])

def update(lit, with_corners):
    new_lit = set()
    for l in lit:
        neighbors = get_neighbors(l)
        if len(lit.intersection(neighbors)) in [2,3]:
            new_lit.add(l)
        for neighbor in neighbors:
            if neighbor in lit: continue
            n = get_neighbors(neighbor)
            if len(lit.intersection(n)) == 3:
                new_lit.add(neighbor)
    if with_corners:
        new_lit.update(corners)
    return new_lit

def solve(with_corners):
    lit = set()
    if with_corners:
        lit.update(corners)
    for r in range(SIZE):
        for c in range(SIZE):
            if input[r][c] == '#':
                lit.add((r,c))
    for _ in range(100):
        lit = update(lit, with_corners)
    return len(lit)

print("Part 1:", solve(False))
print("Part 2:", solve(True))
