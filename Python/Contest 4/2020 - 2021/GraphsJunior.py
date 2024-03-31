#
# ACSL 2020-2021 - Contest 4 - Graphs - Junior Division
#
#

#
# Complete the 'findCharacteristic' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER choice
#  2. STRING edges
#
def find_characteristic(choice, edges):

    graph = [[False for x in range(10)] for y in range(10)]

    edge_list = edges.split(" ")
    for k in range(len(edge_list)):
        number = int(edge_list[k])
        from_node = number // 10
        to_node = number % 10
        graph[from_node][to_node] = True

    result = 0
    if choice == 1:
        count1 = 0
        for x in range(1, 10):
            if graph[x][x]:
                count1 += 1

        count2 = 0
        for x in range(1, 10):
            for y in range(1, 10):
                if x != y and graph[x][y] and graph[y][x]:
                    count2 += 1
        count2 //= 2

        result = count1 + count2

    elif choice == 2:
        count = 0
        max_index = 0
        for x in range(1, 10):
            current = 0
            for y in range(1, 10):
                if graph[x][y]:
                    current += 1

            if current > count:
                count = current
                max_index = x

        for z in range(1, 10):
            if graph[max_index][z]:
                result += max_index*10 + z

    elif choice == 3:
        for x in range(1, 10):
            for y in range(1, 10):
                for z in range(1, 10):
                    if graph[x][y] and graph[y][z]:
                        result += 1

    return result


test_input = [
    ["2", "12 13 23 31 34 41"],
    ["1", "12 23 34 11 21 32 45 53 95 43 99 29 91"],
    ["3", "12 23 34 41 31 52 45 61 14 21 33 55 13 54 32 56 36"],
    ["1", "12 11 33 34 43 55 52 41 31 25 88 79 98 45 13 42 87 35 51 21 14 78"],
    ["2", "12 11 33 34 43 55 52 41 31 25 88 79 98 45 13 42 87 35 51 21 14 78"],
    ["1", "12 31 41 42 43 45 51 63 64 56 16"],
    ["2", "12 13 22 23 24 34 42 98 71 87 17 96 67"],
    ["3", "12 14 21 24 25 32 41 43 59 65 91 87 76 95"],
    ["2", "11 12 14 15 23 25 31 43 45 51 52 68 79 87 89"],
    ["3", "55 77 45 54"]
]

test_output = [25, 5, 49, 10, 50, 0, 42, 24, 52, 6]

for i in range(10):
    test_result = find_characteristic(int(test_input[i][0]), test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
