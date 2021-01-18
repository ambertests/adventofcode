input = [line.strip() for line in open("input/day08.txt").readlines()]

code = 0
string = 0
new_code = 0
for line in input:
    code += len(line)
    to_esc = len([c for c in line if c in ['\"', '\\']])
    new_code += (len(line) + to_esc + 2)
    skip = []
    for i in range(1,len(line)-1):
        if i in skip: continue
        c = line[i]
        if c == '\\':
            if line[i+1] == 'x':
                skip.extend([i+1, i+2, i+3])
            else: 
                skip.append(i+1)
        string += 1

print("Part 1:", code - string)
print("Part 2:", new_code - code)
