#
# ACSL 2021-2022 - Contest 4 - Fibonacci & Mandelbrot - Intermediate Division
#
#

#
# Complete the 'cycleLength' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
#  1. FLOAT realPartC
#  2. FLOAT imagPartC
#
def cycle_length(real_part_c, imag_part_c):

    c = [real_part_c, imag_part_c]
    number = [0.0, 0.0]
    number_list = [number]
    n = 0

    while n <= 500:
        n += 1
        number = function(number, c)

        if absolute_value_square(number) > 16:
            return "ESCAPES " + str(n)

        for k in range(n):
            if number_list[k][0] == number[0] and number_list[k][1] == number[1]:
                return str(n - k)

        number_list.append(number)

    return ""


def addition(first, second):
    return [first[0]+second[0], first[1]+second[1]]


def multiplication(first, second):
    return [first[0]*second[0] - first[1]*second[1], first[0]*second[1] + first[1]*second[0]]


def absolute_value_square(number):
    return number[0]*number[0] + number[1]*number[1]


def round_to_decimal(number):
    return [round(number[0], 2), round(number[1], 2)]


def function(number, c):
    return round_to_decimal(addition(round_to_decimal(multiplication(number, number)), c))


test_input = [
    [-0.1, 0.75],
    [2.0, -0.3],
    [-0.5, 0.56],
    [-1.21, -0.32],
    [0.01, -0.64],
    [-0.52, 0.57],
    [-1.07, -0.2],
    [-1.04, -0.28],
    [0.33, 0.77],
    [0.26, -0.02]
]

test_output = [
    "3",
    "ESCAPES 2",
    "5",
    "8",
    "13",
    "5",
    "8",
    "21",
    "ESCAPES 6",
    "1"
]

for i in range(10):
    test_result = cycle_length(test_input[i][0], test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
