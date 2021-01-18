
def reverse_binary(b):
    return ''.join(['0' if c == '1' else '1' for c in b])

def dragon(b, l):
    while len(b) < l:
        b = b + '0' + reverse_binary(reversed(b))
    return b[:l]

def checksum(d):
    c = ''.join(['1' if d[i] == d[i+1] else '0' for i in range(0,len(d),2)])
    if len(c)%2 == 0:
        c = checksum(c)
    return c

input = '11101000110010100'
print("Part 1:", checksum(dragon(input, 272)))
print("Part 2:", checksum(dragon(input, 35651584)))
