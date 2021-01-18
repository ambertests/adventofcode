from itertools import permutations
distances = {}
cities = set()

input = [line.strip().split(' = ') for line in open('input/day09.txt').readlines()]
for line in input:
    c, k = tuple(line)
    cities.update(c.split(' to '))
    distances[c] = int(k) 

combos = list(permutations(list(cities),len(cities)))

trips = set()
for c in combos:
    total_distance = 0
    for i in range(len(c)-1):
        k = ' to '.join([c[i], c[i+1]])
        if k not in distances:
            k = ' to '.join([c[i+1], c[i]])
        total_distance += distances[k]
    trips.add(total_distance)

print("Part 1:", min(list(trips)))
print("Part 2:", max(list(trips)))