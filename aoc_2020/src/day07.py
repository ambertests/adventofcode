class Bag:
    def __init__(self, line):
        split1 = line.split(' contain ')
        split2 = split1[0].split(' ')
        self.color = '_'.join(split2[0:2])
        contents = split1[1]
        self.contents = []
        if not contents.startswith('no'):
            for c in contents.split(', '):
                split3 = c.split(' ')
                self.contents.append(InnerBag(split3[0], '_'.join(split3[1:3])))
        

    def __str__(self):
        return '%s %s' % (self.color, [str(c) for c in self.contents])


class InnerBag:
    def __init__(self, quantity, color):
        self.quantity = int(quantity)
        self.color = color

    def __str__(self):
        return '%s %s' % (self.quantity, self.color)

input = open('input/day07.txt').readlines()
all_bags = {}
outer_bags = {}
for i in input:
    bag = Bag(i)
    all_bags[bag.color] = bag
    for c in bag.contents:
        if c.color in outer_bags:
            outer_bags[c.color].append(bag.color)
        else:
            outer_bags[c.color] = [bag.color]

# https://www.reddit.com/r/adventofcode/comments/k8cbhv/2020_day_7_part_1_incorrect_solution_count_is_too/
bags = set()  # Outer bags
bags_to_scan = {'shiny_gold'}

# Scan for new parent bags.
while bags_to_scan:
	# Keep track of new outer bags so we can scan their parents in the next loop.
    new_bags = set()

    for bag in bags_to_scan:
		# Look at all possible parents that can contain this bag
        if bag in outer_bags:
            for parent in outer_bags[bag]:
                # If this parent hasn't already been found, keep track of it.
                if parent not in bags:
                    new_bags.add(parent)

	# Add the new bags to our main list.
    bags |= new_bags

	# Replace the scan list with the new bags we found
    bags_to_scan = new_bags

print("Part 1:", len(bags))

# https://github.com/voidlessVoid/advent_of_code_2020/blob/main/day_07/michael/solution.py
def count_contents(current_bag):
    if not current_bag.contents:
        return 0
    return sum([count_contents(all_bags[bag.color]) * bag.quantity + bag.quantity \
        for bag in current_bag.contents])

print("Part 2:", count_contents(all_bags["shiny_gold"]))