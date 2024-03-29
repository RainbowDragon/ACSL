#
# ACSL 2020-2021 - Contest 1 - Numeral Triangles - Senior Division
#
#

#
# Complete the 'sumOfLastRow' function below.
#
# The function is expected to return a CHARACTER.
# The function accepts following parameters:
#  1. STRING s
#  2. STRING d
#  3. INTEGER r
#
def sum_of_last_row(s, d, r):

    result = 0
    numbers_to_skip = r * (r - 1) // 2

    start_number = int(s, 16)
    delta = int(d, 16)
    current_number = start_number + delta * numbers_to_skip

    for k in range(r):
        result += transform_to_single_hex_digit(current_number)
        current_number += delta

    result = transform_to_single_hex_digit(result)
    hex_digits = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"]

    return hex_digits[result]


def transform_to_single_hex_digit(number):

    while number > 15:
        digit_sum = 0

        while number > 0:
            digit_sum += number % 16
            number //= 16

        number = digit_sum

    return number


test_input = [[""] * 3] * 10
test_input[0] = ["A", "9", "5"]
test_input[1] = ["ABC", "F", "4"]
test_input[2] = ["BAD", "50", "10"]
test_input[3] = ["FED", "ABC", "25"]
test_input[4] = ["184", "231", "35"]
test_input[5] = ["ABE", "CAB", "40"]
test_input[6] = ["31415", "92653", "60"]
test_input[7] = ["DEAF", "BED", "72"]
test_input[8] = ["BAD", "DAD", "100"]
test_input[9] = ["704", "1776", "244"]

test_output = ["5", "C", "A", "F", "5", "5", "F", "3", "A", "E"]

for i in range(10):
    test_result = sum_of_last_row(test_input[i][0], test_input[i][1], int(test_input[i][2]))

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
