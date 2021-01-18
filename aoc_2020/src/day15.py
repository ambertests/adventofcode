from time import time
# My first solution had a dict with the last number
# spoken as the key and an array of turns as the value.
# For part two I added code to ensure the array was never
# more than two turns, but it still took minutes to complete.

# After looking at some other solutions, I updated the dict 
# to have only the last turn as the vaule instead of an array
# of turns. That brought the part 2 time down to around
# 20 sec. A further optimization was to change the dict into
# a pre-allocated array, which cut the time to around 12 sec.
def solve(numbers, iter):
    start = time()
    last_turn = [0 for i in range(iter)]
    last_num = -1
    for i in range(iter):
        if i < len(numbers):
            # initialize with the starting numbers
            last_num = numbers[i]
            # turns are 1-based
            last_turn[last_num] = i+1
        else:
            num = last_turn[last_num]
            if num > 0:
                # since turns are 1-based, i is equal to the last turn
                num = i - num
            # very important to update the array
            # before the last_num is updated
            last_turn[last_num] = i
            last_num = num
    end = time()
    print(round(end - start, 3))
    return last_num

print("Part 1:", solve([13,16,0,12,15,1], 2020))
# a little slow but still works
print("Part 2:", solve([13,16,0,12,15,1], 30000000))
