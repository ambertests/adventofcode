input = open('input/day16.txt').read().split("\n\n")
field_values = input[0].split("\n")
fields = {}
valid_nums = set()
for fv in field_values:
    field, vals = (fv.split(': '))
    fields[field] = set()
    for v in vals.split(' or '):
        se = [int(a) for a in v.split('-')]
        nums = list(range(se[0], se[1]+1))
        fields[field].update(nums)
        valid_nums.update(nums)

field_count = len(fields)
my_ticket = [int(i) for i in input[1].split("\n")[1].split(",")]
nearby = [[int(i) for i in row.split(",")] for row in input[2].split('\n')[1:]]

err_rate = 0
valid_tickets = []
for n in nearby:
    diff = set(n).difference(valid_nums)
    err_rate += sum(list(diff))
    if len(diff) == 0: valid_tickets.append(n)

print("Part 1:", err_rate)

# first determine possible positions for each field
potential = [[] for f in range(field_count)]
for field in fields:
    for pos in range(field_count):
        vals = set([t[pos] for t in valid_tickets])
        if fields[field].intersection(vals) == vals:
            potential[pos].append(field)

field_position = [None for f in range(field_count)]
while None in field_position:
    for p in range(field_count):
        # if a position only has one possible field, then
        # assign that field to the position and remove it
        # from other potential positions.
        if len(potential[p]) == 1:
            field = potential[p][0]
            field_position[p] = field
            for pot in potential:
                if field in pot:
                    pot.remove(field)

prod = 1
for i in range(field_count):
    if field_position[i].startswith("departure"):
        prod *= my_ticket[i]

print("Part 2:", prod)


