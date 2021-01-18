NUMPAD = [
    [1,2,3],
    [4,5,6],
    [7,8,9],
]
NUMPAD2 = [
[' ',' ','1',' ',' '],
[' ','2','3','4',' '],
['5','6','7','8','9'],
[' ','A','B','C',' '],
[' ',' ','D',' ',' ']
]
def get_bathroom_code(instructions, numpad, start):
    size = len(numpad)
    x,y = start
    code = []
    for line in instructions:
        for c in line:
            if c == 'U':
                if x > 0 and numpad[x-1][y] != ' ': 
                    x -= 1
            elif c == 'D':
                if x < size - 1 and numpad[x+1][y] != ' ':
                    x += 1
            elif c == 'L':
                if y > 0 and numpad[x][y-1] != ' ': 
                    y -= 1 
            elif c == 'R':
                if y < size - 1 and numpad[x][y+1] != ' ': 
                    y += 1

        code.append(numpad[x][y])
    return ''.join([str(c) for c in code])

input = [list(line.strip()) for line in open('input/day02.txt').readlines()]
print("Part 1:", get_bathroom_code(input, NUMPAD, (1,1)))
print("Part 2:", get_bathroom_code(input, NUMPAD2, (2,0)))