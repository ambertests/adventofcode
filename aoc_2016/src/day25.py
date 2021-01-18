
def get_val(x, registers):
    if x.isalpha():
        num = registers[x]
    else: 
        num = int(x)
    return num

def run_program(a_val):
    input = [line.strip().split(' ') for line in open('input/day25.txt').readlines()]
    registers = {'a':a_val,'b':0,'c':0,'d':0}
    i = 0
    output = []
    while i < len(input):
        line = input[i]
        cmd = line[0]
        if cmd == 'out':
            val = get_val(line[1], registers)
            output.append(val)
            if len(output) == 10:
                i += len(input)
            else:
                i += 1
        elif cmd == 'cpy':
            num = get_val(line[1], registers)
            reg = line[2]
            if reg in registers:
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
            val = get_val(line[1], registers)
            num  = get_val(line[2], registers)
            if val != 0:
                i += num
            else:
                i += 1
    return ''.join([str(o) for o in output])

i = 0
output = ''
while output != '0101010101':
    i += 4 # all multiples of 4 start with '01', so this cuts runtime by 75%
    output = run_program(i)

print("Part 1:", i)
