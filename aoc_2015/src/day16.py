input = [line.strip().split(' ') for line in open('input/day16.txt').readlines()]
data = '''children: 3
cats: 7
samoyeds: 2
pomeranians: 3
akitas: 0
vizslas: 0
goldfish: 5
trees: 3
cars: 2
perfumes: 1'''

D = {}
for d in data.split('\n'):
    k, v = d.split(': ')
    D[k] = int(v)

A = [None for _ in range(500)]
for l in input:
    #['Sue', '495:', 'children:', '9,', 'akitas:', '8,', 'vizslas:', '4']
    i = int(l[1][:-1])
    a = {}
    a[l[2][:-1]] = int(l[3][:-1])
    a[l[4][:-1]] = int(l[5][:-1])
    a[l[6][:-1]] = int(l[7])
    A[i-1] = a

def solve1():
    valid = list(range(500))
    invalid = []
    for i in valid:
        aunt = A[i]
        for key in aunt:
            if aunt[key] != D[key]:
                invalid.append(i)
                break
    for inv in invalid: valid.remove(inv)
    return valid[0]+1

def solve2():
    valid = list(range(500))
    invalid = []
    for i in valid:
        aunt = A[i]
        for key in aunt:
            if key in ['cats', 'trees']:
                if aunt[key] <= D[key]:
                    invalid.append(i)
                    break
            elif key in ['pomeranians', 'goldfish']:
                if aunt[key] >= D[key]:
                    invalid.append(i)
                    break
            elif aunt[key] != D[key]:
                invalid.append(i)
                break
    for inv in invalid: valid.remove(inv)
    return valid[0]+1

print("Part 1:", solve1())
print("Part 2:", solve2())