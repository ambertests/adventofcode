test = [5764801, 17807724]
day25 = [10705932, 12301431]

def transform(num, loops):
# Set the value to itself multiplied by the subject number.
# Set the value to the remainder after dividing the value by 20201227.
    val = 1
    for i in range(loops):
        val = (val*num)%20201227
    return val
    
def handshake(card_loop, door_loop):
    card_key = transform(7, card_loop)
    door_key = transform(7, door_loop)
    encryption1 = transform(card_key, door_loop)
    encryption2 = transform(door_key, card_loop)
    if encryption1 == encryption2:
        return encryption1
    else:
        return -1

def find_loop_count(target):
    val = 1
    loops = 0
    while val != target:
        val = (val*7)%20201227
        loops += 1
    return loops

def solve(input):
    card_key = input[0]
    door_key = input[1]
    card_loops = find_loop_count(card_key)
    door_loops = find_loop_count(door_key)
    print(card_loops, door_loops)
    key = handshake(card_loops, door_loops)
    print("Part 1:", key)

solve(day25)



