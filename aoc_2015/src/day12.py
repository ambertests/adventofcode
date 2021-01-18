import re, json

def sum_numbers(s):
    return sum([int(i) for i in re.findall("[-\d]+", str(s))])

input = open("input/day12.txt").read()
print("Part 1:", sum_numbers(input))

def clear_red(o):
    if isinstance(o, list):
        for n in o:
            clear_red(n)
    elif isinstance(o, dict):
        if 'red' in list(o.values()):
            o.clear()
        else: 
            for k in o:
                if isinstance(o[k], list) or isinstance(o[k], dict):
                    clear_red(o[k])
    return o


print("Part 2:", sum_numbers(clear_red(json.loads(input))))