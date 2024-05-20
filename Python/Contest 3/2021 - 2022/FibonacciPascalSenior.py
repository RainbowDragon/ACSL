#
# ACSL 2021-2022 - Contest 3 - Fibonacci & Pascal - Senior Division
#
#

#
# Complete the 'countUniqueValues' function below.
#
# The function is expected to return an INTEGER.
# The function accepts INTEGER fibNumber as parameter.
#
def count_unique_values(fib):

    n = get_fibonacci_index(fib)
    count_map = {}

    for k in range(1, n+1):
        j = 0
        m = k
        current = 1
        if current in count_map:
            count_map.update({current: count_map.get(current)+1})
        else:
            count_map.update({current: 1})

        while j + 1 < m:
            current = (current * (m - j) * (m - j - 1)) // (m * (j + 1))
            m -= 1
            j += 1
            if current in count_map:
                count_map.update({current: count_map.get(current)+1})
            else:
                count_map.update({current: 1})

    result = 0
    for key in count_map:
        if count_map.get(key) == 1:
            result += 1

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


test_input = [
    8, 89, 610, 10946, 317811, 55, 1597, 832040, 9227465, 1836311903
]

test_output = [
    2, 8, 16, 31, 58, 6, 21, 67, 96, 171
]

for i in range(10):
    test_result = count_unique_values(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
