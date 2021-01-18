input = [line.strip() for line in open('input/day13.txt').readlines()]


def part_1():
    timestamp = int(input[0])
    buses = [int(i) for i in 
                filter(lambda x: x != 'x', input[1].split(',')) ]
    bus_id = 0
    stop_time = 100000000
    for bus in buses:
        next_bus = (bus * (timestamp // bus)) + bus
        if next_bus >= 0 and next_bus < stop_time:
            bus_id = bus
            stop_time = next_bus
    return (stop_time - timestamp) * bus_id

print("Part 1:", part_1())

# Chinese Remainder Theorem - WTF????
# https://github.com/sophiebits/adventofcode/blob/main/2020/day13.py
def crt(pairs):
    M = 1
    for x, mx in pairs:
        M *= mx # get the least common factor for all the mod values
    total = 0
    for x, mx in pairs:
        b = M // mx # extract the mod from the LCF
        # this next line makes no sense to me...
        total += x * b * pow(b, mx-2, mx) 
        total %= M # mod the total by the LCF
    return total

def part_2(input):
    pairs = []
    for i, n in enumerate(input.split(',')):
        if n == 'x':
            continue
        n = int(n)
        pairs.append((n - i, n))
    return crt(pairs)

print("Part 2:", part_2(input[1]))

# I cribbed the Part 2 solution above with no real 
# understanding of how it worked. I still don't *get*
# the CRT, but I was able to solve a similar problem, 
# AOC 2016.15, with modular arithmetic. So I ported
# that solution over here to see if it would work. 
# It *does* work for the examples, but is still way
# too slow for the actual problem. The final example
# takes several minutes to solve, so the actual problem
# would take days. I guess that's the magic of CRT...

def process_input(input):
    B = []
    buses = input.split(',')
    #17,x,13,19
    for i in range(len(buses)):
        if buses[i] == 'x': continue
        interval = int(buses[i])
        delay = i
        time = 0
        while (time + delay)%interval != 0:
            time += 1
        B.append((time, interval))
    return B

def solve(input):
    # this brute force method works ok for a 
    # small set with relatively low values (<100),
    # but is very slow for larger sets with
    # larger values
    B = process_input(input)
    t = 0
    arrived = False
    while not arrived:
        t += 1
        a = True
        for b in B:
            m, i = b
            if t%i != m:
                a = False
                break
        arrived = a
    return t

print(solve('7,13,x,x,59,x,31,19')) #1068781 242ms
print(solve('17,x,13,19')) #3417 < 1ms
print(solve('67,7,59,61')) #754018 137ms
print(solve('67,x,7,59,61')) #779210 145ms
print(solve('67,7,x,59,61')) #1261476 218ms
# start = time()
# print(solve('1789,37,47,1889'), time()-start) #1202161486 263s (> 4m)
# start = time()
# print(solve(input[1]), time()-start) #554865447501099 LOL...
