from functools import reduce

# https://stackoverflow.com/questions/6800193/what-is-the-most-efficient-way-of-finding-all-the-factors-of-a-number-in-python
def factors(n):
        step = 2 if n%2 else 1
        return set(reduce(list.__add__,
                    ([i, n//i] for i in range(1, int(n**0.5)+1, step) if not n % i)))

def solve(target):
    house_count = 0
    deliveries = {}
    complete = set()
    pt1 = 0
    pt2 = 0
    while pt1 == 0 or pt2 == 0:
        house_count += 1
        gifts1 = 0
        gifts2 = 0
        elves = factors(house_count)
        if pt1 == 0:
            gifts1 = sum(elves)*10
            if gifts1 >= target:
                pt1 = house_count
        if pt2 == 0:
            working = elves.difference(complete)
            for elf in working:
                if elf in deliveries:
                    deliveries[elf] += 1
                    if deliveries[elf] == 50:
                        complete.add(elf)
                else:
                    deliveries[elf] = 1
            gifts2 = sum(working)*11
            if gifts2 >= target:
                pt2 = house_count

    return pt1, pt2

# takes around 20s
pt1, pt2 = solve(29000000)
print("Part 1:", pt1)
print("Part 2:", pt2)
        

