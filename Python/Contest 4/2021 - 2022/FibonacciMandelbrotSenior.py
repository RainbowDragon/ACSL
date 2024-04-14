#
# ACSL 2021-2022 - Contest 4 - Fibonacci & Mandelbrot - Senior Division
#
#

#
# Complete the 'numFibonacciCycles' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. FLOAT realPartLL
#  2. FLOAT imagPartLL
#  3. FLOAT realPartUR
#  4. FLOAT imagPartUR
#  5. FLOAT incr
#
def num_fibonacci_cycles(real_part_ll, imag_part_ll, real_part_ur, imag_part_ur, incr):

    nx = round((real_part_ur - real_part_ll) / incr) + 1
    ny = round((imag_part_ur - imag_part_ll) / incr) + 1

    fibonacci_set = get_fibonacci_set()

    result = 0

    for x in range(nx):
        for y in range(ny):
            real_part = real_part_ll + incr * x
            imag_part = imag_part_ll + incr * y
            cycle_length = get_cycle_length(real_part, imag_part)

            if cycle_length in fibonacci_set:
                result += 1

    return result


def get_fibonacci_set():

    fibonacci_set = set(())
    first = 1
    second = 2
    fibonacci_set.add(1)
    fibonacci_set.add(2)

    while second <= 500:
        temp = first
        first = second
        second = temp + first
        fibonacci_set.add(second)

    return fibonacci_set


def get_cycle_length(real_part, imag_part):

    c = round_to_decimal([real_part, imag_part])
    number = [0.0, 0.0]
    number_list = [number]

    cycle_length = 0
    n = 0
    while n <= 500:
        n += 1
        number = function(number, c)

        if absolute_value_square(number) > 16:
            cycle_length = 0
            break

        found = False
        for j in range(n):
            if number_list[j][0] == number[0] and number_list[j][1] == number[1]:
                found = True
                cycle_length = n - j
                break

        if found:
            break

        number_list.append(number)

    return cycle_length


def addition(first, second):
    return [first[0]+second[0], first[1]+second[1]]


def multiplication(first, second):
    return [first[0]*second[0] - first[1]*second[1], first[0]*second[1] + first[1]*second[0]]


def absolute_value_square(number):
    return number[0]*number[0] + number[1]*number[1]


def round_to_decimal(number):
    return [round(number[0], 3), round(number[1], 3)]


def function(number, c):
    return round_to_decimal(addition(multiplication(number, number), c))


test_input = [
    [0.1, 0.2, 0.4, 0.35, 0.075],
    [-0.1, -0.1, 0.0, 0.1, 0.05],
    [-2.5, -1.0, -2.0, 0.0, 0.05],
    [-0.1, -0.3, -0.05, -0.2, 0.005],
    [-0.3, -0.3, 0.2, 0.2, 0.1],
    [-1.5, -1.5, 1.5, 1.5, 0.2],
    [-0.4, -0.3, 0.4, 0.3, 0.06],
    [0.175, -0.1, 0.235, 0.4, 0.006],
    [-0.2, -0.2, 0.0, 0.5, 0.02],
    [-0.375, -0.1, 0.075, 0.5, 0.025]
]

test_output = [
    9, 15, 1, 222, 34, 30, 127, 764, 388, 456
]

for i in range(10):
    test_result = num_fibonacci_cycles(
        test_input[i][0], test_input[i][1], test_input[i][2], test_input[i][3], test_input[i][4]
    )

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
