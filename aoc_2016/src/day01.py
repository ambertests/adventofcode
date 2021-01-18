input = open("input/day01.txt").read().split(', ')
directions = ['N', 'E', 'S', 'W']
def walk_path(path):
    x, y = 0, 0
    facing = 0
    hq = (0,0)
    found_hq = False
    visited = set()
    for step in path:
        sub_path = []
        dir = step[0]
        count = int(step[1:])
        if dir == 'R':
            if facing < len(directions) - 1:
                facing += 1
            else:
                facing = 0
        elif dir == 'L':
            if facing == 0:
                facing = len(directions) - 1
            else:
                facing -= 1

        direction = directions[facing]
        new_x = x
        new_y = y
        if direction == 'N':
            new_x -= count
            for i in range(count):
                sub_path.append((x-i, y))
        elif direction == 'S':
            new_x += count
            for i in range(count):
                sub_path.append((x+i, y))
        elif direction == 'E':
            new_y += count
            for i in range(count):
                sub_path.append((x, y+i))
        elif direction == 'W':
            new_y -= count
            for i in range(count):
                sub_path.append((x, y-i))
        for p in sub_path:
            if p in visited and not found_hq:
                found_hq = True
                hq = p
            visited.add(p)
        x, y = new_x, new_y
    return abs(x) + abs(y), abs(hq[0]) + abs(hq[1])

print(walk_path(['R2', 'L3']))
print(walk_path(['R2', 'R2', 'R2']))
print(walk_path(['R5', 'L5', 'R5', 'R3']))
print(walk_path(['R8', 'R4', 'R4', 'R8']))

pt1, pt2 = walk_path(input)
print("Part 1:", pt1)
print("Part 2:", pt2)
    
