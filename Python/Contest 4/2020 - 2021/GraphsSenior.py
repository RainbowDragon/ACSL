#
# ACSL 2020-2021 - Contest 4 - Graphs - Senior Division
#
#

#
# Complete the 'sumPathsOfLengthN' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER num
#  2. STRING edges
#
def sum_paths_of_length_n(num, edges):

    graph = [[False for x in range(10)] for y in range(10)]

    edge_list = edges.split(" ")
    for k in range(len(edge_list)):
        number = int(edge_list[k])
        from_node = number // 10
        to_node = number % 10
        graph[from_node][to_node] = True

    result = 0
    visited = [False for x in range(10)]
    for k in range(1, 10):
        visited[k] = True
        result += dfs(graph, visited, k, 0, num, k)
        visited[k] = False

    return result


def dfs(graph, visited, node, length, max_length, path):

    if length == max_length:
        return path

    result = 0
    for j in range(1, 10):
        if graph[node][j] and (not visited[j]):
            visited[j] = True
            result += dfs(graph, visited, j, length+1, max_length, path*10+j)
            visited[j] = False

    return result


test_input = [
    ["2", "12 23 34 41 31"],
    ["3", "12 23 34 41 13 32"],
    ["4", "67 75 54 12 13 23 31 34 41 56 45"],
    ["3", "34 45 56 63 64 61 13"],
    ["2", "12 21 13 15 53 33"],
    ["2", "12 31 41 42 43 45 51 63 64 56 16"],
    ["3", "12 13 22 23 24 34 42 98 71 87 17 96 67"],
    ["4", "12 14 21 24 25 32 41 43 59 65 91 87 76 95"],
    ["3", "11 12 14 15 23 25 31 43 45 51 52 68 79 87 89"],
    ["2", "55 77 45 54"]
]

test_output = [
    1653, 15242, 356313, 37651, 581, 8478, 74349, 754366, 59578, 0
]

for i in range(10):
    test_result = sum_paths_of_length_n(int(test_input[i][0]), test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
