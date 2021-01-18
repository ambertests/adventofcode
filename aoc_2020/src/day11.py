input = [list(line.strip()) for line in open('input/day11.txt').readlines()]

max_col = len(input[0])
max_row = len(input)

def print_seats(arr):
    for row in arr:
        print("".join(row))
    print('\n')

def count_neigbors(seats, row, col, pt2):
    deltas = [(-1, -1), (-1, 0), (-1, 1), 
              (0, -1),           (0, 1), 
              (1, -1),  (1, 0),  (1, 1)]
    neighbors = 0
    for delta in deltas:
        r = row + delta[0]
        c = col + delta[1]
        if not pt2:
            if r >= 0 and r < max_row and \
                      c >= 0 and c < max_col and \
                             seats[r][c] == '#':
                neighbors += 1
        else:
            found_seat = "."
            while found_seat == "." and \
                             r >= 0 and r < max_row and \
                                    c >= 0 and c < max_col:

                found_seat = seats[r][c]
                
                if delta[0] < 0: r -= 1
                elif delta[0] > 0: r += 1
                
                if delta[1] < 0: c -= 1
                elif delta[1] > 0: c += 1

            if found_seat == "#": neighbors += 1

    return neighbors

def do_seat_update(seats, pt2):
    updated = [['.' for c in range(max_col)] 
                        for r in range(max_row)]
    occupied = 0
    changes = 0
    for row in range(max_row):
        for col in range(max_col):
            seat = seats[row][col]
            if seat == '.':
                continue
            if seat == 'L':
                if count_neigbors(seats, row, col, pt2) == 0:
                    updated[row][col] = '#'
                    occupied += 1
                    changes += 1
                else:
                    updated[row][col] = 'L'
            if seat == '#':
                if pt2: n_limit = 5
                else: n_limit = 4
                if count_neigbors(seats, row, col, pt2) >= n_limit:
                    updated[row][col] = 'L'
                    changes += 1
                else:
                    updated[row][col] = '#'
                    occupied += 1

    return updated, occupied, changes

def do_the_thing(pt2):
    seats = [[input[r][c] for c in range(max_col)] 
                for r in range(max_row)]
    occupied = 0
    changes = -1
    while changes != 0:
        #print_seats(seats)
        updated, occupied, changes = do_seat_update(seats, pt2)
        seats = updated.copy()
        #print_seats(seats)
    return occupied

print("Part 1:", do_the_thing(pt2=False))
print("Part 2:", do_the_thing(pt2=True))


    