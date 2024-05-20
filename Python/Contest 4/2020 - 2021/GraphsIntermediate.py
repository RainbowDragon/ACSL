#
# ACSL 2020-2021 - Contest 4 - Graphs - Intermediate Division
#
#

#
# Complete the 'sumPathsOfLength2' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. STRING edges
#
def sum_paths_of_length2(edges):

    graph = [[False for x in range(10)] for y in range(10)]

    edge_list = edges.split(" ")
    for k in range(len(edge_list)):
        number = int(edge_list[k])
        from_node = number // 10
        to_node = number % 10
        graph[from_node][to_node] = True

    result = 0
    for x in range(1, 10):
        for y in range(1, 10):
            for z in range(1, 10):
                if x != y and y != z and z != x and graph[x][y] and graph[y][z]:
                    result += x*100 + y*10 + z

    return result


test_input = [
    "12 23 34 41 31",
    "12 23 34 41 13 32",
    "76 75 12 13 23 31 34 41 56",
    "34 45 56 63 64 61 13",
    "12 21 13 15 53 33",
    "12 31 41 42 43 45 51 63 64 56 16",
    "12 13 22 23 24 34 42 98 71 87 17 96 67",
    "12 14 21 24 25 32 41 43 59 65 91 87 76 95",
    "11 12 14 15 23 25 31 43 45 51 52 68 79 87 89",
    "55 77 45 54"
]

test_output = [
    1653, 1789, 2956, 4515, 581, 8478, 6301, 7880, 7249, 0
]

for i in range(10):
    test_result = sum_paths_of_length2(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
