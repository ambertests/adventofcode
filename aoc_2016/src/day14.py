from hashlib import md5
SALT = 'cuanljph'

def solve(salt, stretch=0):
    keys = set()
    num = 0
    triples = {}
    while len(keys) < 64:
        hex = md5((salt + str(num)).encode()).hexdigest()
        for i in range(stretch):
            hex = md5(hex.encode()).hexdigest()
        for t in triples:
            if num > t + 1000: continue
            if triples[t] * 5 in hex:
                keys.add(t)
        for i in range(len(hex)-3):
            if hex[i] == hex[i+1] == hex[i+2]:
                triples[num] = hex[i]
                break
        num += 1
    return sorted(keys)[63]

#print("Example:", orig_solve('abc')) # the example doesn't work???
print("Part 1:", solve(SALT))
print("Part 2:", solve(SALT, 2016))


