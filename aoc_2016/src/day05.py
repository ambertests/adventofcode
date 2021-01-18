from hashlib import md5
part1 = []
part2 = [None for _ in range(8)]
key = 'wtnhxymk'
num = 0
while len(part1) < 8 or None in part2:
    hex = md5((key + str(num)).encode()).hexdigest()
    if hex.startswith('00000'):
        if len(part1) < 8:
            part1.append(hex[5])
        if hex[5].isdigit() and int(hex[5]) < 8:
            idx = int(hex[5])
            if idx < 8 and part2[idx] is None:
                part2[idx] = hex[6]
    num += 1

print("Part 1:", ''.join(part1))
print("Part 2:", ''.join(part2))