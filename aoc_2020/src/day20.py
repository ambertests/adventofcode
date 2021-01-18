
input = [block.split('\n') for block in open("input/day20.txt").read().split("\n\n")]
# This puzzle took me *ages* to solve. The code is ugly as hell, 
# but it runs pretty quickly - around 200ms for both solutions.
class Tile:
    def __init__(self, id, raw):
        self.id = id
        self.tile = [list(row) for row in raw]

    def __str__(self):
        return '\n'.join([''.join(row) for row in self.tile])

    def get_edges(self):
        # |-0-|
        # 3   1
        # |-2-|
        return [
            self.tile[0],
            [row[-1] for row in self.tile],
            self.tile[-1],
            [row[0] for row in self.tile]
        ]
    
    def is_adjacent(self, other):
        adj = False
        edges = self.get_edges()
        for e in other.get_edges():
            if e in edges or list(reversed(e)) in edges:
                adj = True
                break
        return adj

    def h_flip(self):
        self.tile = [list(reversed(row)) for row in self.tile]

    def v_flip(self):
        self.tile = [self.tile[i] for i in list(reversed(range(len(self.tile))))]

    def rotate(self):
        self.tile = [list(reversed([r[i] for r in self.tile])) 
                                for i in range(len(self.tile[0]))]

    def remove_frame(self):
        self.tile = [row[1:-1] for row in self.tile[1:-1]]


TILES = {} # tile objects indexed by key
N = {} # keys and sets of neighboring keys
for block in input:
    id = int(block[0][5:9])
    TILES[id] = Tile(id, block[1:])
SIZE = int(len(TILES)**0.5)
IMAGE = [[None for c in range(SIZE)] for r in range(SIZE)]
MONSTER = [
    '                  _ ',
    '\    /\    /\    /@<',
    ' \  /  \  /  \  /    '
]

def find_adjacent(key):
    adj = []
    tile = TILES[key]
    for t in TILES:
        if t == key: continue
        if tile.is_adjacent(TILES[t]): 
            adj.append(t)
    return adj

def join(a, b):
    a_side = -1
    b_side = -1
    do_reverse = False
    for a_i, a_e in enumerate(a.get_edges()):
        for b_i, b_e in enumerate(b.get_edges()):
            if a_e == b_e:
                a_side = a_i
                b_side = b_i
                break
            elif a_e == list(reversed(b_e)):
                a_side = a_i
                b_side = b_i
                do_reverse = True
                break
        if a_side >= 0: 
            break
    return a_side, b_side, do_reverse

def join_right(a, b):
    a_side, b_side, do_reverse = join(a,b)
    if a_side != 1:
        return False
    while b_side != 3:
        b.rotate()
        a_side, b_side, do_reverse = join(a,b)
    if do_reverse:
        b.v_flip()
        a_side, b_side, do_reverse = join(a,b)
    return a_side == 1 and b_side == 3 and not do_reverse

def join_bottom(a, b):
    a_side, b_side, do_reverse = join(a,b)
    if a_side != 2: 
        return False
    while b_side != 0:
        b.rotate()
        a_side, b_side, do_reverse = join(a,b)
    if do_reverse:
        b.h_flip()
        a_side, b_side, do_reverse = join(a,b)
    return a_side == 2 and b_side == 0 and not do_reverse

def orient_top_left_corner(corner_id):
    adj = N[corner_id]
    corner = TILES[corner_id]
    right = TILES[adj[0]]
    bottom = TILES[adj[1]]
    
    while join(corner,right)[0] != 1:
        corner.rotate()
    
    if join(corner,bottom)[0] != 2:
        corner.v_flip()


def make_row(r_index, c_index, left_id):
    for adj in N[left_id]:
        joined = join_right(TILES[left_id], TILES[adj])
        if joined: 
            IMAGE[r_index][c_index] = TILES[adj]
            if c_index < SIZE - 1:
                make_row(r_index, c_index + 1, adj)
            break

def start_new_row(r_index):
    top = IMAGE[r_index - 1][0]
    bottom = None
    for adj in N[top.id]:
        joined = join_bottom(top, TILES[adj])
        if joined: 
            IMAGE[r_index][0] = TILES[adj]
            bottom = adj
            break
    if bottom is None:
        print("could not start row", r_index)
    return bottom


def assemble_image():
    part1 = 1
    corners = []
    for key in TILES:
        adj = find_adjacent(key)
        N[key] = adj
        if len(adj) == 2:
            corners.append(key)
            part1 *= key
    print("Part 1:", part1)
    top_left = corners[0]
    orient_top_left_corner(top_left)
    IMAGE[0][0] = TILES[top_left]
    make_row(0,1,top_left)
    for r in range(1,SIZE):
        first = start_new_row(r)
        make_row(r, 1, first)

def merge_tiles():
    new_size = len(IMAGE[0][0].tile) - 2
    raw = [[] for i in range(new_size*SIZE)]
    offset = 0
    for r in range(SIZE):
        for c in range(SIZE):
            tile = IMAGE[r][c]
            tile.remove_frame()
            for i in range(new_size):
                raw[i+offset].extend(tile.tile[i])
        offset += new_size
    return Tile(0, raw)

def get_monster_deltas():
    deltas = set()
    m_arr = [list(row) for row in MONSTER]
    for row in range(len(m_arr)):
        for col in range(len(m_arr[row])):
            if m_arr[row][col] != ' ':
                deltas.add((row,col))
    return deltas

def find_monsters(T, deltas):
    monsters = []
    #deltas = get_monster_deltas()
    max_r_delta = max([d[0] for d in deltas])
    max_c_delta = max([d[1] for d in deltas])
    tile_size = len(T.tile)
    for r in range(0, tile_size - max_r_delta):
        for c in range(0, tile_size - max_c_delta):
            found = True
            for d in deltas:
                if T.tile[r + d[0]][c + d[1]] != '#':
                    found = False
                    break
            if found: monsters.append((r,c))
    return monsters

def find_monsters_in_tile(tile, deltas):
    monsters = find_monsters(tile, deltas)
    rotations = 0
    while len(monsters) == 0 and rotations < 3:
        rotations += 1
        tile.rotate()
        monsters = find_monsters(tile, deltas)
    if len(monsters) == 0:
        tile.v_flip()
        monsters = find_monsters(tile, deltas)
        rotations = 0
        while len(monsters) == 0 and rotations < 3:
            rotations += 1
            tile.rotate()
            monsters = find_monsters(tile, deltas)
    for m in monsters:
        for d in deltas:
            tile.tile[m[0]+d[0]][m[1]+d[1]] = MONSTER[d[0]][d[1]]
    return monsters

def solve():
    assemble_image()
    merged = merge_tiles()
    deltas = get_monster_deltas()
    find_monsters_in_tile(merged, deltas)
    print(merged)
    print("Part 2:", sum([len([c for c in r if c == '#']) for r in merged.tile]))


solve()



