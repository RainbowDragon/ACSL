#
# ACSL 2023-2024 - Contest 1 - ACSL Tiles - Junior Division
#
#

#
# Complete the 'findDiscardSum' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
# 1. INTEGER originalRows
# 2. STRING tiles
#
def findDiscardSum(originalRows, tiles):

    rows = [0] * 4
    for j in reversed(range(4)):
        rows[j] = originalRows % 10
        originalRows //= 10

    result = 0

    tile_list = tiles.split()
    for tile in tile_list:
        cur = int(tile)
        front = cur // 10
        back = cur % 10
        is_matched = False

        for k in range(4):
            if rows[k] == front:
                is_matched = True
                rows[k] = back
                break
            elif rows[k] == back:
                is_matched = True
                rows[k] = front
                break

        if not is_matched:
            result += (front + back)

    return result


test_input = [[""] * 2] * 10
test_input[0] = ["5923", "56 85 27 73 14 34 62"]
test_input[1] = ["8423", "74 92 57 93 26 87 14 63 82 54 12"]
test_input[2] = ["1253", "51 81 35 84 95 26 59 13 71 35 46 28"]
test_input[3] = ["2694", "69 76 41 89 16 78 64 36 12 95 52"]
test_input[4] = ["6479", "58 73 92 54 75 35 78 25 81 24 16 95 36 82 14 27 43 13 51"]
test_input[5] = ["3972", "18 17 65 61 37 51 57 38 72 92 54 59 43 41 31 28"]
test_input[6] = ["9146", "95 74 51 19 75 26 32 39 35 31 25 73"]
test_input[7] = ["7918", "63 18 56 98 48 52 26 92 83 13 17 58 91 67 58"]
test_input[8] = ["9758", "52 14 51 27 77 62 76 82 96 56 46 49 87"]
test_input[9] = ["7169", "71 56 15 65 98 71 89 71 11 55 77 17 66 51"]

test_output = [18, 26, 31, 22, 45, 56, 0, 59, 48, 14]

for i in range(10):
    test_result = findDiscardSum(int(test_input[i][0]), test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
