#
# ACSL 2019-2020 - Contest 4 - Patolli - Intermediate Division
#
#

def get_final_location(input_str):

    tokens = input_str.split(" ")
    markers = set(())
    for k in range(3):
        markers.add(int(tokens[k]))

    starts = [0] * 3
    for k in range(3):
        starts[k] = int(tokens[k+3])
    number_of_rolls = int(tokens[6])

    for k in range(number_of_rolls):
        roll = int(tokens[k+7])
        starts.sort()
        if starts[0] in markers:
            markers.remove(starts[0])
        markers.add(starts[1])
        markers.add(starts[2])
        starts[0] = move(starts[0], roll, markers)
        markers.remove(starts[1])
        markers.remove(starts[2])

    ends = []
    for k in range(3):
        if starts[k] != 52:
            ends.append(starts[k])

    if len(ends) == 0:
        return "GAME OVER"
    else:
        ends.sort()
        sb = []
        for k in range(len(ends)):
            if k > 0:
                sb.append(" ")
            sb.append(str(ends[k]))
        return "".join(sb)


def move(current, roll, markers):

    next_location = current + roll

    if current == 52 or next_location > 52 or next_location in markers:
        next_location = current
    elif is_prime(next_location):
        for k in range(6):
            if (next_location+1) in markers:
                break
            else:
                next_location += 1
    elif is_perfect_square(next_location):
        for k in range(6):
            if (next_location-1) in markers:
                break
            else:
                next_location -= 1
    elif will_move_horizontal_and_vertical(current, next_location):
        for k in range(next_location, current-1, -1):
            next_location = k
            if k % roll == 0 and k not in markers:
                break

    return next_location


def is_prime(number):

    for k in range(2, number):
        if number % k == 0:
            return False

    return True


def is_perfect_square(number):

    for k in range(1, number):
        if k * k == number:
            return True

    return False


start_of_turn = [6, 11, 16, 21, 26, 34, 39, 44, 49]


def will_move_horizontal_and_vertical(current, next_location):

    for k in range(len(start_of_turn)):
        if current <= start_of_turn[k] and next_location >= start_of_turn[k] + 2:
            return True

    return False


test_input = [
    "4 14 24 1 8 12 6 6 3 5 1 5 6",
    "14 28 31 10 20 24 7 6 6 5 5 6 2 4",
    "5 30 33 3 20 24 8 6 6 6 5 6 3 4 6",
    "4 11 15 2 8 20 5 5 2 5 1 6",
    "45 50 48 42 46 40 6 3 2 6 5 4 1",
    "37 41 47 35 43 48 6 5 5 6 5 4 2",
    "13 29 39 15 21 31 10 4 5 2 4 6 6 5 5 6 5",
    "43 47 40 28 30 32 10 5 3 2 6 1 5 2 6 3 2",
    "1 5 10 2 12 8 12 5 5 4 4 3 3 2 2 1 1 6 6",
    "20 25 15 30 18 24 16 6 6 4 5 2 1 6 4 2 3 6 5 4 5 3 1"
]

test_output = [
    "13 15 18", "26 29 30", "20 23 24", "14 16 20", "44 46 47",
    "49 50", "34 35 36", "37 38 39", "9 11 12", "32 33 35"
]

for i in range(10):
    test_result = get_final_location(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
