#
# ACSL 2022-2023 - Contest 4 - Targets - Junior Division
#
#

#
# Complete the 'arrowForMostTargets' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
#  1. INTEGER size
#  2. STRING targets
#
def arrow_for_most_targets(size, targets):

    grid = [[False for x in range(size)] for y in range(size)]

    target_list = targets.split(" ")
    for j in range(len(target_list)):
        number = int(target_list[j])
        row = number // 10
        col = number % 10
        grid[row][col] = True

    result = [-1, ""]

    for j in range(size):
        result = get_most_targets(grid, size, 0, j, result)

    for j in range(1, size-1):
        result = get_most_targets(grid, size, j, 0, result)
        result = get_most_targets(grid, size, j, size-1, result)

    for j in range(size):
        result = get_most_targets(grid, size, size-1, j, result)

    return result[1]


dr = [0, -1, 0, 1, -1, -1, 1, 1]
dc = [-1, 0, 1, 0, -1, 1, 1, -1]


def get_most_targets(grid, size, row, col, result):

    for k in range(8):
        count = 0
        in_target = False
        sr = row
        sc = col

        while 0 <= sr < size and 0 <= sc < size:
            if not in_target:
                if grid[sr][sc]:
                    in_target = True
                    count += 1
            else:
                if grid[sr][sc]:
                    count += 1
                else:
                    break

            sr += dr[k]
            sc += dc[k]

        if count > result[0]:
            result[0] = count
            result[1] = str(row) + str(col) + str(chr(ord('A') + k))

    return result


test_input = [
    ["6", "13 21 41 42 44"],
    ["5", "31 21 13 32 11 12"],
    ["10", "81 84 86 87 88 71 73 75 77 32 33 45 47 48 11 13 15 16"],
    ["8", "65 45 55 32 42 54 13 14 41 61 24"],
    ["4", "12 22 21"],
    ["9", "11 14 17 33 44 24 31 35 37 45 41 53 57 62 64 66 71 77"],
    ["7", "15 23 24 32 35 31 45 55 44"],
    ["6", "43 33 44 14 23 41"],
    ["10", "25 71 82 63 54 45 56 75 77 88 21 24 26 27 28 12 13 15 34 35 33 37"],
    ["8", "12 13 16 21 22 31 34 35 36 45 43 41 52 54 56 66 65 64 63 61"]
]

test_output = ["40C", "01D", "89A", "05D", "02D", "80F", "65B", "03D", "29A", "27H"]

for i in range(10):
    test_result = arrow_for_most_targets(int(test_input[i][0]), test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
