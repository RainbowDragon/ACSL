#
# ACSL 2023-2024 - Contest 3 - ACSL Rack-O - Senior Division
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

    infos = info.split()
    slots = int(infos[0])
    cards = int(infos[1])

    rack_list = [int(x) for x in rack.split()]
    if is_rack_in_order(rack_list):
        return get_rack_as_string(rack_list)

    current = get_rack_step_down_value(rack_list)

    pile_list = [int(x) for x in pile.split()]
    for k in range(len(pile_list)):
        card = pile_list[k]

        [value1, index1] = use_ideal_slot(rack_list, slots, cards, card)
        [value2, index2] = use_ascending_order(rack_list, card)

        if index1 != -1 and index2 != -1:
            if min(value1, value2) <= current:
                current = min(value1, value2)
                if value1 <= value2:
                    rack_list[index1] = card
                else:
                    rack_list[index2] = card
        else:
            if index1 != -1 and value1 <= current:
                current = value1
                rack_list[index1] = card
            elif index2 != -1 and value2 <= current:
                current = value2
                rack_list[index2] = card

        if is_rack_in_order(rack_list):
            return get_rack_as_string(rack_list)

    return get_rack_as_string(rack_list)


def is_rack_in_order(rack_list):

    for k in range(1, len(rack_list)):
        if rack_list[k] < rack_list[k-1]:
            return False

    return True


def get_rack_as_string(rack_list):

    sb = [str(rack_list[0])]
    for k in range(1, len(rack_list)):
        sb.append(" ")
        sb.append(str(rack_list[k]))

    return "".join(sb)


def get_rack_step_down_value(rack_list):

    result = 0
    for k in range(1, len(rack_list)):
        if rack_list[k] < rack_list[k-1]:
            result += 1

    return result


def use_ideal_slot(rack_list, slots, cards, card):

    copy_list = rack_list.copy()
    size = cards // slots
    if cards % slots != 0:
        size += 1

    index = (card - 1) // size
    low = index * size + 1
    high = (index + 1) * size
    if index == slots - 1:
        high = cards

    if rack_list[index] < low or rack_list[index] > high:
        copy_list[index] = card
    else:
        index = -1

    value = get_rack_step_down_value(copy_list)

    return [value, index]


def use_ascending_order(rack_list, card):

    copy_list = rack_list.copy()

    index = -1
    found = False
    for k in range(1, len(rack_list)-1):
        if not (rack_list[k] >= rack_list[k-1] and rack_list[k] <= rack_list[k+1]):
            if card <= rack_list[k] and rack_list[k] <= rack_list[k+1]:
                index = k - 1
                found = True
            elif card >= rack_list[k-1] and card <= rack_list[k+1]:
                index = k
                found = True
            elif rack_list[k-1] <= rack_list[k] and rack_list[k] <= card:
                index = k + 1
                found = True

        if found:
            copy_list[index] = card
            break

    value = get_rack_step_down_value(copy_list)

    return [value, index]


test_input = [
    ["9 70", "40 35 30 56 32 58 44 17 45", "31 37 10 28 21 62 7 64 16 12"],
    ["15 90", "15 12 18 9 28 17 46 51 7 53 65 70 74 84 47", "45 73 3 52 54 16 21 44 87 40 68 30 37"],
    ["12 130", "20 110 30 16 84 40 91 69 75 7 81 15", "33 47 114 55 35 71 25 123 51 23 34 10 100 77 36 115"],
    ["8 100", "6 13 47 62 32 70 76 12", "3 67 80 10 39 44 2 43 90 85 21 63 4 52"],
    ["12 110", "44 35 22 25 79 100 85 69 87 3 56 28", "97 10 48 43 42 21 81 47 86 88 80 54 24 50"],
    ["9 140", "74 135 61 45 92 122 14 98 138", "105 60 66 116 5 106 97 85 18 139 96"],
    ["15 75", "27 43 24 9 70 64 3 33 30 63 11 1 25 12 35", "69 15 10 2 34 66 21 49 23 51 5 57 38 40 53"],
    ["11 85", "68 52 8 25 22 18 29 16 74 48 34", "30 43 6 77 7 28 73 9 27 54 71 79 42 49"],
    ["15 60", "2 10 13 19 20 26 30 34 39 41 47 48 52 58 60", "9 31 50 59 1"],
    ["10 75", "70 65 60 55 50 45 40 35 30 20", "44 10 15 46 23 72 3 68 53 37 52 75 39 51 18 71 73 62"]
]

test_output = [
    "7 10 21 31 32 37 44 62 64",
    "3 12 18 21 30 37 40 51 52 53 65 70 74 84 87",
    "10 25 30 34 35 40 47 69 75 100 114 123",
    "6 21 39 43 63 70 80 85",
    "10 35 81 86 88 24 42 80 87 97 56 28",
    "5 18 61 66 92 97 106 116 138",
    "2 43 53 9 23 64 66 10 30 49 51 57 25 34 35",
    "6 7 8 25 30 43 54 71 74 77 79",
    "2 10 13 19 20 26 30 34 39 41 47 48 52 58 60",
    "3 10 23 46 50 51 52 53 72 75"
]

for i in range(10):
    test_result = play_rack_0(test_input[i][0], test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
