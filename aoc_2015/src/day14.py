class Reindeer:
    def __init__(self, name, rate, fly_time, rest_time):
        self.name = name
        self.rate = rate
        self.fly_time = fly_time
        self.rest_time = rest_time
        self.fly_sec = 0
        self.rest_sec = 0
        self.fly = True
        self.distance = 0
        self.points = 0

    def tick(self):
        if self.fly:
            self.fly_sec += 1
            self.distance += self.rate
            if self.fly_sec == self.fly_time:
                self.fly = False
                self.rest_sec = 0
        else:
            self.rest_sec += 1
            if self.rest_sec == self.rest_time:
                self.fly = True
                self.fly_sec = 0

input = [line.strip().split(' ') for line in open("input/day14.txt").readlines()]
R = []
for line in input:
    name = line[0]
    rate = int(line[3])
    fly_time = int(line[6])
    rest_time = int(line[-2])
    R.append(Reindeer(name, rate, fly_time, rest_time))

for _ in range(2503):
    for r in R: r.tick()
    R.sort(reverse=True, key=lambda x:x.distance)
    lead = R[0].distance
    for r in R:
        if r.distance == lead: r.points += 1

R.sort(reverse=True, key=lambda x:x.distance)
print("Part 1:", R[0].name, R[0].distance)
R.sort(reverse=True, key=lambda x:x.points)
print("Part 2:", R[0].name, R[0].points)

