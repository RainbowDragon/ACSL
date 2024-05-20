#
# ACSL 2021-2022 - Contest 3 - Fibonacci & Pascal - Junior Division
#
#

#
# Complete the 'printNumbers' function below.
#
# The function is expected to return a STRING.
# The function accepts INTEGER fibNumber as parameter.
#
def print_numbers(fib):

    n = get_fibonacci_index(fib)
    k = 0
    current = 1
    result = "1"

    while k + 1 < n:
        current = (current * (n - k) * (n - k - 1)) // (n * (k + 1))
        n -= 1
        k += 1
        result += " " + str(current)

    return result


def get_fibonacci_index(fib):

    index = 1
    first = 1
    second = 1

    while second < fib:
        temp = first
        first = second
        second += temp
        index += 1

    return index


test_input = [8, 89, 610, 10946, 317811, 2, 55, 1597, 17711, 832040]

test_output = [
    "1 4 3",
    "1 9 28 35 15 1",
    "1 13 66 165 210 126 28 1",
    "1 19 153 680 1820 3003 3003 1716 495 55 1",
    "1 26 300 2024 8855 26334 54264 77520 75582 48620 19448 4368 455 14",
    "1 1",
    "1 8 21 20 5",
    "1 15 91 286 495 462 210 36 1",
    "1 20 171 816 2380 4368 5005 3432 1287 220 11",
    "1 28 351 2600 12650 42504 100947 170544 203490 167960 92378 31824 6188 560 15"
]

for i in range(10):
    test_result = print_numbers(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
