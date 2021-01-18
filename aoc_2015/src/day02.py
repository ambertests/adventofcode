boxes = [[int(c) for c in line.strip().split('x')] 
                for line in open("input/day02.txt").readlines()]
paper = 0
ribbon = 0
for box in boxes:
    l, w, h = box
    dims = list(box)
    dims.sort()
    paper += ((2*l*w + 2*w*h + 2*h*l) + (dims[0]*dims[1]))
    ribbon += ((2*dims[0])+(2*dims[1])) + l*w*h

print("Part 1:", paper)
print("Part 2:", ribbon)