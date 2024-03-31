#
# ACSL 2019-2020 - Contest 4 - Patolli - Junior Division
#
#

def get_final_location(input_str):

    tokens = input_str.split(" ")
    markers = set(())
    for k in range(3):
        markers.add(int(tokens[k]))

    current = int(tokens[3])
    number_of_rolls = int(tokens[4])

    for k in range(number_of_rolls):
        roll = int(tokens[k+5])
        current = move(current, roll, markers)

    if current == 52:
        return "GAME OVER"
    else:
        return str(current)


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
    "4 6 8 1 5 6 3 5 1 1",
    "10 24 32 8 4 4 4 3 5",
    "10 22 32 8 7 4 4 3 5 5 5 6",
    "17 20 27 16 7 3 5 4 6 5 1 4",
    "43 46 50 40 5 3 1 2 4 4",
    "25 27 49 22 7 2 2 6 6 5 3 6",
    "50 41 38 45 9 4 2 5 3 1 6 4 3 1",
    "21 26 30 19 6 6 4 6 1 2 3",
    "5 14 18 2 7 2 5 4 5 2 1 6",
    "10 17 20 9 12 4 5 3 1 6 2 3 3 5 4 1 6"
]

test_output = ["17", "23", "33", "34", "GAME OVER", "42", "GAME OVER", "27", "15", "48"]

for i in range(10):
    test_result = get_final_location(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
