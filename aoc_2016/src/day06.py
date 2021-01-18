from collections import Counter

lines = [list(line.strip()) for line 
                in open("input/day06.txt").readlines()]
cols = [[row[i] for row in lines] for i in range(len(lines[0]))]
max_char = [Counter(c).most_common()[0][0] for c in cols]
min_char = [Counter(c).most_common()[-1][0] for c in cols]

print("Part 1:", ''.join(max_char))
print("Part 2:", ''.join(min_char))

