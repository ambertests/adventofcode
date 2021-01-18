alphabet = 'abcdefghijklmnopqrstuvwxyz'

def increment_letter(c):
    if c == 'z': return 'a'
    c = alphabet[alphabet.index(c)+1]
    if c in ['i','l','o']:
        c = alphabet[alphabet.index(c)+1]
    return c

def increment(password):
    pw = list(password)
    to_inc = -1
    while pw[to_inc] == 'z':
        to_inc -= 1
    for i in range(to_inc,0):
        pw[i] = increment_letter(pw[i])
    return ''.join(pw)

def is_valid(password):
    pairs = {}
    has_triple = False
    for i in range(len(password)):
        if i < len(password)-1 and password[i] == password[i+1]: 
            pair = password[i:i+2]
            pair_coord = (i, i+1)
            if pair in pairs:
                if (i-1, i) not in pairs[pair]:
                    pairs[pair].append(pair_coord)
            else:
                pairs[pair] = [pair_coord]
        if i < len(password)-2:
            if password[i:i+3] in alphabet:
                has_triple = True

    return len(pairs) >= 2 and has_triple

def get_next_password(password):
    pw = increment(password)
    while not is_valid(pw):
        pw = increment(pw)
    return pw

pw = 'hxbxwxba'
p1 = get_next_password(pw)
p2 = get_next_password(p1)
print("Part 1:", p1)
print("Part 2:", p2)