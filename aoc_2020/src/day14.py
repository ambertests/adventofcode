from itertools import product

input = [line.strip() for line in open('input/day14.txt').readlines()]

def parse_line(line):
    mask = None
    index = None
    val = None
    if line.startswith('mask'):
        mask = line[-36:]
    else:
        split = line.split(' = ')
        index = int(split[0][4:][:-1])
        val = int(split[1])
    return mask, index, val

def apply_mask_to_value(mask, num):
    b = list(bin(num)[2:].zfill(36))
    for m in range(len(mask)):
        if mask[m] == 'X': continue
        b[m] = mask[m]
    return int(''.join(b), 2)

def apply_mask_to_address(mask, num):
    b = list(bin(num)[2:].zfill(36))
    addresses = []
    float_indexes = []
    for m in range(len(mask)):
        if mask[m] == '0': continue
        if mask[m] == 'X':
            float_indexes.append(m)
        b[m] = mask[m]
    prod = list(product(list('01'), repeat=len(float_indexes)))
    for p in prod:
        for i in range(len(p)):
            b[float_indexes[i]] = p[i]
        addresses.append(int(''.join(b), 2))
    return addresses

def solve(part_2=False):
    mem = {}
    mask = None
    for line in input:
        m, i, v = parse_line(line)
        if m: mask = m
        else:
            if not part_2:
                mem[i] = apply_mask_to_value(mask, v)
            else:
                indexes = apply_mask_to_address(mask, i)
                for index in indexes:
                    mem[index] = v
    return sum(mem.values())

print("Part 1:", solve())
print("Part 2:", solve(True))

