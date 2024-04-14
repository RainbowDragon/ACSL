#
# ACSL 2019-2020 - Contest 4 - Patolli - Senior Division
#
#

def get_final_location(input_str):

    tokens = input_str.split(" ")

    player1 = [0] * 3
    for k in range(3):
        player1[k] = int(tokens[k])

    player2 = [0] * 3
    for k in range(3):
        player2[k] = int(tokens[k+3])

    number_of_rolls = int(tokens[6])
    for r in range(number_of_rolls):
        roll = int(tokens[r+7])

        markers = set(())
        player1.sort()
        player2.sort()
        for j in range(3):
            if player1[j] != 52:
                markers.add(player1[j])
            if player2[j] != 52:
                markers.add(player2[j])

        if r % 2 == 0:
            markers.remove(player1[0])
            player1[0] = move(player1[0], roll, markers)
        else:
            markers.remove(player2[0])
            player2[0] = move(player2[0], roll, markers)

    sum1 = 0
    sum2 = 0
    for j in range(3):
        if player1[j] != 52:
            sum1 += player1[j]
        if player2[j] != 52:
            sum2 += player2[j]

    return str(sum1) + " " + str(sum2)


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
    "3 15 18 5 13 21 4 4 5 4 4",
    "1 11 20 3 7 16 8 3 5 6 4 6 6 6 1",
    "18 22 15 6 10 5 6 3 2 6 5 1 4",
    "12 20 15 40 35 30 5 1 2 3 4 5",
    "25 20 15 12 9 6 7 6 5 4 5 3 1 6",
    "1 9 18 3 10 17 8 3 1 6 4 6 6 5 5",
    "40 44 48 50 30 45 12 5 3 1 2 4 6 5 4 3 2 1 6",
    "38 41 48 34 42 46 10 6 6 5 1 6 3 5 1 5 2",
    "4 20 38 12 23 44 10 5 6 4 6 3 6 3 4 4 3",
    "17 34 41 15 29 39 16 6 1 5 1 4 6 2 3 5 1 5 5 5 3 3 6"
]

test_output = [
    "49 46", "51 34", "55 37", "54 111", "71 33",
    "31 44", "144 138", "145 135", "85 113", "138 124"
]

for i in range(10):
    test_result = get_final_location(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
