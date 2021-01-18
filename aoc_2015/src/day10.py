
   
def look_and_say(numstr, turns):
    for _ in range(turns):
        new_str = ''
        last = ''
        count = 0
        for i in range(len(numstr)):
            c = numstr[i]
            if i == 0:
                last = c
                count = 1
            elif c == last:
                count += 1
            elif c != last:
                new_str = new_str + str(count) + last
                last = c
                count = 1
            if i == len(numstr) - 1:
                new_str = new_str + str(count) + last
        numstr = new_str
    return numstr

# The above method worked ok for part 1 but was way too slow 
# for part 2. So after getting totally bogged down in trying 
# to leverage Conway elements, I finally cribbed the method 
# below from https://rosettacode.org/wiki/Look-and-say_sequence#Python
# The groupby method does all the hard work of grouping the string
# elements fast enough to complete the 50 iterations in ~20s
# >>> for k,g in groupby('1113122113'):
# ...     print(k,list(g))
# ... 
# ('1', ['1', '1', '1'])
# ('3', ['3'])
# ('1', ['1'])
# ('2', ['2', '2'])
# ('1', ['1', '1'])
# ('3', ['3'])
# >>> 
from itertools import groupby
def lookandsay(number):
	return ''.join( str(len(list(g))) + k
		        for k,g in groupby(number) )

def solve():
    num = '1113122113'
    num = look_and_say(num, 40)
    print("Part 1:", len(num))
    for _ in range(10):
        num = lookandsay(num)
    print("Part 2:", len(num))

solve()



