#
# ACSL 2020-2021 - Contest 3 - MultipleArrays - Intermediate Division
#
#

#
# Complete the 'sumSmallestInVisitedCells' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. STRING rows_cols
#  2. STRING array1
#  3. STRING array2
#  4. STRING array3
#
def sum_smallest_in_visited_cells(rows_cols, array1, array2, array3):

    rows, cols = (int(x) for x in rows_cols.split())
    matrix1 = parse_array(array1, rows, cols)
    matrix2 = parse_array(array2, rows, cols)
    matrix3 = parse_array(array3, rows, cols)

    pos = [0, 0]
    result = find_smallest(matrix1, matrix2, matrix3, pos[0], pos[1])
    while pos[0] + 1 < rows and pos[1] + 1 < cols:
        cur_max = matrix1[pos[0]][pos[1]+1] - 1
        max_count = 1
        next_row = pos[0]
        next_col = pos[1] + 1

        for matrix in [matrix1, matrix2, matrix3]:
            if matrix[pos[0]][pos[1]+1] > cur_max:
                cur_max = matrix[pos[0]][pos[1]+1]
                max_count = 1
                next_row = pos[0]
                next_col = pos[1] + 1
            elif matrix[pos[0]][pos[1]+1] == cur_max:
                max_count += 1

            if matrix[pos[0]+1][pos[1]] > cur_max:
                cur_max = matrix[pos[0]+1][pos[1]]
                max_count = 1
                next_row = pos[0] + 1
                next_col = pos[1]
            elif matrix[pos[0]+1][pos[1]] == cur_max:
                max_count += 1

        if max_count == 1:
            pos[0] = next_row
            pos[1] = next_col
        else:
            pos[0] += 1
            pos[1] += 1

        result += find_smallest(matrix1, matrix2, matrix3, pos[0], pos[1])

    return result


def parse_array(s, r, c):
    array = [int(x) for x in s.split()]
    matrix = []
    for j in range(r):
        matrix.append(array[:c])
        array = array[c:]

    return matrix


def find_smallest(m1, m2, m3, r, c):
    nums = []
    for m in [m1, m2, m3]:
        nums.append(m[r][c])

    return min(nums)


test_input = [
    [
        "3 4",
        "1 2 3 4 7 7 8 9 5 6 7 8",
        "6 8 6 4 4 5 3 2 8 3 1 9",
        "3 6 7 3 4 6 2 1 3 2 5 5"
    ],
    [
        "4 2",
        "31 17 24 19 15 29 22 26",
        "25 13 25 18 19 27 19 13",
        "12 15 17 18 29 16 25 20"
    ],
    [
        "4 5",
        "3 1 4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4",
        "6 2 6 4 3 3 8 3 2 7 7 2 3 8 3 3 4 6 2 6",
        "5 8 3 2 3 9 7 9 8 5 3 5 6 2 9 5 1 4 1 3"
    ],
    [
        "4 3",
        "3 1 4 1 5 2 2 6 5 3 5 8",
        "9 7 1 3 2 6 8 4 6 2 6 4",
        "3 2 1 3 2 1 1 2 3 1 2 3"
    ],
    [
        "4 6",
        "4 8 3 7 1 6 7 6 2 4 3 3 7 5 1 0 5 8 2 0 9 5 3 2",
        "4 4 5 9 2 3 0 2 1 9 6 4 0 6 2 8 4 2 0 8 9 3 5 2",
        "6 9 5 0 3 4 8 7 5 3 4 2 1 1 7 10 6 7 9 3 1 2 3 2"
    ],
    [
        "5 3",
        "31 41 59 26 53 58 97 93 23 84 62 64 33 83 27",
        "95 28 84 19 71 69 39 93 75 10 85 20 97 49 44",
        "59 23 78 61 40 62 97 20 49 98 62 80 34 83 53"
    ],
    [
        "4 5",
        "-3 -1 -4 -1 -5 -9 -2 -6 -5 -3 -5 -8 -9 -7 -4 -3 -5 -3 -8 -4",
        "-6 -2 -6 -4 -3 -3 -8 -3 -2 -7 -9 -5 -2 -8 -8 -4 -4 -9 -7 -1",
        "-6 -9 -3 -9 -9 -3 -7 -5 -1 -5 -8 -2 -9 -7 -4 -9 -4 -4 -5 -9"
    ],
    [
        "5 5",
        "1 2 3 4 5 6 7 8 9 10 11 12 13 12 11 10 9 8 7 8 5 4 3 2 1",
        "2 4 6 8 10 12 14 16 18 20 22 20 18 16 14 12 10 8 26 4 2 4 6 8 10",
        "1 3 5 7 9 11 13 15 17 19 21 23 25 23 21 19 17 15 13 1 9 7 5 3 1"
    ]
]

test_output = [6, 60, 16, 9, 11, 159, -49, 63]

for i in range(8):
    test_result = sum_smallest_in_visited_cells(test_input[i][0], test_input[i][1], test_input[i][2], test_input[i][3])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
