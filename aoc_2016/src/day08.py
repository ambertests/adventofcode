input = [line.strip() for line in open('input/day08.txt').readlines()]
width = 50
height = 6

S = [['.' for w in range(width)] for h in range(height)]

def dump():
    for r in S:
        print(''.join(r))
    print()

def do_rect(inst):
    w, h = [int(s) for s in inst.split(' ')[1].split('x')]
    for r in range(h):
        for c in range(w):
            S[r][c] = '#'

def do_rotate(inst):
    sp = inst.split(' ')
    idx = int(sp[2].split('=')[1])
    amt = int(sp[-1])
    if sp[1] == 'row':
        new = ['.' for _ in range(width)]
        for i in range(width):
            l = i+amt
            if l >= width: l = l-width
            new[l] = S[idx][i]
        S[idx] = new
    else:
        col = [r[idx] for r in S]
        new = ['.' for _ in range(height)]
        for i in range(height):
            l = i+amt
            if l >= height: l = l-height
            new[l] = col[i]
        for h in range(height):
            S[h][idx] = new[h]

for line in input:
    if line.startswith('rect'):
        do_rect(line)
    else:
        do_rotate(line)

print("Part 1:", sum([len([c for c in r if c == '#']) for r in S]))
print("Part 2:")
dump() #UPOJFLBCEZ


