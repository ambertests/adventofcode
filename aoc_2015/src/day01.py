input = open("input/day01.txt").read().strip()
up = 0
down = 0
basement = -1
for i in range(len(input)):
    if input[i] == '(': up += 1
    else: down += 1
    if basement < 0 and up - down == -1:
        basement = i+1

print('Part 1:', up - down)
print('Part 2:', basement)