
input = [line.strip() for line in open('input/day10.txt').readlines()]
bots = []
outputs = []

def can_give(idx):
    if idx < len(bots):
        if bots[idx] is not None:
            if len(bots[idx]) == 2:
                return True
    return False

def extend_array(arr, l):
    if l >= len(arr):
        for _ in range(l-len(arr)+1):
            arr.append(None)

def assign_value(idx, val):
    extend_array(bots, idx)
    if bots[idx] is None:
        bots[idx] = [val]
    else:
        bots[idx].append(val)
    if 61 in bots[idx] and 17 in bots[idx]:
        print("Part 1:", idx)

def output(idx, val):
    extend_array(outputs, idx)
    outputs[idx] = val

def run_bots():
    #bot 2 gives low to bot 1 and high to bot 0
    actions = [line.split(' ') for line in input]
    while len(actions):
        todo = actions.copy()
        for action in todo:
            if action[0] == 'value':
                idx = int(action[5])
                val = int(action[1])
                assign_value(idx, val)
                actions.remove(action)
            else: #bot
                idx = int(action[1])    
                if can_give(idx):
                    vals = bots[idx]
                    low_i = int(action[6])
                    high_i = int(action[-1])
                    if action[5] == 'bot':
                        assign_value(low_i, min(vals))
                    else:
                        output(low_i, min(vals))
                    
                    if action[-2] == 'bot':
                        assign_value(high_i, max(vals))
                    else:
                        output(high_i, max(vals))
                    actions.remove(action)

run_bots()
print("Part 2:", outputs[0]*outputs[1]*outputs[2])
