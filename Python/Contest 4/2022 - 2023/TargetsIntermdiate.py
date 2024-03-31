#
# ACSL 2022-2023 - Contest 4 - Targets - Intermediate Division
#
#

#
# Complete the 'targetsWithMostArrows' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
#  1. INTEGER size
#  2. STRING targets
#
def targets_with_most_arrows(size, targets):

    grid = [[False for x in range(size)] for y in range(size)]

    target_list = targets.split(" ")
    for j in range(len(target_list)):
        number = int(target_list[j])
        row = number // 10
        col = number % 10
        grid[row][col] = True

    board = [[0 for x in range(size)] for y in range(size)]

    for j in range(size):
        update_board(grid, board, size, 0, j)

    for j in range(1, size-1):
        update_board(grid, board, size, j, 0)
        update_board(grid, board, size, j, size-1)

    for j in range(size):
        update_board(grid, board, size, size-1, j)

    most_count = -1
    result = ""
    for j in range(1, size):
        for k in range(1, size):
            if board[j][k] > most_count:
                most_count = board[j][k]
                result = str(j) + str(k)
            elif board[j][k] == most_count:
                result += " " + str(j) + str(k)

    return result


dr = [0, -1, 0, 1, -1, -1, 1, 1]
dc = [-1, 0, 1, 0, -1, 1, 1, -1]


def update_board(grid, board, size, row, col):

    for k in range(8):
        sr = row
        sc = col

        while 0 <= sr < size and 0 <= sc < size:
            if grid[sr][sc]:
                board[sr][sc] += 1
                break

            sr += dr[k]
            sc += dc[k]

    return


test_input = [
    ["6", "13 21 41 42 44"],
    ["5", "31 21 13 32 11 12"],
    ["10", "81 84 86 87 88 71 73 75 77 32 33 45 47 48 11 13 15 16"],
    ["8", "65 45 55 32 42 54 13 14 41 61 24"],
    ["4", "12 22 21"],
    ["9", "11 14 17 33 44 24 31 35 37 45 41 53 57 62 64 66 71 77"],
    ["7", "15 23 24 32 35 31 45 55 44"],
    ["6", "11 22 33 44 14 23 41"],
    ["10", "71 82 63 54 45 56 75 77 88 21 24 26 27 28 12 13 15 34 35 33 37"],
    ["5", "11 12 13 21 22 23 31 32 33"]
]

test_output = [
    "13", "11 13", "16 32", "13 61", "12 21 22", "45", "31", "11 14 22 33 41 44", "63", "11 13 31 33"
]

for i in range(10):
    test_result = targets_with_most_arrows(int(test_input[i][0]), test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
