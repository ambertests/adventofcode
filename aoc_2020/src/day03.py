input = [line.strip() for line in open('input/day03.txt').readlines()]

row_count = len(input)
row_len = len(input[0])
# Right 1, down 1.
# Right 3, down 1. (This is the slope you already checked.)
# Right 5, down 1.
# Right 7, down 1.
# Right 1, down 2.
slopes = [
(1,1),
(3,1),
(5,1),
(7,1),
(1,2)
]
tree_counts = []
for slope in slopes:
    # "down" is the row_offset
    row_offset = slope[1]
    col_offset = slope[0]
    row = row_offset
    col = col_offset
    trees = 0
    while row < row_count:
        if input[row][col] == "#":
            trees += 1
        row += row_offset
        col += col_offset
        if col >= row_len:
            col -= row_len
    tree_counts.append(trees)
        
print(tree_counts)
print("Part 1:", tree_counts[1])
tree_product = 1
for t in tree_counts:
    tree_product *= t
print("Part 2:", tree_product)