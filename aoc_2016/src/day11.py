from itertools import combinations
import time
L, H, T, R, C, S, P, E, D = 1,2,3,4,5,6,7,8,9
test = {
    4:set(),
    3:{-L},
    2:{-H},
    1:{H,L}
}

day11 = {
    4:set(),
    3:{T},
    2:{-T, -R, R, -C, C},
    1:{-S, S, -P, P}
}

day11_2 = {
    4:set(),
    3:{T},
    2:{-T, -R, R, -C, C},
    1:{-S, S, -P, P, -E, E, -D, D}
}

items = None
valid_hands = None
valid_floors = None
def do_prep(bldg):
    global items, valid_hands, valid_floors
    items = set()
    for f in test:
        items.update(bldg[f])

    # print('\n** valid pairs **')
    valid_pairs = [set(c) for c in combinations(items, 2) 
                        if abs(c[0]) == abs(c[1]) or (c[0] < 0 and c[1] < 0) 
                                                        or (c[0] > 0 and c[1] > 0)]
                        #if c[0][0] == c[1][0] or c[0][1] == c[1][1]]
    # print(valid_pairs)

    # print('\n** matched sets **')
    matched_sets = [set(v )for v in [tuple(p) for p in valid_pairs] if abs(v[0]) == abs(v[1])]
    # print(matched_sets)

    # print('\n** valid hands **')
    valid_hands = [set([i]) for i in items]
    valid_hands.extend(valid_pairs)
    # print(valid_hands, len(valid_hands))valid_floors = [set()]
    valid_floors = [set()]
    valid_floors.extend([v for v in valid_hands])
    for r in range(3, len(items)+1):
        valid_floors.extend([set(c) for c in combinations(items, r) 
                                if is_valid_floor(set(c), matched_sets)])

    # print('\n** valid floors **')
    # print(valid_floors, len(valid_floors))

def is_valid_floor(floor, matched_sets):
    if len(floor) < 2:
        return True
    check = floor.copy()
    for pair in matched_sets:
        p = set(pair)
        if check.intersection(p) == p:
            check -= p
    for c in check:
        if c > 0:
            return False
    return True

def next_floors(current_floor):
    if current_floor == 1:
        return [2]
    elif current_floor == 4:
        return [3]
    else:
        return [current_floor + 1, current_floor - 1]

def done(bldg):
    return len(bldg[4]) == len(items)

def move_items(items, floor, bldg):
    new_bldg = {i+1:set() for i in range(4)}
    for f in bldg:
        if f != floor:
            new_bldg[f] = bldg[f] - items
        else:
            new_bldg[f] = bldg[f] | items
    return new_bldg

def find_possible_moves(current_floor, bldg):
    moves = []
    possibles = [v for v in valid_hands 
                    if v & bldg[current_floor] == v]
    has_pair = False
    for items in possibles:
        for floor in next_floors(current_floor):
            if bldg[current_floor] - items in valid_floors and \
                bldg[floor] | items in valid_floors:
                moves.append(list(items) + [floor])
                if len(items) == 2: has_pair = True
    # optimizations from https://www.reddit.com/r/adventofcode/comments/5hoia9/2016_day_11_solutions/db1v1ws
    # first is to only take pairs upstairs and singletons downstairs
    if has_pair:
        moves = [m for m in moves if (len(m) == 3 and m[2] > current_floor) or
                    (len(m) == 2 and m[1]< current_floor)]
    return moves
# second optimization is to stop not only on equal buildings
# but also buildings where the floors have the same ratio of M/G
def is_equivalent(bldg, prev):
    if bldg in prev:
        return True
    for p in reversed(prev):
        floor_diff = False
        for f in bldg:
            if len(p[f]) == len(bldg[f]):
                p_gen = len([i for i in p[f] if i < 0])
                b_gen = len([i for i in bldg[f] if i < 0])
                if p_gen != b_gen:
                    floor_diff = True
                    break
            else:
                floor_diff = True
                break
        if not floor_diff:
            return True
    return False

valid_paths = []
max_path = 0
path_count = 0
failures = {
    'too_long':0,
    'dup_bldg':0,
    'shuffle':0,
    'no_moves':0
}
import time
def get_all_paths(path, floor, bldg, prev=[]):
    global path_count
    path_count += 1
    if path_count % 1000000 == 0:
        print(path_count, ':', max_path)
        print(failures)
    if done(bldg):
        #print('we did it!!!', len(path))
        valid_paths.append(path)
        return
    if len(path) > max_path:
        #print('path longer than', max_path)
        failures['too_long'] += 1
        return
    if is_equivalent(bldg, prev[-10:]):
        #print('we\'ve been here before')
        failures['dup_bldg'] += 1
        return
    moves = find_possible_moves(floor, bldg)
    if not moves:
        failures['no_moves'] += 1
        return

    for move in moves:
        new_path = path.copy()
        new_path.append(move)
        new_items = set(move[:-1])
        new_floor = move[-1]
        new_prev = prev.copy()
        new_prev.append(bldg)
        new_bldg = move_items(new_items, new_floor, bldg)
        get_all_paths(new_path, new_floor, new_bldg, new_prev)

def solve(bldg):
    global max_path
    max_path = 65
    start = time.time()
    do_prep(bldg)
    get_all_paths([],1,bldg.copy())
    print(time.time()-start)
    print(failures)
    if valid_paths:
        print("Part 1:", min([len(v) for v in valid_paths]))
    else:
        print('no valid path')


print("Part 1:", solve(day11))
# it takes over 2 minutes to solve part 1 - so slow! part 2 would likely take hours
# the reddit forum had a very simple algorithmic solution for determining the minimum
# number of steps:
print("Part 2:", sum(2 * sum([8,5,1,0][:x]) - 3 for x in range(1,4)))
# the array is made up of the number of items on each floor. how does it work????
# something to do with the 2 items up, 1 item down pattern

#solve(day11_2)
#solve(test)