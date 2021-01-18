from itertools import combinations

def solve(group_size):
    packages = [int(l.strip()) for l in open("input/day24.txt").readlines()]
    group_weight = int(sum(packages)/group_size)
    combos = []
    r = 1
    while not len(combos):
        combos.extend([c for c in combinations(packages, r) 
                                if sum(list(c)) == group_weight])
        r += 1
    qe = set()
    for c in combos:
        p = 1
        for n in c:
            p *= n
        qe.add(p)
    return min(qe)

print("Part 1:", solve(3))
print("Part 2:", solve(4))
