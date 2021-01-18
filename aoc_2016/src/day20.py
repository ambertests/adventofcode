test_max = 9
day20_max = 4294967295
blocked = [[int(i) for i in line.strip().split('-')]
                    for line in open('input/day20.txt')]
# sort by min block value
blocked.sort(key=lambda x:x[0])

current_max = 0
pt1 = 0
ok = 0
for i, block in enumerate(blocked):
    if block[0] > current_max + 1:
        if pt1 == 0:
            pt1 = current_max + 1
        ok += block[0] - 1 - current_max
    if block[1] > current_max:
        current_max = block[1]

if current_max < day20_max:
    ok += day20_max - current_max

print("Part 1:", pt1)
print("Part 2:", ok)