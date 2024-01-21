#
# ACSL 2023-2024 - Contest 1 - ACSL Tiles - Intermediate Division
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
    start_index = 0
    is_last_double = False

    tile_list = tiles.split()
    for tile in tile_list:
        cur = int(tile)
        front = cur // 10
        back = cur % 10

        is_matched = False
        index = start_index

        if is_last_double:
            if rows[index] == front:
                is_matched = True
                rows[index] = back
            elif rows[index] == back:
                is_matched = True
                rows[index] = front
        else:
            for k in range(4):
                index = (start_index + k) % 4
                if rows[index] == front:
                    is_matched = True
                    rows[index] = back
                    break
                elif rows[index] == back:
                    is_matched = True
                    rows[index] = front
                    break

        if not is_matched:
            result += (front + back)
        else:
            is_last_double = (front == back)
            if is_last_double:
                start_index = index
            else:
                start_index = index + 1

    return result


test_input = [[""] * 2] * 10
test_input[0] = ["5923", "56 27 73 34 99 45 32 19 64 57 18"]
test_input[1] = ["4687", "81 72 15 89 36 21 13 67 42 93 48 83 45 47 52 94 62"]
test_input[2] = ["1932", "94 81 13 43 21 31 89 69 18 28 86 88 29 89 92"]
test_input[3] = ["1957", "32 69 87 73 31 88 62"]
test_input[4] = ["1542", "24 44 39 32 92 63 47 76 37 78 38"]
test_input[5] = ["5179", "14 92 71 51 42 19 18 28 89 29 96 46 13 57 78 27"]
test_input[6] = ["4287", "69 36 21 93 94 35 83 62 88 97 18 72 42 73 75 31 28 52 66 87"]
test_input[7] = ["1745", "21 37 92 42 93 96 19 72 78 18 71 36 56 85 99 97 31 98 23"]
test_input[8] = ["6655", "23 55 55 45 94 99 12 99 89 32 77 65 58 57 66 27 16 76"]
test_input[9] = ["3333", " 43 35 55 34 37 53 23 49 13 22 98 12 33 11 53 79 57 39 77 43 72 22 12 48 17 15 75"]

test_output = [21, 86, 11, 23, 46, 16, 114, 61, 36, 67]

for i in range(10):
    test_result = findDiscardSum(int(test_input[i][0]), test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
