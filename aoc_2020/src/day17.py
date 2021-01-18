input = [list(line.strip()) for line in open('input/day17.txt').readlines()]

# https://github.com/jonathanpaulson/AdventOfCode/blob/master/2020/17.py
def solve(part2=False):
    active = set()
    # add initial active points to set
    for i, l in enumerate(input):
        for j, c in enumerate(l):
            if c == '#':
                active.add((i, j, 0, 0))

    for _ in range(6):
        new_active = set()
        # we only need to check active points and points directly adjacent
        to_check = set()
        for (x,y,z,w) in active:
            for dx in [-1,0,1]:
                for dy in [-1,0,1]:
                    for dz in [-1,0,1]:
                        for dw in [-1,0,1]:
                            if w+dw==0 or (part2):
                                to_check.add((x+dx, y+dy, z+dz, w+dw))
        for (x,y,z,w) in to_check:
            n = 0
            for dx in [-1,0,1]:
                for dy in [-1,0,1]:
                    for dz in [-1,0,1]:
                        for dw in [-1,0,1]:
                            # make sure it isn't just the same point
                            if not dx == dy == dz == dw == 0:
                                if (x+dx, y+dy, z+dz, w+dw) in active:
                                    n += 1
            # 3 active neighbors activates an inactive point
            if (x,y,z,w) not in active and n == 3:
                new_active.add((x,y,z,w))
            # active points must have 2 or 3 active neighbors to stay active
            if (x,y,z,w) in active and n in [2,3]:
                new_active.add((x,y,z,w))
        active = new_active
    return len(active)

print("Part 1:", solve())
print("Part 2:", solve(True))


    


