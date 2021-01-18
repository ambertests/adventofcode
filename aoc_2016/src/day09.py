def decompress(s, part):
    n, i = 0, 0
    while i < len(s):
        if s[i] == '(':
            lp = i
            rp = lp + s[lp:].index(')')
            l, c = [int(x) for x in s[lp+1:rp].split('x')]
            if part == 1:
                n += l*c
            else:
                chunk = s[rp+1:rp+1+l]
                n += decompress(chunk, part) * c
            i = rp + 1 + l
        else:
            n += 1
            i += 1
    return n

s = open('input/day09.txt').read().strip()
print("Part 1:", decompress(s, 1))
print("Part 2:", decompress(s, 2))

# print(decompress('ADVENT', 1)) #6      
# print(decompress('A(1x5)BC', 1)) #7
# print(decompress('(3x3)XYZ', 1)) #9
# print(decompress('A(2x2)BCD(2x2)EFG', 1)) #11
# print(decompress('(6x1)(1x3)A', 1)) #6
# print(decompress('X(8x2)(3x3)ABCY', 1)) #18

# print(decompress('(3x3)XYZ', 2)) #9
# print(decompress('(27x12)(20x12)(13x14)(7x10)(1x12)A', 2)) #241920
# print(decompress('(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN', 2)) #445
