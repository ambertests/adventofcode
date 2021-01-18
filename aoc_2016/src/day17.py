from hashlib import md5
room = [
'#########',
'#S| | | #',
'#-#-#-#-#',
'# | | | #',
'#-#-#-#-#',
'# | | | #',
'#-#-#-#-#',
'# | | |  ',
'####### V'
]
start = (0,0)
vault = (3,3)
size = 4

PATH = ''
def find_path(path, room, code, part):
    global PATH
    if room == vault:
        if PATH == '': 
            PATH = path
        elif part == 1 and len(path) < len(PATH):
            PATH = path
        elif part == 2 and len(path) > len(PATH):
            PATH = path
    else:
        x,y = room
        doors = []
        hex = md5((code + path).encode()).hexdigest()
        if hex[0] in 'bcdef' and y > 0:
            doors.append(((x,y-1), 'U'))
        if hex[1] in 'bcdef' and y < size - 1:
            doors.append(((x,y+1), 'D'))
        if hex[2] in 'bcdef' and x > 0:
            doors.append(((x-1,y), 'L'))
        if hex[3] in 'bcdef' and x < size - 1:
            doors.append(((x+1,y), 'R'))
        for door in doors:
            find_path(path + door[1], door[0], code, part)

def solve(code, part):
    global PATH
    PATH = ''
    find_path('', start, code, part)
    if part == 1:
        return PATH
    else:
        return len(PATH)

# print(solve('ihgpwlah', 1) == 'DDRRRD')
# print(solve('kglvqrro', 1) == 'DDUDRLRRUDRD')
# print(solve('ulqzkmiv', 1) == 'DRURDRUDDLLDLUURRDULRLDUUDDDRR')

print("Part 1:", solve('mmsxrhfx', 1)) #RLDUDRDDRR

# print(solve('ihgpwlah', 2) == 370)
# print(solve('kglvqrro', 2) == 492)
# print(solve('ulqzkmiv', 2) == 830)

print("Part 2:", solve('mmsxrhfx', 2)) #590