import collections
input = [int(line.strip()) for line in open('input/day10.txt').readlines()]
input.sort()
ones = 1
threes = 1
for i in range(len(input) - 1):
    diff = input[i+1] - input[i]
    if diff == 1:
        ones += 1
    elif diff == 3:
        threes += 1
    else:
        print("diff is", diff)
print("Part 1:", ones * threes)

# https://www.reddit.com/r/adventofcode/comments/ka8z8x/2020_day_10_solutions/

combinations = collections.defaultdict(int, {0: 1})
for adapter in input:
    combinations[adapter] = combinations[adapter - 1] + combinations[adapter - 2] + combinations[adapter - 3]

# keys = [c for c in combinations]
# keys.sort()
# for k in keys:
#     print(k, combinations[k])

print("Part 2:", combinations[input[-1]])

