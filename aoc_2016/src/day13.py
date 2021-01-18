start = (1,1)
# test #
# target = (7,4)
# fav = 10
# size = 10

# # real #
target = (31,39) #(7,4)
fav = 1358 #10
size = 50 #10

def get_char(x,y,fav,path=[]):
    char = '.'
    if (x,y) == target: 
        char = 'F'
    elif (x,y) == start:
        char = 'S'
    elif (x,y) in path:
        char = 'O'
    else:
        a = x*x + 3*x + 2*x*y + y + y*y
        b = str(bin(a+fav))[2:]
        c = len([c for c in b if c == '1'])
        if c%2 > 0: char = '#'
    return char

def build_maze(w, h, fav, path=[]):
    return [[get_char(x,y,fav,path) for x in range(w)] for y in range(h)]

paths = []
len_50 = set()
def find_all_paths(path,maze,pos,part):
    if part == 1 and pos == target:
        path.append(pos)
        paths.append(path)
        return
    if part == 2 and len(path) <= 50:
        len_50.update(path + [pos])
        if len(path) == 50:
            return
    x,y = pos
    adj = [
        (x+1,y),
        (x,y+1),
        (x-1,y),
        (x,y-1)
    ]
    for step in adj:
        if step[0] in range(len(maze[0])) and step[1] in range(len(maze)):
            if step not in path:
                if maze[step[1]][step[0]] != '#':
                    new_path = path + [pos]
                    find_all_paths(new_path, maze, step, part)


w, h = 40,45
grid = build_maze(w,h, fav)
find_all_paths([], grid, start,1)

min_path = min([len(p) for p in paths])
print('Part 1:',min_path-1) # don't include start

find_all_paths([],grid,start,2)
print('Part 2:',len(len_50))

# path = [p for p in paths if len(p) == min_path][0]
# to_print = build_maze(w,h,fav,path)
# for row in to_print:
#     print(''.join(row))





        