input = [line.strip().split(' ') for line in open('input/day12.txt').readlines()]
registers = {
    'a':0,
    'b':0,
    'c':0,
    'd':0
}
def run_program(part=1):
    if part > 1:
        registers['c'] = 1
    i = 0
    while i < len(input):
        line = input[i]
        cmd = line[0]
        if cmd == 'cpy':
            if line[1].isdigit():
                num = int(line[1])
            else:
                num = registers[line[1]]
            reg = line[2]
            registers[reg] = num
            i += 1
        elif cmd == 'inc':
            reg = line[1]
            registers[reg] += 1
            i += 1
        elif cmd == 'dec':
            reg = line[1]
            registers[reg] -= 1
            i += 1
        elif cmd == 'jnz':
            flag = line[1]
            if flag.isdigit():
                val = int(flag)
            else:
                val = registers[flag]
            num = int(line[2])
            if val != 0:
                i += num
            else:
                i += 1
    return registers['a']

print(run_program(1))
print(run_program(2))