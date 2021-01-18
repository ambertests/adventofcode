from itertools import combinations
from collections import Counter
import math
containers = [int(i) for i in open("input/day17.txt").readlines()]
c_counter = Counter(containers)
C = set(containers)
for c in c_counter:
    # differentiate duplicate containers by adding a decimal value
    if c_counter[c] > 1:
        for i in range(1,c_counter[c]):
            C.add(c + (i*0.1))
            
combos = set()
for r in range(1,len(C)):
    combos.update(list(combinations(C, r)))

# remove the decimal value when compiling the valid set
valid = [[math.floor(i) for i in c] for c in combos 
            if sum([math.floor(i) for i in c]) == 150]

print("Part 1:", len(valid))

min_set = min([len(s) for s in valid])
print("Part 2:", len([s for s in valid if len(s) == min_set]))

