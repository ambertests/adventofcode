input = open('input/day02.txt').readlines()
#2-6 w: wkwwwfwwpvw
#int_list = [int(x.strip()) for x in input]
pt1_valid = 0
pt2_valid = 0
for ln in input:
    splt = ln.strip().split(" ")
    rng = [int(i) for i in splt[0].split("-")]
    min = rng[0]
    max = rng[1]
    letter = splt[1][0]
    pwd = splt[2]

    l_count = 0
    p_count = 0
    for l in range(len(pwd)):
        if pwd[l] == letter: 
            l_count += 1
            if l+1 == min or l+1 == max:
                p_count += 1
    if l_count >= min and l_count <= max:
        pt1_valid += 1
    if p_count == 1: 
        pt2_valid += 1


print("Part 1:", pt1_valid)
print("Part 2:", pt2_valid)
    



