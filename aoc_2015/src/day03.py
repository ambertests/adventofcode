input = open("input/day03.txt").read().strip()

def solve_part1():
    house = (0,0)
    houses = {house}
    for c in input:
        x,y = house
        if c == '^': x += 1
        elif c == 'v': x -= 1
        elif c == '>': y += 1
        elif c == '<': y -= 1
        next = (x,y)
        houses.add(next)
        house = next
    return len(houses)

def solve_part2():
    santa = (0,0)
    robo = (0,0)
    houses = {santa,robo}
    for i in range(len(input)):
        c = input[i]
        if i %2 == 0:
            house = santa
        else:
            house = robo
        x,y = house
        if c == '^': x += 1
        elif c == 'v': x -= 1
        elif c == '>': y += 1
        elif c == '<': y -= 1
        next = (x,y)
        houses.add(next)
        if i %2 == 0:
            santa = next
        else:
            robo = next
    return len(houses)

print("Part 1:", solve_part1())
print("Part 2:", solve_part2())

