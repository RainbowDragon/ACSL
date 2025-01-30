#
# ACSL 2023-2024 - Contest 3 - ACSL Rack-O - Junior Division
#
#

#
# Complete the 'playRackO' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
# 1. INTEGER slots
# 2. INTEGER cards
# 3. STRING rack
# 4. STRING pile
#
def play_rack_0(slots, cards, rack, pile):

    rack_list = [int(x) for x in rack.split()]
    if is_rack_in_order(rack_list):
        return get_rack_sum(rack_list)

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
            return get_rack_sum(rack_list)

    return 0


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


def get_rack_sum(rack_list):

    return sum(rack_list)


test_input = [
    ["10", "60", "40 35 20 56 32 58 42 17 45 34", "31 44 10 28 19 46 7 37 16 2"],
    ["15", "90", "15 56 38 9 28 17 46 51 7 53 65 70 74 84 47", "45 73 52 54 16 21 44 40 68 30 20 87"],
    ["8", "100", "6 13 47 62 32 70 76 12", "3 67 80 10 39 44 2 43 40 85 21 33 4 52"],
    ["12", "110", "44 35 22 25 79 100 85 69 87 3 56 28", "97 10 48 43 42 21 81 47 86 88 94 54 24 50"],
    ["10", "80", "29 22 11 40 55 58 48 4 45 44", "24 71 50 35 61 70 33 20 61 56 34 69"],
    ["9", "140", "74 135 61 45 92 122 14 98 138", "105 60 66 116 5 106 97 85 18 139 96"],
    ["15", "70", "27 43 24 9 70 64 3 33 30 63 11 1 25 12 35", "69 15 10 2 34 66 21 49 23 51 5 57 38 40 53"],
    ["11", "80", "68 52 8 25 22 18 29 16 74 48 34", "30 43 6 7 54 73 11 27 28 71 47 42 50"],
    ["15", "60", "2 10 13 19 20 26 30 34 39 41 47 48 52 58 60", "9 31 50 59 1"],
    ["10", "75", "70 65 60 55 50 45 40 35 30 10", "44 8 15 46 23 72 3 68 53 37 75 39"]
]

test_output = [
    326, 772, 421, 775, 0, 713, 0, 285, 499, 411
]

for i in range(10):
    test_result = play_rack_0(int(test_input[i][0]), int(test_input[i][1]), test_input[i][2], test_input[i][3])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
