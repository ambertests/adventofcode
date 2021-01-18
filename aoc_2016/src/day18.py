
test1 = '..^^.'
test2 = '.^^.^.^^^^'
day18 = '.^..^....^....^^.^^.^.^^.^.....^.^..^...^^^^^^.^^^^.^.^^^^^^^.^^^^^..^.^^^.^^..^.^^.^....^.^...^^.^.'

def solve(first, row_count):
    traps = set()
    safe = set()
    [traps.add((i,0)) if first[i] == '^' else safe.add((i,0)) 
                                        for i in range(len(first))]
    for y in range(1, row_count):
        for x in range(len(first)):
            tile = (x, y)
            left = (x-1, y-1) in traps
            center = (x, y-1) in traps
            right = (x+1, y-1) in traps
            if left and center and not right:
                traps.add(tile)
            elif center and right and not left:
                traps.add(tile)
            elif left and not center and not right:
                traps.add(tile)
            elif right and not center and not left:
                traps.add(tile)
            else:
                safe.add(tile)
    return len(safe)

# print(solve(test1, 3) == 6)
# print(solve(test2, 10) == 38)
print("Part 1:", solve(day18, 40)) # 2035
print("Part 2:", solve(day18, 400000)) # 20000577
# part 2 is pretty slow (> 1m) - maybe there is an algorithm?