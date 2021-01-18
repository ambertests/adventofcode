input = open("input/day19.txt").read().split('\n\n')
rules = {}
for rule in input[0].split('\n'):
    key, val = rule.split(': ')
    if '"' in val:
        rules[int(key)] = val[1] # get the char without the quotes
    else:
        rules[int(key)] = [[int(i) for i in l.split(' ')] for l in val.split(' | ')]

messages = input[1].split('\n')

# https://www.reddit.com/r/adventofcode/comments/kg1mro/2020_day_19_solutions/ggc65jt/
def check_match(str, stack):
    if len(stack) == len(str) == 0:
        return True
    elif len(stack) == 0 or len(str) == 0:
        return False
    c = stack[0]
    stack = stack[1:]
    if c in ['a', 'b']:
        if str[0] == c:
            # once a character is matched, it is removed from the string
            return check_match(str[1:], stack.copy())
    else:
        for rule in rules[c]:
            # the rule needs to be cast to a list 
            # because it might be a character. also
            # since c came from the front of the stack
            # the expanded rule is prepended
            if check_match(str, list(rule) + stack):
                return True
    return False

def get_valid_count():
    valid = 0
    for message in messages:
        if(check_match(message, rules[0][0])):
            valid += 1
    return valid

print("Part 1:", get_valid_count())

rules[8] = [[42], [42, 8]]
rules[11] = [[42, 31],[42, 11, 31]]
print("Part 2:", get_valid_count())
