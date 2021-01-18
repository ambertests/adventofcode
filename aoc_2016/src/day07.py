import re

input = [line.strip() for line in open('input/day07.txt').readlines()]

def has_abba(s):
    hasit = False
    if len(s) < 4:
        return hasit
    for i in range(len(s)-3):
        chunk = s[i:i+4]
        if chunk[0] == chunk[3]:
            if chunk[0] != chunk[1]:
                if chunk[1] == chunk[2]:
                    hasit = True
                    break
    return hasit

def find_babs(s):
    babs = []
    if len(s) < 3:
        return babs
    for i in range(len(s)-2):
        chunk = s[i:i+3]
        if chunk[0] == chunk[2]:
            if chunk[0] != chunk[1]:
                babs.append(chunk)
    return babs

def supports_tls(bracketed, split):
    for b in bracketed:
        if has_abba(b):
            return False
    for s in split:
        if s in bracketed: 
            continue
        if has_abba(s):
            return True
    return False

def supports_ssl(bracketed, split):
    for b in bracketed:
        babs = find_babs(b)
        for bab in babs:
            aba = ''.join([bab[1],bab[0],bab[1]])
            for s in split:
                if s in bracketed: continue
                if aba in s:
                    return True
    return False

def check_address(line):
    bracketed = re.findall('\[(.*?)\]', line)
    split = re.split('\[(.*?)\]', line)
    tls = supports_tls(bracketed, split)
    ssl = supports_ssl(bracketed, split)
    return tls, ssl

def solve():
    tls = 0
    ssl = 0
    for line in input:
        t, s = check_address(line)
        if t: tls += 1
        if s: ssl += 1
    return tls, ssl


pt1, pt2 = solve()
print("Part 1:", pt1)
print("Part 2:", pt2)

    
