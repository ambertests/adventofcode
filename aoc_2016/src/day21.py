def swap_pos(a,x,y): 
    l = list(a)
    ax = a[x]
    ay = a[y]
    l[y] = ax
    l[x] = ay
    return ''.join(l)

def swap_letters(s, a, b):
    l = list(s)
    ai = l.index(a)
    bi = l.index(b)
    l[ai] = b
    l[bi] = a
    return ''.join(l)

def rotate(s, n):
    arr = [None for _ in range(len(s))]
    n = n%len(s)
    for i in range(len(arr)):
        pos = i+n
        if pos >= len(arr): pos = pos - len(arr)
        elif pos < 0: pos = pos + len(arr)
        arr[pos] = s[i]
    return ''.join(arr)

def rotate_pos(s, c, reverse=False):
    # map all the possible rotations
    rot = {i:i+1 if i < 4 else i+2 for i in range(len(s)) }
    rev = {}
    if reverse:
        for i in rot:
            # determine where the letter would have gone
            # and use that index for the reverse rotation
            dest = i + (rot[i]%len(s))
            if dest >= len(s): dest = dest - len(s)
            rev[dest] = -(rot[i])
    
    n = s.index(c)
    if not reverse:
        r = rotate(s, rot[n])
    else:
        r = rotate(s, rev[n])
    return r

def reverse(s, x, y):
    return s[:x] + ''.join(list(reversed(s[x:y+1]))) + s[y+1:]

def move(s, x, y):
    l = list(s)
    a = l.pop(x)
    l.insert(y, a)
    return ''.join(l)

def scramble(pwd, lines):
    for line in lines:
        sp = line.split(' ')
        cmd = sp[0]
        if cmd == 'swap':
            if sp[1] == 'position':
                pwd = swap_pos(pwd, int(sp[2]), int(sp[-1]))
            else:
                pwd = swap_letters(pwd, sp[2], sp[-1])
        elif cmd == 'rotate':
            if sp[1] == 'left':
                pwd = rotate(pwd, -(int(sp[2])))
            elif sp[1] == 'right':
                pwd = rotate(pwd, int(sp[2]))
            else:
                pwd = rotate_pos(pwd, sp[-1])
        elif cmd == 'reverse':
            pwd = reverse(pwd, int(sp[2]), int(sp[-1]))
        elif cmd == 'move':
            pwd = move(pwd, int(sp[2]), int(sp[-1]))
        else:
            print('unrecognized cmd:', cmd)
    return pwd

def unscramble(pwd, lines):
    for line in reversed(lines):
        sp = line.split(' ')
        cmd = sp[0]
        if cmd == 'swap':
            if sp[1] == 'position':
                pwd = swap_pos(pwd, int(sp[-1]), int(sp[2]))
            else:
                pwd = swap_letters(pwd, sp[-1], sp[2])
        elif cmd == 'rotate':
            if sp[1] == 'left':
                pwd = rotate(pwd, int(sp[2]))
            elif sp[1] == 'right':
                pwd = rotate(pwd, -(int(sp[2])))
            else:
                pwd = rotate_pos(pwd, sp[-1], True)
        elif cmd == 'reverse':
            pwd = reverse(pwd, int(sp[2]), int(sp[-1])) # this one stays the same
        elif cmd == 'move':
            pwd = move(pwd, int(sp[-1]), int(sp[2]))
        else:
            print('unrecognized cmd:', cmd)
    return pwd


input = [line.strip() for line in open('input/day21.txt').readlines()]
print('Part 1:', scramble('abcdefgh', input))
print('Part 2:', unscramble('fbgdceah', input))
