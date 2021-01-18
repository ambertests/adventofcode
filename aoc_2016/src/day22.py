def get_node_info(file):
    nodes = set()
    df = [line.strip().split() for line 
                in open('input/%s.txt' % file).readlines() 
                            if line.startswith('/dev')]
    for nodeinfo in df:
    # Filesystem              Size  Used  Avail  Use%
    # /dev/grid/node-x0-y0     92T   68T    24T   73%
        _, x, y = nodeinfo[0].split('-')
        size = int(nodeinfo[1][:-1])
        used = int(nodeinfo[2][:-1])
        node = (int(x[1:]), int(y[1:]), size, used)
        nodes.add(node)
    return nodes

def get_pair_count(nodes):
    pair_count = 0
    for a in nodes:
        ax, ay, _, au = a
        if au == 0: continue
        for b in nodes:
            bx, by, bs, bu = b
            if bx == ax and by == ay:
                continue
            if au <= (bs-bu):
                pair_count += 1
    return pair_count

def make_grid(nodes, reg):
    max_x = max([n[0] for n in nodes])
    max_y = max([n[1] for n in nodes])
    grid = [[None for x in range(max_x + 1)]for y in range(max_y + 1)]
    walls = []
    empty = (0,0)
    for node in nodes:
        x, y, _, u = node
        if u == 0: 
            char = '0'
            empty = (x,y)
        elif u > reg: 
            char = '#'
            walls.append((x,y))
        else: char = '.'
        grid[y][x] = char
    grid[0][max_x] = 'D'
    return grid, max_x, empty, walls

def swap(a,b,grid):
    new = copy_grid(grid)
    ax, ay = a
    bx, by = b
    node_a = grid[ay][ax]
    node_b = grid[by][bx]
    new[by][bx] = node_a
    new[ay][ax] = node_b
    return new
 
def copy_grid(grid):
    return [[x for x in y] for y in grid]

def find_empty(grid):
    for y in range(len(grid)):
        for x in range(len(grid[0])):
            if grid[y][x] == '0':
                return (x,y)

def find_data(grid):
    for y in range(len(grid)):
        for x in range(len(grid[0])):
            if grid[y][x] == 'D':
                return (x,y)

def print_grid(grid):
    for row in grid:
        print(' '.join(row))
    print()

def solve(input, reg):
    nodes = get_node_info(input)
    print("Part 1:", get_pair_count(nodes))
    
    grid, max_x, empty, walls = make_grid(nodes, reg)
    goal = (0,0)
    data = (max_x, 0)
    wall_edge = min([w[0] for w in walls if w[1] < empty[1]])
    step_count = 0

    # move to left of wall
    old_empty = empty
    while empty[0] >= wall_edge:
        empty = (empty[0] - 1, empty[1])
        step_count += 1
    grid = swap(old_empty, empty, grid)

    # go up to top row
    old_empty = empty
    while empty[1] != 0:
        empty = (empty[0], empty[1] - 1)
        step_count += 1
    grid = swap(old_empty, empty, grid)

    # move next to data
    old_empty = empty
    while empty[0] != data[0] - 1:
        empty = (empty[0] + 1, empty[1])
        step_count += 1
    grid = swap(old_empty, empty, grid)

    # swap data, move empty around to left, repeat
    while data != goal:
        grid = swap(empty, data, grid)
        empty = find_empty(grid)
        data = find_data(grid)
        step_count += 1
        if data != goal:
            # down 1, left 2, up 1
            old_empty = empty
            empty = (empty[0], empty[1] + 1)
            grid = swap(empty, old_empty, grid)
            step_count += 1

            old_empty = empty
            empty = (empty[0]-2, empty[1])
            grid = swap(empty, old_empty, grid)
            step_count += 2

            old_empty = empty
            empty = (empty[0], empty[1] - 1)
            grid = swap(empty, old_empty, grid)
            step_count += 1
    
    print("Part 2:", step_count)

    
solve('day22', 100)

