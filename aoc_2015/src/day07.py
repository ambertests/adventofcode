
mapping = {}

def get_input():
    return [line.strip() for line in open("input/day07.txt").readlines()]

def process_input(instructions, b_override=None):
    while len(instructions) > 0:
        for line in instructions:
            val, dest = line.split(' -> ')
            s_val = val.split(' ')
            if dest == 'b' and b_override is not None:
                mapping[dest] = b_override
                instructions.remove(line)
            elif len(s_val) == 1:
                if val.isnumeric():
                    mapping[dest] = int(val)
                    instructions.remove(line)
                elif val in mapping:
                    mapping[dest] = mapping[val]
                    instructions.remove(line)
            elif len(s_val) == 2:
                op, n = tuple(s_val)
                if op == 'NOT':
                    if n in mapping:
                        mapping[dest] = 65535 - mapping[n]
                        instructions.remove(line)
                    elif n.isnumeric():
                        mapping[dest] = 65535 - int(n)
                        instructions.remove(line)
            else:
                x, op, y = tuple(s_val)
                if x in mapping or x.isnumeric():
                    if x.isnumeric(): x = int(x)
                    else: x = mapping[x]
                    if y in mapping or y.isnumeric():
                        if y.isnumeric(): y = int(y)
                        else: y = mapping[y]
                        if op == 'AND':
                            mapping[dest] = x & y
                        elif op == 'OR':
                            mapping[dest] = x | y
                        elif op == 'LSHIFT':
                            mapping[dest] = x << y
                        elif op == 'RSHIFT':
                            mapping[dest] = x >> y
                        instructions.remove(line)

def solve():
    process_input(get_input())
    map_a = mapping['a']
    print("Part 1:", map_a)
    mapping.clear()
    process_input(get_input(), map_a)
    print("Part 2:", mapping['a'])

solve()
