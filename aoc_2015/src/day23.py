
def load_instructions(file):
    instructions = []
    f = 'input/%s.txt' % file
    for line in [line.strip() for line in open(f).readlines()]:
        instr = line[:3]
        if instr in ['hlf', 'tpl', 'inc']:
            reg = line[4:]
            instructions.append((instr, reg))
        elif instr == 'jmp':
            offset = int(line[4:])
            instructions.append((instr, offset))
        elif instr in ['jie', 'jio']:
            reg = line[4:5]
            offset = int(line[7:])
            instructions.append((instr, reg, offset))
    return instructions

def run_program(file, a_val=0):
    program = load_instructions(file)
    registers = {'a':a_val, 'b':0}
    next = 0
    while next < len(program):
        inst = program[next]
        cmd = inst[0]
        reg = inst[1]
        if cmd == 'hlf':
            registers[reg] = int(registers[reg]/2)
            next += 1
        elif cmd == 'tpl':
            registers[reg] *= 3
            next += 1
        elif cmd == 'inc':
            registers[reg] += 1
            next += 1
        elif cmd == 'jmp':
            next += reg
        elif cmd == 'jie':
            offset = inst[2]
            if not registers[reg] % 2:
                next += offset
            else:
                next += 1
        elif cmd == 'jio':
            offset = inst[2]
            if registers[reg] == 1:
                next += offset
            else:
                next += 1
    return registers


reg = run_program('day23')
print("Part 1:", reg['b'])
reg = run_program('day23', 1)
print("Part 2:", reg['b'])





