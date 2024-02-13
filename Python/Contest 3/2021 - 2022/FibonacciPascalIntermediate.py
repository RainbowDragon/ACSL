#
# ACSL 2021-2022 - Contest 3 - Fibonacci & Pascal - Intermediate Division
#
#

#
# Complete the 'findOddEvenMax' function below.
#
# The function is expected to return a STRING.
# The function accepts INTEGER fibNumber as parameter.
#
def findOddEvenMax(fib):

    n = getFibonacciIndex(fib)
    k = 0
    current = 1
    count_odd = 1
    count_even = 0
    cur_max = 1

    while k + 1 < n:
        current = (current * (n - k) * (n - k - 1)) // (n * (k + 1))
        n -= 1
        k += 1
        if current % 2 == 0:
            count_even += 1
        else:
            count_odd += 1
        cur_max = max(cur_max, current)

    return str(count_odd) + " " + str(count_even) + " " + str(cur_max)


def getFibonacciIndex(fib):

    index = 1
    first = 1
    second = 1

    while second < fib:
        temp = first
        first = second
        second += temp
        index += 1

    return index


test_input = [8, 89, 610, 10946, 317811, 55, 1597, 832040, 9227465, 1836311903]

test_output = [""] * 10
test_output[0] = "2 1 4"
test_output[1] = "5 1 35"
test_output[2] = "4 4 210"
test_output[3] = "8 3 3003"
test_output[4] = "3 11 77520"
test_output[5] = "3 2 21"
test_output[6] = "5 4 495"
test_output[7] = "4 11 203490"
test_output[8] = "9 9 2042975"
test_output[9] = "7 16 354817320"

for i in range(10):
    test_result = findOddEvenMax(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
