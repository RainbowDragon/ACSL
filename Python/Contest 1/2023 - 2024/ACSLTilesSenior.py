#
# ACSL 2023-2024 - Contest 1 - ACSL Tiles - Senior Division
#
#

#
# Complete the 'findHandSum' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
# 1. INTEGER originalRows
# 2. STRING handTiles
# 3. STRING drawPile
#
def findHandSum(originalRows, handTiles, drawPile):

    rows = [0] * 4
    for j in reversed(range(4)):
        rows[j] = originalRows % 10
        originalRows //= 10

    tile_list = handTiles.split()
    hand_list = []
    for tile in tile_list:
        hand_list.append(int(tile))

    tile_list = drawPile.split()
    draw_list = []
    for tile in tile_list:
        draw_list.append(int(tile))

    start_index = 0
    is_last_double = False

    while len(hand_list) != 0:
        is_matched = False

        for hand in hand_list:
            front = hand // 10
            back = hand % 10

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

            if is_matched:
                is_last_double = (front == back)
                if is_last_double:
                    start_index = index
                else:
                    start_index = index + 1

                hand_list.remove(hand)
                break

        if not is_matched:
            if len(draw_list) != 0:
                hand_list.append(draw_list[0])
                draw_list.pop(0)
            else:
                break

    result = 0
    for hand in hand_list:
        result += (hand % 10) + (hand // 10)

    return result


test_input = [[""] * 3] * 10
test_input[0] = ["5923", "56 27 73 34 99 45 32 17 64 57 18 11", "36 92 22 50 82"]
test_input[1] = ["1324", "85 31 32 96 25 1 68", "30 35 42 11 78 39 19 9 81"]
test_input[2] = ["7836", "57 62 19 97 3 11 28 92 66 87 45", "68 55 58 98 38 14 53 88 44 94 81 76 74 99 27 20"]
test_input[3] = ["4", "50 0 39 98 2 99 63 46 92 74 14 58 68 33 37", "51 42 95 60 67 77 84 7 96 8 35 10 19 22 11 82 40"]
test_input[4] = ["8937", "63 84 6 57 8 2 30 9 87 52 5", "58 40 62 54 27 96 35 99 61 56 14 51 88 13"]
test_input[5] = ["1453", "24 63 52 9 85 43 6 77 19", "1 33 47 57 46 21 13 84 56 82 39 50 55 16 92 70"]
test_input[6] = ["655", "23 55 55 45 94 99 12 99 89 32", "0"]
test_input[7] = ["2594", "53 44 48 68 93 95 12 3 0 87 81 74 91 15 23 63", "72 24 83 5 76 33 26 29 7 34 8 64 16"]
test_input[8] = ["51", "84 4 70 32 58 17 38 63 51 56 27", "60 43 0 99 50 95 20 82 25 88 10 64 14 45 66 81 53"]
test_input[9] = ["0", "93 70 65 9 66 14 46 68 20 63 21 71 88 30 31 1 75", "80 84 85 47 19 89 37 26 4 76 79 92 49 51 45 53 78"]

test_output = [16, 0, 102, 16, 71, 38, 13, 11, 67, 0]

for i in range(10):
    test_result = findHandSum(int(test_input[i][0]), test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
