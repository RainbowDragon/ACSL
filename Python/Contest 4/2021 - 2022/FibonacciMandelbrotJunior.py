#
# ACSL 2021-2022 - Contest 4 - Fibonacci & Mandelbrot - Junior Division
#
#

#
# Complete the 'absResult' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
#  1. FLOAT realPartC
#  2. FLOAT imagPartC
#
import math


def abs_result(real_part_c, imag_part_c):

    c = [real_part_c, imag_part_c]
    number = [0.0, 0.0]

    for k in range(1, 6):
        number = function(number, c)
        if absolute_value_square(number) > 16:
            return "ESCAPES " + str(k)

    return str("{:.3f}".format(math.sqrt(absolute_value_square(number))))


def addition(first, second):
    return [first[0]+second[0], first[1]+second[1]]


def multiplication(first, second):
    return [first[0]*second[0] - first[1]*second[1], first[0]*second[1] + first[1]*second[0]]


def absolute_value_square(number):
    return number[0]*number[0] + number[1]*number[1]


def function(number, c):
    return addition(multiplication(number, number), c)


test_input = [
    [0.3, -0.67], [2.0, -0.3], [-0.5, 0.56], [-0.62, 0.43], [0.0, 0.543],
    [0.45, -0.56], [-0.325, 0.765], [0.561, -0.456], [-0.213, 0.729], [-0.053, 0.0]
]

test_output = [
    "0.817", "ESCAPES 2", "0.018", "0.508", "0.574",
    "2.153", "1.057", "ESCAPES 5", "0.834", "0.050"
]

for i in range(10):
    test_result = abs_result(test_input[i][0], test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
