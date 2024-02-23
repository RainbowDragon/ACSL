#
# ACSL 2020-2021 - Contest 3 - MultipleArrays - Senior Division
#
#

#
# Complete the 'sumOfMinAlongPath' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. STRING dim
#  2. STRING_ARRAY arrays
#
def sumOfMinAlongPath(dim, arrays):

    rows, cols = (int(x) for x in dim.split())
    matrix = parseArray(arrays, rows, cols)

    visited = [[False for a in range(cols)] for b in range(rows)]
    pos = [0, 0]
    result = 0
    while not visited[pos[0]][pos[1]]:
        visited[pos[0]][pos[1]] = True
        result += findSmallest(matrix, pos[0], pos[1])
        value_dict = {}
        for dr in [-1, 0, 1]:
            if 0 <= pos[0] + dr < rows:
                for dc in [-1, 0, 1]:
                    if 0 <= pos[1] + dc < cols:
                        if dr != 0 or dc != 0:
                            for m in matrix:
                                if m[pos[0]+dr][pos[1]+dc] in value_dict:
                                    value_dict.update({m[pos[0]+dr][pos[1]+dc]: value_dict.get(m[pos[0]+dr][pos[1]+dc])+1})
                                else:
                                    value_dict.update({m[pos[0]+dr][pos[1]+dc]: 1})

        temp = []
        for key in value_dict:
            if value_dict[key] == 1:
                temp.append(key)
        cur_max = max(temp)

        found = False
        for dr in [-1, 0, 1]:
            if 0 <= pos[0] + dr < rows:
                for dc in [-1, 0, 1]:
                    if 0 <= pos[1] + dc < cols:
                        if dr != 0 or dc != 0:
                            for m in matrix:
                                if m[pos[0]+dr][pos[1]+dc] == cur_max:
                                    pos[0] = pos[0]+dr
                                    pos[1] = pos[1]+dc
                                    found = True
                                    break
                    if found:
                        break
            if found:
                break

    return result


def parseArray(arrays, r, c):

    matrix = []
    for s in arrays:
        array = [int(x) for x in s.split()]
        m = []
        for j in range(r):
            m.append(array[:c])
            array = array[c:]

        matrix.append(m)

    return matrix


def findSmallest(matrix, r, c):
    nums = []
    for m in matrix:
        nums.append(m[r][c])

    return min(nums)


test_input = []
test_input.append(["3 4", "6 2 7 4 6 1 5 7 5 6 7 8", "4 8 6 4 4 5 7 2 7 6 5 4", "3 6 9 2 4 8 2 6 3 2 1 4", "4 3 5 7 6 8 9 1 2 9 3 5"])
test_input.append(["4 4", "5 2 8 3 1 8 5 3 0 7 1 7 9 5 8 6", "5 4 0 9 5 4 6 2 8 1 8 2 8 1 7 2", "2 7 1 8 2 8 5 8 2 8 4 5 9 0 4 5"])
test_input.append(["5 3", "9 9 9 8 8 8 7 7 7 6 6 6 5 5 5", "5 6 7 8 5 5 6 7 8 9 5 6 7 8 9", "5 6 3 2 1 9 4 3 2 1 5 4 3 2 1", "5 5 5 6 6 6 7 7 7 8 8 8 9 9 9", "1 2 3 4 5 6 7 8 9 8 7 6 5 4 3"])
test_input.append(["3 5", "5 3 4 5 6 7 8 9 8 7 6 5 4 3 2", "1 3 5 7 9 7 5 9 1 2 4 3 8 6 4", "3 2 4 5 1 6 5 8 9 2 3 8 1 4 6"])
test_input.append(["5 3", "8 6 4 2 5 2 4 6 8 7 8 6 4 2 0", "7 9 7 5 3 8 3 6 7 9 1 9 7 5 3", "5 2 3 4 8 6 7 8 9 5 2 3 4 5 6", "9 4 9 2 3 9 1 8 7 6 5 4 3 2 8", "1 5 4 7 8 9 3 2 1 4 5 6 9 8 7"])
test_input.append(["5 6", "3 1 4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4 6 2 6 4 3 3 8 3 2 7", "7 2 3 8 3 3 4 6 2 6 4 8 3 2 3 9 7 9 8 5 3 5 6 2 9 5 1 4 1 3", "6 2 8 3 1 8 5 3 6 7 1 8 6 2 5 3 1 8 5 3 4 7 6 8 6 2 8 3 1 8", "2 7 1 8 2 8 1 8 2 8 4 6 2 7 1 8 2 8 1 8 2 8 4 6 2 7 1 8 2 8", "1 4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4 6 2 6 4 3 3 8 3 2 7 3", "4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4 6 2 6 4 3 3 8 3 2 7 3 1"])
test_input.append(["5 4", "11 12 13 14 15 16 17 18 19 20 11 12 13 14 15 16 17 18 19 20", "21 22 23 24 24 14 16 18 20 18 28 38 10 12 14 12 12 12 14 14", "12 11 23 13 15 25 17 27 19 29 11 11 13 13 15 15 17 17 19 19", "21 31 15 27 11 23 27 19 23 29 31 19 18 17 16 15 14 13 12 11"])
test_input.append(["4 5", "-2 -1 -4 -1 -5 -9 -2 -6 -5 -3 -5 -4 -9 -7 -9 -3 -2 -3 -8 -4", "-6 -2 -6 -4 -3 -3 -8 -3 -2 -7 -1 -2 -4 -8 -4 -2 -1 -1 -3 -9", "-2 -4 -6 -8 -6 -5 -2 -3 -3 -5 -7 -9 -7 -5 -3 -5 -2 -3 -5 -7", "-4 -5 -2 -6 -9 -1 -3 -6 -8 -9 -1 -2 -5 -6 -2 -9 -6 -5 -3 -2", "-3 -1 -4 -1 -5 -9 -2 -6 -5 -3 -5 -8 -9 -7 -9 -3 -2 -3 -8 -4", "-6 -2 -6 -4 -3 -3 -8 -3 -2 -7 -3 -1 -8 -1 -5 -9 -2 -6 -5 -3", "-5 -8 -9 -7 -9 -3 -2 -3 -8 -4 -6 -2 -6 -4 -3 -3 -8 -3 -2 -7"])

test_output = [6, 12, 6, 17, 9, 14, 60, -48]

for i in range(8):
    test_result = sumOfMinAlongPath(test_input[i][0], test_input[i][1:])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
