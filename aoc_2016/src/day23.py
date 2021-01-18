
def do_toggle(input, num):
    if num < len(input):
        line = input[num]
        cmd = line[0]
        if cmd == 'inc':
            line[0] = 'dec'
        elif cmd in ['dec', 'tgl']:
            line[0] = 'inc'
        elif cmd == 'jnz':
            line[0] = 'cpy'
        elif cmd == 'cpy':
            line[0] = 'jnz'
        else:
            print('unknown cmd')

def get_val(x, registers):
    try:
        num = int(x)
    except:
        num = registers[x] 
    return num

def run_program(a_val):
    input = [line.strip().split(' ') for line in open('input/day23.txt').readlines()]
    registers = {'a':a_val,'b':0,'c':0,'d':0}
    i = 0
    while i < len(input):
        line = input[i]
        cmd = line[0]
        if cmd == 'tgl':
            val = get_val(line[1], registers)
            do_toggle(input, i+val)
            # print('[%d]' % i, 'toggled line', i+val)
            # for n, line in enumerate(input):
            #     print('[%d]' % n,' '.join(line))
            i += 1
        elif cmd == 'cpy':
            num = get_val(line[1], registers)
            reg = line[2]
            if reg in registers:
                registers[reg] = num
                #print('[%d]' % i, num, 'copied to', reg)
                i += 1
        elif cmd == 'inc':
            reg = line[1]
            registers[reg] += 1
            #print('[%d]' % i, 'increased', reg,  'to', registers[reg])
            i += 1
        elif cmd == 'dec':
            reg = line[1]
            registers[reg] -= 1
            #print('[%d]' % i, 'decreased', reg, 'to', registers[reg])
            i += 1
        elif cmd == 'jnz':
            val = get_val(line[1], registers)
            num  = get_val(line[2], registers)
            if val != 0:
                #print('[%d]' % i,'jumping to line', i+num)
                i += num
            else:
                i += 1
    return registers['a']


print(run_program(7))
#print(run_program(12))
#https://www.reddit.com/r/adventofcode/comments/5jvbzt/2016_day_23_solutions/dbjbldd
import math
print(math.factorial(12) + (72*77))