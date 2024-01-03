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
def sumOfLastRow(s, d, r):

    result = 0
    numbers_to_skip = r * (r - 1) // 2

    current_number = transformToSingleDigit(s)
    for k in range(numbers_to_skip):
        current_number += d
        current_number = transformToSingleDigit(current_number)

    for k in range(r):
        result += transformToSingleDigit(current_number)
        current_number += d

    return result

def transformToSingleDigit(number):

    while number > 9:
        digit_sum = 0

        while number > 0:
            digit_sum += number % 10
            number //= 10

        number = digit_sum

    return number


test_input = [[0] * 3] * 10
test_input[0] = [2, 3, 5]
test_input[1] = [221, 2, 4]
test_input[2] = [184, 231, 35]
test_input[3] = [71, 5, 27]
test_input[4] = [1, 24, 100]
test_input[5] = [599, 23, 43]
test_input[6] = [4326, 1234, 80]
test_input[7] = [704, 1776, 200]
test_input[8] = [6283, 185, 31]
test_input[9] = [3141, 59, 26]

test_output = [28, 17, 140, 135, 397, 218, 399, 1003, 154, 126]

for i in range(10):
    test_result = sumOfLastRow(test_input[i][0], test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))

