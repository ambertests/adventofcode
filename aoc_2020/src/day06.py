input = open('input/day06.txt').read().split("\n\n")
total_yes = 0
for i in input:
    yes = set()
    for line in i.split("\n"):
        for c in line:
            yes.add(c)
    total_yes += len(yes)

print("Part 1:", total_yes)

total_common = 0
for i in input:
    yes_sets = []
    for line in i.split("\n"):
        yes_sets.append(set(line))
    common = yes_sets[0]
    for n in range(1, len(yes_sets)):
        common = common & yes_sets[n]
    total_common += len(common)

print("Part 2:", total_common)