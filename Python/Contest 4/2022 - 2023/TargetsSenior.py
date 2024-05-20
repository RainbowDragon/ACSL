#
# ACSL 2022-2023 - Contest 4 - Targets - Senior Division
#
#

#
# Complete the 'missingArrow' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
#  1. INTEGER size
#  2. STRING targets
#  3. STRING numbers
#  4. STRING arrows
#
def missing_arrow(size, targets, numbers, arrows):

    grid = [[False for x in range(size)] for y in range(size)]

    target_list = targets.split(" ")
    for j in range(len(target_list)):
        number = int(target_list[j])
        row = number // 10
        col = number % 10
        grid[row][col] = True

    row_count = [0] * size
    col_count = [0] * size
    for j in range(size):
        row_count[j] = int(numbers[j])
        col_count[j] = int(numbers[j+size+1])

    board = [[-1 for x in range(size)] for y in range(size)]

    arrow_list = arrows.split(" ")
    for k in range(len(arrow_list)):
        arrow = arrow_list[k]
        r = int(arrow[0])
        c = int(arrow[1])
        d = ord(arrow[2]) - ord('A')
        board[r][c] = d

    row_sum = [0] * size
    col_sum = [0] * size
    for r in range(size):
        for c in range(size):
            if board[r][c] >= 0:
                row_sum[r] += 1
                col_sum[c] += 1

    row = -1
    col = -1
    for k in range(size):
        if row_sum[k] < row_count[k]:
            row = k

        if col_sum[k] < col_count[k]:
            col = k

    for r in range(size):
        for c in range(size):
            if board[r][c] >= 0:
                update_board(grid, size, r, c, board[r][c])

    direction = -1
    for k in range(8):
        sr = row
        sc = col

        while 0 <= sr < size and 0 <= sc < size:
            if grid[sr][sc]:
                direction = k
                break

            sr += dr[k]
            sc += dc[k]

    return str(row) + str(col) + chr(direction+ord('A'))


dr = [0, -1, 0, 1, -1, -1, 1, 1]
dc = [-1, 0, 1, 0, -1, 1, 1, -1]


def update_board(grid, size, row, col, direction):

    sr = row
    sc = col

    while 0 <= sr < size and 0 <= sc < size:
        if grid[sr][sc]:
            grid[sr][sc] = False
            break

        sr += dr[direction]
        sc += dc[direction]

    return


test_input = [
    ["4", "02 11 20 21", "0103 1012", "13E 30B 33E"],
    ["6", "15 23 24 32 33 34 42 43 51", "401211 401211", "00G 20G 40G 53B 02G 03D 04G 35A"],
    ["6", "00 10 13 20 21 24 30 31 43 50 53", "420113 022124", "01H 02H 03H 04H 15H 14H 45A 51B 55E 52E"],
    ["6", "02 05 13 23 12 35 24 00", "011114 410111", "14F 20F 50F 53F 51F 40F 30F"],
    ["6", "01 10 23 42 53 12 04 52 00", "200232 111024", "05H 34H 35H 54E 55E 40F 45A 41B"],
    ["6", "02 04 10 13 20 33 35 40 44 55", "202204 212122", "00C 05A 22F 25D 32E 50B 51F 53E 54C"],
    ["6", "01 10 20 21 22 23 24 31 45 51", "310123 100441", "03A 04D 05H 13A 34A 43E 44E 53A 54F"],
    ["6", "11 12 13 30 35 41 42 43 45 51 52", "323102 422102", "00G 01G 02G 10G 15D 20D 22D 23G 31D 50C"],
    ["6", "11 20 22 31 32 33 40 42 44 50 51 53", "411132 231114", "00D 01D 04H 05H 15H 21D 30D 43A 45A 52A 55A"],
    ["6", "05 11 20 21 22 23 33 42 43 52 53 54", "321312 401043", "00D 02H 04H 14H 15B 24H 34H 35H 40C 50C 55A"]
]

test_output = [
    "32E", "30C", "35H", "55E", "02D", "34D", "50B", "55A", "41H", "30F"
]

for i in range(10):
    test_result = missing_arrow(int(test_input[i][0]), test_input[i][1], test_input[i][2], test_input[i][3])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
