import re

input = [line.strip() for line in open("input/day06.txt").readlines()]
LIGHTS = []

def process_line(line, pt1=True):
    nums = [int(d) for d in re.findall(r'\d+', line)]
    toggle = line.startswith('toggle')
    turn_on = line.startswith('turn on')
    turn_off = line.startswith('turn off')
    for r in range(nums[0], nums[2]+1):
        for c in range(nums[1], nums[3]+1):
            if turn_on:
                if pt1: LIGHTS[r][c] = 1
                else: LIGHTS[r][c] += 1
            elif turn_off:
                if pt1: LIGHTS[r][c] = 0
                elif LIGHTS[r][c] > 0: LIGHTS[r][c] -= 1
            elif toggle:
                if pt1:
                    if LIGHTS[r][c] == 0:
                        LIGHTS[r][c] = 1
                    else:
                        LIGHTS[r][c] = 0
                else:
                    LIGHTS[r][c] += 2


def solve(pt1=True):
    global LIGHTS
    LIGHTS = [[0 for i in range(1000)] for r in range(1000)]
    for line in input:
        process_line(line, pt1)
    on = sum([len([c for c in r if c == 1]) for r in LIGHTS])
    if pt1: return on
    else: return sum([sum(r) for r in LIGHTS])

print("Part 1:", solve())
print("Part 2:", solve(False))