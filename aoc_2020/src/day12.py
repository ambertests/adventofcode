input = [line.strip() for line in open('input/day12.txt').readlines()]

EAST = 'E'
WEST = 'W'
NORTH = 'N'
SOUTH = 'S'

def update_headings(headings, dir, num):
    opp = get_opposite(dir)
    if headings[opp] == 0:
        headings[dir] += num
    elif headings[opp] > 0:
        if headings[opp] >= num:
            headings[opp] -= num
        else:
            num -= headings[opp]
            headings[opp] = 0
            headings[dir] += num

def get_opposite(dir):
    if dir == NORTH: return SOUTH
    if dir == SOUTH: return NORTH
    if dir == EAST: return WEST
    if dir == WEST: return EAST

def get_90(dir):
    if dir == NORTH: return EAST
    if dir == SOUTH: return WEST
    if dir == EAST: return SOUTH
    if dir == WEST: return NORTH


def do_move(cmd, headings, current=None):
    dir = cmd[0]
    num = int(cmd[1:])
    if dir == 'F':
        update_headings(headings, current, num)
    else:
        update_headings(headings, dir, num)

def do_turn(cmd, current):
    new_dir = current
    dir = cmd[0]
    deg = int(cmd[1:])
    if dir == 'R':
        if deg == 90:
            new_dir = get_90(current)
        if deg == 180:
            new_dir = get_opposite(current)
        if deg == 270:
            new_dir = get_opposite(get_90(current))
    else:
        if deg == 90:
            new_dir = get_opposite(get_90(current))
        if deg == 180:
            new_dir = get_opposite(current)
        if deg == 270:
            new_dir = get_90(current)
    return new_dir


def part_1():
    ship_location = {}
    ship_location[EAST] = 0
    ship_location[WEST] = 0
    ship_location[NORTH] = 0
    ship_location[SOUTH] = 0

    current = EAST
    for cmd in input:
        if cmd.startswith('L') or cmd.startswith('R'):
            current = do_turn(cmd, current)
        else:
            do_move(cmd, ship_location, current)
    return sum(ship_location.values())
            
print("Part 1:", part_1())

def part_2():
    ship_location = {}
    ship_location[EAST] = 0
    ship_location[WEST] = 0
    ship_location[NORTH] = 0
    ship_location[SOUTH] = 0

    waypoint = {}
    waypoint[EAST] = 10
    waypoint[WEST] = 0
    waypoint[NORTH] = 1
    waypoint[SOUTH] = 0

    for cmd in input:
        action = cmd[0]
        num = int(cmd[1:])
        if action == 'F':
            for key in waypoint:
                if waypoint[key] > 0:
                    update_headings(ship_location, key, waypoint[key]*num)
        elif action in ['N', 'S', 'E', 'W']:
            do_move(cmd, waypoint)
        elif action in ['R', 'L']:
            to_update = []
            for key in waypoint:
                if waypoint[key] > 0:
                    new_dir = do_turn(cmd, key)
                    to_update.append((new_dir, waypoint[key]))
                    waypoint[key] = 0
            for t in to_update:
                waypoint[t[0]] = t[1]
    return sum(ship_location.values())

print("Part 2:", part_2())