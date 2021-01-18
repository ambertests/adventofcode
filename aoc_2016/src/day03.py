import re
input = [[int(i) for i in re.findall("[-\d]+", line)] 
            for line in open("input/day03.txt").readlines()]

def is_triangle(sides):
    return  sides[0] + sides[1] > sides[2] and \
            sides[1] + sides[2] > sides[0] and \
            sides[2] + sides[0] > sides[1]

def part1():
    valid = 0
    for i in input:
        if is_triangle(i): 
            valid += 1
    return valid

def part2():
    valid = 0
    for col in range(3):
        for row in range(0, len(input), 3):
            a = input[row][col]
            b = input[row+1][col]
            c = input[row+2][col]
            if is_triangle([a,b,c]):
                valid += 1
    return valid

print("Part 1:", part1())
print("Part 2:", part2())

