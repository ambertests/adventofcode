rows = [r for r in range(128)]
seats = [s for s in range(8)]

def bisect_array(char, arr):
    half = int(len(arr)/2)
    if char in ['F', 'L']:
        return arr[:half]
    else:
        return arr[half:]

def get_seat_id(assignment):
    row_assgn = assignment[:7]
    seat_assgn = assignment[-3:]
    my_row = rows
    my_seat = seats
    for ra in row_assgn:
        my_row = bisect_array(ra, my_row)
    for sa in seat_assgn:
        my_seat = bisect_array(sa, my_seat)
    return (my_row[0] * 8) + my_seat[0]

input = [line.strip() for line in open('input/day05.txt').readlines()]
seat_ids = []
for i in input:
    seat_id = get_seat_id(i)
    seat_ids.append(seat_id)

print("Part 1:", max(seat_ids))
seat_ids.sort()
prev = seat_ids[0]
for i in range(1, len(seat_ids)):
    if seat_ids[i] - prev > 1:
        print("Part 2:", prev+1)
        break
    else:
        prev = seat_ids[i]
