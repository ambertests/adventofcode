# Started out trying to represent the hex tile as a linked list with six
# links for the six directions, but setting up all the cross links was way
# too complicated and I ended up missing some. After peeking at the sub-reddit,
# I picked up on just using decimals to represent tile movement and after that
# it was pretty easy. 
def get_path(line):
    path = []
    for i in range(len(line)):
        if i > 0 and line[i-1] in ['s', 'n']:
            continue
        dir = line[i]
        if dir in ['s', 'n']:
            dir = line[i:i+2]
        path.append(dir)
    return path

def walk_path(path):
    tile = (0,0)
    x,y = tile
    for p in path:
        if p == 'e': tile = (x,y+1)
        elif p == 'w': tile = (x,y-1)
        elif p == 'nw': tile = (x+0.5,y-0.5)
        elif p == 'ne': tile = (x+0.5,y+0.5)
        elif p == 'sw': tile = (x-0.5,y-0.5)
        elif p == 'se': tile = (x-0.5,y+0.5)
        x,y = tile
    return tile

def get_neighbors(tile):
    x,y = tile
    return {
        (x,y+1),
        (x,y-1),
        (x+0.5,y-0.5),
        (x+0.5,y+0.5),
        (x-0.5,y-0.5),
        (x-0.5,y+0.5)
    }

def flip_tiles(black_tiles):
    new_black = set()
    for b in black_tiles:
        neighbors = get_neighbors(b)
        # black tiles with 1 or 2 black neighbors stay black
        if len(black_tiles.intersection(neighbors)) in [1,2]:
            new_black.add(b)
        # since white tiles are flipped if they have 2 black
        # neighbors, we only need to check white tiles that
        # are adjacent to black tiles
        checked = set()
        for n in neighbors:
            if n in black_tiles or n in checked: continue
            if len(black_tiles.intersection(get_neighbors(n))) == 2:
                new_black.add(n)
            checked.add(n)
    return new_black

def solve(file):
    input = [line.strip() for line in open("input/" + file).readlines()]
    # the puzzle only cares about black tile counts,
    # so we don't need a grid of all the tiles, just
    # a set of black ones
    black_tiles = set()
    for line in input:
        end = walk_path(get_path(line))
        if end in black_tiles:
            black_tiles.remove(end)
        else:
            black_tiles.add(end)

    print("Part 1:", len(black_tiles))

    for _ in range(100):
        black_tiles = flip_tiles(black_tiles)

    print("Part 2:", len(black_tiles))

solve("day24.txt")