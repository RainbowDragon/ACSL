#
# ACSL 2023-2024 - Contest 3 - ACSL Rack-O - Intermediate Division
#
#

#
# Complete the 'playRackO' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
# 1. STRING info
# 2. STRING rack
# 3. STRING pile
#
def play_rack_0(info, rack, pile):

    rack_list = [int(x) for x in rack.split()]
    if is_rack_in_order(rack_list):
        return get_rack_value1(rack_list)

    pile_list = [int(x) for x in pile.split()]
    for k in range(len(pile_list)):
        card = pile_list[k]

        is_updated = update_with_rule1(rack_list, card)

        if not is_updated:
            is_updated = update_with_rule2(rack_list, card)

        if not is_updated:
            is_updated = update_with_rule3(rack_list, card)

        if not is_updated:
            is_updated = update_with_rule4(rack_list, card)

        if not is_updated:
            is_updated = update_with_rule5(rack_list, card)

        if is_updated and is_rack_in_order(rack_list):
            return get_rack_value1(rack_list)

    return get_rack_value2(rack_list)


def update_with_rule1(rack_list, card):

    for k in range(1, len(rack_list)):
        if card + 1 == rack_list[k]:
            rack_list[k-1] = card
            return True

    return False


def update_with_rule2(rack_list, card):

    for k in range(len(rack_list)-1):
        if card - 1 == rack_list[k]:
            rack_list[k+1] = card
            return True

    return False


def update_with_rule3(rack_list, card):

    for k in range(1, len(rack_list)-1):
        if card > rack_list[k-1] and card < rack_list[k+1]:
            if not (rack_list[k] > rack_list[k-1] and rack_list[k] < rack_list[k+1]):
                rack_list[k] = card
                return True

    return False


def update_with_rule4(rack_list, card):

    if card < rack_list[1] and rack_list[0] > rack_list[1]:
        rack_list[0] = card
        return True

    return False


def update_with_rule5(rack_list, card):

    n = len(rack_list)
    if card > rack_list[n-2] and rack_list[n-1] < rack_list[n-2]:
        rack_list[n-1] = card
        return True

    return False


def is_rack_in_order(rack_list):

    for k in range(1, len(rack_list)):
        if rack_list[k] < rack_list[k-1]:
            return False

    return True


def get_rack_value1(rack_list):

    result = rack_list[0]
    count = 0
    in_sequence = False

    for k in range(1, len(rack_list)):
        result += rack_list[k]

        if rack_list[k] - rack_list[k-1] == 1:
            if in_sequence:
                count += 1
            else:
                count += 2
            in_sequence = True
        else:
            if count >= 3:
                result += count * 5
            in_sequence = False
            count = 0

    if count >= 3:
        result += count * 5

    return result


def get_rack_value2(rack_list):

    result = 0

    for k in range(1, len(rack_list)):
        if rack_list[k] < rack_list[k-1]:
            result -= 1

    return result


test_input = [
    ["10 60", "40 35 20 56 32 58 42 17 45 34", "31 44 10 28 19 46 7 37 16 2"],
    ["15 90", "15 12 18 9 28 17 46 51 7 53 65 70 74 84 47", "45 73 3 52 54 16 21 44 87 40 68 30 20"],
    ["12 130", "20 110 30 16 84 40 91 69 75 7 81 15", "39 47 114 55 35 71 25 123 51 23 34 10 77 36 115"],
    ["8 100", "6 13 47 62 32 70 76 12", "3 67 80 10 39 44 2 43 40 85 21 33 4 52"],
    ["12 110", "44 35 22 25 79 100 85 69 87 3 56 28", "97 10 48 43 42 21 81 47 86 88 80 54 24 50"],
    ["9 140", "74 135 61 45 92 122 14 98 138", "105 60 66 116 5 106 97 85 18 139 96"],
    ["15 70", "27 43 24 9 70 64 3 33 30 63 11 1 25 12 35", "69 15 10 2 34 66 21 49 23 51 5 57 38 40 53"],
    ["11 80", "68 52 8 25 22 18 29 16 74 48 34", "30 43 6 77 7 28 73 9 27 54 71 79 42 49"],
    ["15 60", "2 10 13 19 20 26 30 34 39 41 47 48 52 58 60", "9 31 50 59 1"],
    ["10 75", "70 65 60 55 50 45 40 35 30 20", "44 10 15 46 23 72 3 68 53 37 75 39"]
]

test_output = [
    341, 752, 656, 421, -1, 728, -2, 342, 499, 428
]

for i in range(10):
    test_result = play_rack_0(test_input[i][0], test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
