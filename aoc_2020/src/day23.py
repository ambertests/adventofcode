# Started out trying to solve with array slicing but quickly realized some sort
# of linked list would be better. My initial Cup implementation was *way* over-
# engineered as a doubly-linked list with value-neutral pop and insert methods.
# I also had a find function which traversed the Cup list to find a given label,
# but that was much much too slow for Part 2, so I replaced it with label->Cup
# lookup dict. Evenutally I whittled down the Cup class to just the __init__ 
# with label and next properties. Solve time is around 30s, which is pretty
# slow, but it works correctly. Replacing the linked list with a label->next_label  
# dict or an array (see day15.py) would likely be much faster, but I couldn't 
# wrap my brain around how to re-insert the three pickup cups, so left it as is.
class Cup:
    def __init__(self, label, prev=None):
        self.label = label
        self.next = None
        if prev is not None: 
            prev.next = self

CUP_MAP = {}

def init_cups(input, size):
    first = Cup(input[0])
    CUP_MAP[first.label] = first
    cup = first
    for i in range(1,len(input)):
        cup = Cup(input[i], cup)
        CUP_MAP[cup.label] = cup
    if size > max(input):
        for n in range(max(input),size):
            cup = Cup(n+1, cup)
            CUP_MAP[cup.label] = cup
    cup.next = first
    return first

def cup_game(cup, max, moves):
    for _ in range(moves):
        current = cup.label
        pickup = cup.next
        cup.next = pickup.next.next.next
        pickup.next.next.next = None
        p_labels = [pickup.label, pickup.next.label, pickup.next.next.label]
        dest = current - 1 if current > 1 else max
        while dest in p_labels:
            dest = dest - 1 if dest > 1 else max
        dest_cup = CUP_MAP[dest]
        pickup.next.next.next = dest_cup.next
        dest_cup.next = pickup
        cup = cup.next
     
    return CUP_MAP[1]

def solve(input):
    cup = init_cups(input, max(input))
    cup = cup_game(cup, max(input), 100)
    nums = []
    while cup.next.label != 1:
        nums.append(cup.next.label)
        cup = cup.next
    print("Part 1:", "".join(str(i) for i in nums))
    cup = init_cups(input, 1000000)
    cup = cup_game(cup, 1000000, 10000000)
    print("Part 2:", cup.next.label * cup.next.next.label)


test = [int(c) for c in '389125467']
day23 = [int(c) for c in '872495136']

solve(day23)


