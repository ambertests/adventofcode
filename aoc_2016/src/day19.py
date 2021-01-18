def white_elephant(n, part):
    elves = [1 for i in range(n)]
    final_elf = -1
    while final_elf < 0:
        for i in range(len(elves)):
            if elves[i] == 0: continue
            circle = [a for a in range(len(elves)) if elves[a] > 0]
            if len(circle) == 1:
                final_elf = circle[0] + 1
                break
            e = circle.index(i)
            if part == 1:
                x = e + 1
            else: 
                x = e + int(len(circle)/2)
            if x >= len(circle): x = x - len(circle)
            elves[i] += elves[circle[x]]
            elves[circle[x]] = 0
    return final_elf

# the actual game play is pretty slow for part 1 and too slow for 
# part 2, but it came in handy for testing the algorthims below
def part1(num):
    elf = 1
    for i in range(1,num):
        if i == elf:
            elf = 1
        else: elf += 2
    return elf

def part2(num):
    elf = 1
    add = 1
    for i in range(1,num):
        if i == elf:
            elf = 1
            add = 1
        else:
            if elf*2 == i:
                add = 2
            elf += add
    return elf

test=5
day19=3014387
print("Part 1:", part1(day19))
print("Part 2:", part2(day19)) 

# for i in range(1,21):
#     print(i, white_elephant(i, 2), part2(i))
