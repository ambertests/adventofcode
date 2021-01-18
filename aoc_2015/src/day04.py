from hashlib import md5
part1 = 0
part2 = 0
num = 1
key = 'ckczppom'
while part1 == 0 or part2 == 0:
    hex = md5((key + str(num)).encode()).hexdigest()
    if part1 == 0 and hex.startswith('00000'):
        part1 = num
    elif part2 == 0 and hex.startswith('000000'):
        part2 = num
    num += 1

print("Part 1:", part1)
print("Part 2:", part2)