import itertools
input = [line.strip()[:-1].split(' ') for line in open("input/day13.txt").readlines()]
guests = set()
happy = {}
for line in input:
    g1 = line[0]
    g2 = line[-1]
    guests.update([g1,g2])
    op = line[2]
    val = int(line[3])
    key = g1 + '-' + g2
    if op == 'lose': val = 0-val
    happy[key] = val


def get_max_happiness(seatings):
    max_change = 0
    for seating in seatings:
        change = 0
        for s in range(len(seating)):
            g1 = seating[s]
            if s == len(seating) -1:
                g2 = seating[0]
            else:
                g2 = seating[s+1]
            if g1 == 'Amber' or g2 == 'Amber': continue
            change += happy[g1 + '-' + g2]
            change += happy[g2 + '-' + g1]
        if change > max_change:
            max_change = change
    return max_change

seatings = itertools.permutations(list(guests), len(guests))
print("Part 1:", get_max_happiness(seatings))
guests.add('Amber')
seatings = itertools.permutations(list(guests), len(guests))
print("Part 2:", get_max_happiness(seatings))
