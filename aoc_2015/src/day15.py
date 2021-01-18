import re
from itertools import permutations, combinations_with_replacement

input = [line.strip().split(': ') for line in open('input/day15.txt').readlines()]

i_vals = []
for line in input:
    name = line[0]
    i_vals.append([int(i) for i in re.findall("[-\d]+", str(line[1]))])

capacity = [v[0] for v in i_vals]
durability = [v[1] for v in i_vals]
flavor = [v[2] for v in i_vals]
texture = [v[3] for v in i_vals]
calories = [v[4] for v in i_vals]

def get_amounts():
    amounts = set()
    combos = [c for c in combinations_with_replacement(range(1,100), len(input)) 
                if sum(list(c)) == 100]
    for combo in combos:
        amounts.update(list(permutations(combo)))
    return amounts

def solve():
    scores = set()
    scores_500 = set()
    for amt in get_amounts():
        score = 1
        for vals in [capacity, durability, flavor, texture]:
            tot = sum([a*i for a, i in zip(amt, vals)])
            if tot < 0: 
                score = 0
                break
            score *= tot
        scores.add(score)
        if score > 0 and sum([a*i for a, i in zip(amt, calories)]) == 500:
            scores_500.add(score)
    return max(scores), max(scores_500)

pt1, pt2 = solve()
print("Part 1:", pt1)
print("Part 2:", pt2)


    