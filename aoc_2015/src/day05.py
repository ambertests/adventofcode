
input = [line.strip() for line in open("input/day05.txt").readlines()]

def is_nice1(str):
    nice = True
    naugty = ['ab', 'cd', 'pq', 'xy']
    for n in naugty:
        if n in str: nice = False
    if nice:
        vowel_count = 0
        has_double = False
        for i in range(len(str)):
            c = str[i]
            if c in 'aeiou': vowel_count += 1
            if i > 0 and str[i-1] == c: has_double = True
        nice = has_double and vowel_count >= 3
    return nice

def is_nice2(str):
    pairs = {}
    has_dup = False
    has_dup_pair = False
    for i in range(len(str)):
        if i < len(str)-1: 
            pair = str[i:i+2]
            pair_coord = (i, i+1)
            if pair in pairs:
                if (i-1, i) not in pairs[pair]:
                    pairs[pair].append(pair_coord)
                    has_dup_pair = True
            else:
                pairs[pair] = [pair_coord]

        if i > 1 and str[i] == str[i-2]: 
            has_dup = True
    return has_dup and has_dup_pair

# test_nice = ['qjhvhtzxzqqjkmpb','xxyxx']
# test_naughty = ['aaa','uurcxstgmygtbstg','ieodomkazucvgmuy']
# for n in test_nice:
#     print(is_nice2(n))
# for n in test_naughty:
#     print(is_nice2(n))

part1 = 0
part2 = 0
for str in input:
    if is_nice1(str): part1 += 1
    if is_nice2(str): part2 += 1

print("Part 1:", part1)
print("Part 2:", part2)
