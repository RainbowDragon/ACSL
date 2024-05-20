#
# ACSL 2020-2021 - Contest 1 - Numeral Triangles - Junior Division
#
#

#
# Complete the 'sumOfLastRow' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER s
#  2. INTEGER d
#  3. INTEGER r
#
def sum_of_last_row(s, d, r):

    result = 0
    numbers_to_skip = r * (r - 1) // 2

    current_number = transform_to_single_digit(s)
    for k in range(numbers_to_skip):
        current_number += d
        current_number = transform_to_single_digit(current_number)

    for k in range(r):
        result += transform_to_single_digit(current_number)
        current_number += d

    return result


def transform_to_single_digit(number):

    while number > 9:
        digit_sum = 0

        while number > 0:
            digit_sum += number % 10
            number //= 10

        number = digit_sum

    return number


test_input = [
    [2, 3, 5], [221, 2, 4], [184, 231, 35], [71, 5, 27], [1, 24, 100],
    [599, 23, 43], [4326, 1234, 80], [704, 1776, 200], [6283, 185, 31], [3141, 59, 26]
]

test_output = [28, 17, 140, 135, 397, 218, 399, 1003, 154, 126]

for i in range(10):
    test_result = sum_of_last_row(test_input[i][0], test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
