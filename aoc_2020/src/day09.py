import itertools as it
input = [int(line.strip()) for line in open('input/day09.txt').readlines()]

def can_sum(source_nums, target):
    for c in it.combinations(source_nums, 2):
        if sum(c) == target: return True
    return False

start = 0
end = 25
source_nums = input[start:end]
target = input[end]

while can_sum(source_nums, target):
    start += 1
    end += 1
    source_nums = input[start:end]
    target = input[end]

print("Part 1:", target)

start = 0
end = 2
contig = input[start:end]

while sum(contig) != target:
    while sum(contig) < target:
        end += 1
        contig = input[start:end]
    if sum(contig) > target:
        start += 1
        end = start + 2
        contig = input[start:end]

print("Part 2:", min(contig) + max(contig))


