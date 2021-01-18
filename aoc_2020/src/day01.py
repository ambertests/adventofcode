input = open('input/day01.txt').readlines()
int_list = [int(x.strip()) for x in input]

for i in int_list:
    j = 2020-i
    if j in int_list:
        print("Part 1:", i*j)
        break

pt2 = 0
for i in int_list:
    for j in int_list:
        if i == j or i + j >= 2020: continue
        k = 2020 - (i+j)
        if k in int_list:
            pt2 = i * j * k
            break
    if pt2 > 0: break
    
print("Part 2:", pt2)