input = [line.strip().split(' ') for line in open('input/day15.txt').readlines()]

D = {}
    
for line in input:
    #Disc #1 has 5 positions; at time=0, it is at position 4.
    id = int(line[1][1])
    slots = int(line[3])
    pos = int(line[-1][:-1])
    D[id] = (slots,pos)

def get_mod(delay, slots, pos):
    # give the time delay and the
    # initial position of the disc
    # when would you have to drop 
    # the ball to hit slot 0
    time = 0
    while (time + delay + pos)%slots != 0:
        time += 1
    return time

def solve():
    mods = [0 for _ in range(len(D))]
    for i in range(len(D)):
        s, p = D[i+1]
        m = get_mod(i+1, s, p)
        mods[i] = (s,m)
    t = 0
    dropped = False
    # the time (t) modulo the number of 
    # slots (s) needs to equal the value
    # from get_mod (m) in order to pass 
    # through the disc: t%s == m
    while not dropped:
        t += 1
        d = True
        for i in mods:
            s, m = i
            if t%s != m:
                d = False
                break
        dropped = d
    return t

print("Part 1:", solve()) # 2ms
D[len(D)+1] = (11,0) # 7th disc
print("Part 2:", solve()) # 644ms
# D[len(D)+1] = (23,12) # 8th disc
# print("Part 3:", solve()) # 13s
# D[len(D)+1] = (31,6) # 9th disc
# print("Part 4:", solve()) # 4m
# # 10th disc would likely take over 90m
