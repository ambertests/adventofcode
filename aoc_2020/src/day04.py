
def is_complete(passport):
    cats = []
    lines = passport.split("\n")
    for l in lines:
        fields = l.split(" ")
        for f in fields:
            cats.append(f.split(":")[0])
    return len(cats) == 8 or (len(cats) == 7 and "cid" not in cats)


def is_valid(field):
    f = field.split(":")
    cat = f[0]
    val = f[1]
    valid = False

    if cat == "byr":
        # byr (Birth Year) - four digits; at least 1920 and at most 2002.
        valid = len(val) == 4 and int(val) >= 1920 and int(val) <= 2002
    
    if cat == "iyr":
        # iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        valid = len(val) == 4 and int(val) >= 2010 and int(val) <= 2020
    
    if cat == "eyr":
        # eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
        valid = len(val) == 4 and int(val) >= 2020 and int(val) <= 2030
    
    if cat == "hgt":
        # hgt (Height) - a number followed by either cm or in:
        # If cm, the number must be at least 150 and at most 193.
        # If in, the number must be at least 59 and at most 76.
        unit = val[-2:]
        count = val[:-2]
        if unit == "cm":
            valid = int(count) >= 150 and int(count) <= 193
        if unit == "in":
            valid = int(count) >= 59 and int(count) <= 76
    
    if cat == "hcl":
        # hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f. 
        if len(val) == 7 and val[0] == "#":
            ok = True
            for v in range(1,7):
                if val[v] not in "0123456789abcdef":
                    ok = False
                    break
            valid = ok
    
    if cat == "ecl":
        # ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        colors = ["amb", "blu", "brn", "gry", "grn", "hzl", "oth"]
        valid = val in colors
    
    if cat == "pid":
        # pid (Passport ID) - a nine-digit number, including leading zeroes.
        if len(val) == 9:
            ok = True
            for n in val:
                if n not in "0123456789":
                    ok = False
                    break
            valid = ok
    
    if cat == "cid": 
        # cid (Country ID) - ignored, missing or not.
        valid = True
    
    return valid

input = open('input/day04.txt').read().split("\n\n")


complete = 0 
valid = 0
for p in input:
    if is_complete(p):
        complete += 1
        lines = p.split("\n")
        fields = []
        for l in lines:
            fields.extend(l.split(" "))
        ok = True
        for f in fields:
            if not is_valid(f): 
                ok = False
                break
        if ok: valid += 1
        

print("Part 1:", complete)
print("Part 2:", valid)









