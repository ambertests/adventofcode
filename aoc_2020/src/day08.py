input = [line.strip().split(' ') for line in open('input/day08.txt').readlines()]
accumulator = 0
current_index = 0
indeces = set()

while current_index not in indeces:
    indeces.add(current_index)
    inst = input[current_index]
    cmd = inst[0]
    num = int(inst[1])
    if cmd == 'acc':
        accumulator += num
        current_index += 1
    if cmd == 'jmp':
        current_index += num
    if cmd == 'nop':
        current_index += 1

print("Part 1:", accumulator)

tested = set()
completed = False

while not completed:
    accumulator = 0
    current_index = 0
    indeces.clear()
    did_test = False
    while current_index not in indeces and current_index < len(input):
        indeces.add(current_index)
        inst = input[current_index]
        cmd = inst[0] 
        num = int(inst[1])
        if cmd == 'acc':
            accumulator += num
            current_index += 1
        if cmd == 'jmp':
            if not did_test and current_index not in tested:
                # switch to nop
                tested.add(current_index)
                did_test = True
                current_index += 1
            else:
                current_index += num
        if cmd == 'nop':
            if not did_test and current_index not in tested:
                # switch to jmp
                tested.add(current_index)
                did_test = True
                current_index += num
            else:
                current_index += 1
    completed = current_index >= len(input)

print("Part 2:", accumulator)
