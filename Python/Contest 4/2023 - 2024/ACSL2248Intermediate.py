#
# ACSL 2023-2024 - Contest 4 - ACSL 2248 - Intermediate Division
#
#

#
# Complete the 'play2248' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING boardValues as parameter.
#
def play_2248(board_values):

    board = [[0 for x in range(col_count)] for y in range(row_count)]
    board_value_list = board_values.split(" ")
    max_value = 0
    for x in range(row_count):
        for y in range(col_count):
            board[x][y] = int(board_value_list[x*col_count + y])
            max_value = max(max_value, board[x][y])

    total = 0
    row = 0
    col = 0
    found = False
    last_value = 0
    for x in range(row_count):
        for y in range(col_count):
            for k in range(1, 5):
                row = x + dr[k]
                col = y + dc[k]
                if 0 <= row < row_count and 0 <= col < col_count and board[x][y] == board[row][col]:
                    found = True
                    last_value = board[x][y]
                    total += last_value * 2
                    board[x][y] = board[row][col] = 0
                    break
            if found:
                break
        if found:
            break

    is_done = False
    while not is_done:
        is_done = True
        for k in range(5):
            next_row = row + dr[k]
            next_col = col + dc[k]
            if 0 <= next_row < row_count and 0 <= next_col < col_count:
                if board[next_row][next_col] == last_value or board[next_row][next_col] == (last_value * 2):
                    is_done = False
                    last_value = board[next_row][next_col]
                    total += last_value
                    row = next_row
                    col = next_col
                    board[next_row][next_col] = 0
                    break

    board[row][col] = get_last_value(total)

    max_value = max(max_value, board[row][col])
    min_value = max_value // 128
    replenish_board(board, min_value)

    shift_board(board)

    fill_board(board, max_value, min_value)

    result = []
    for x in range(row_count):
        for y in range(col_count):
            if x > 0 or y > 0:
                result.append(" ")
            result.append(str(board[x][y]))

    return "".join(result)


row_count = 8
col_count = 5
dr = [0, 0, 1, 1, 1]
dc = [-1, 1, -1, 0, 1]


def get_last_value(total):

    last_value = 2
    while last_value < total:
        last_value *= 2

    return last_value


def replenish_board(board, min_value):

    for x in range(row_count):
        for y in range(col_count):
            if board[x][y] < min_value:
                board[x][y] = 0


def shift_board(board):

    for y in range(col_count):

        temp = [0] * 8
        index = row_count - 1
        for x in range(row_count-1, -1, -1):
            if board[x][y] > 0:
                temp[index] = board[x][y]
                index -= 1

        for x in range(row_count):
            board[x][y] = temp[x]


def fill_board(board, max_value, min_value):

    value = max_value
    for x in range(row_count):
        for y in range(col_count):
            if board[x][y] == 0:
                board[x][y] = value
                if value > min_value:
                    value //= 2
                else:
                    value = max_value


test_input = [
    "8 128 4 128 32 16 16 4 256 16 32 4 16 64 4 8 64 64 256 8 16 2 2 256 4 32 128 2 64 8 256 32 128 16 2 8 64 64 128 32",
    "256 128 64 128 32 32 16 8 256 16 4 2 16 64 4 4 128 32 256 8 16 16 64 256 4 32 64 2 64 8 256 2 128 16 2 8 128 256 4 32",
    "256 16 256 2 32 2 32 2 16 8 32 2 256 64 16 4 2 128 2 32 8 8 32 256 2 2 4 8 32 128 2 16 32 64 256 4 2 128 4 8",
    "8 8 16 64 64 256 2 128 16 4 4 64 4 64 16 256 16 64 64 32 32 64 64 256 128 8 128 64 2 16 4 16 256 4 8 64 256 32 16 64",
    "4 16 8 2 32 2 2 8 32 4 2 16 16 4 128 128 32 4 2 128 128 64 8 128 128 4 2 16 32 16 8 8 128 64 32 32 8 128 2 128",
    "2 2 32 16 2 2 8 256 2 128 4 16 64 16 32 8 8 4 16 4 8 8 32 64 2 64 8 64 8 256 128 256 16 8 32 16 32 64 128 2",
    "2 4 256 128 4 256 32 8 64 64 64 128 16 256 64 64 4 4 64 64 64 4 8 64 2 32 4 2 128 256 16 64 64 2 8 256 32 32 32 2",
    "256 8 4 16 128 64 4 32 256 256 8 32 8 4 2 64 8 8 2 8 64 16 64 16 128 4 4 4 32 64 64 2 8 8 32 128 128 128 64 4",
    "256 16 256 2 32 2 32 2 16 8 32 2 256 64 16 4 2 128 2 32 8 8 32 256 2 2 4 8 32 128 16 16 32 64 256 4 16 128 4 8",
    "2 64 32 32 8 32 32 128 256 32 64 128 64 32 32 64 128 16 8 64 8 4 256 64 64 64 256 64 64 4 8 128 64 32 256 256 256 128 64 8"
]

test_output = [
    "512 256 128 128 64 32 16 8 256 32 4 512 256 64 16 8 128 64 256 4 16 128 32 256 8 32 16 16 64 4 256 64 64 16 8 8 128 128 512 32",
    "1024 512 256 128 64 32 16 8 128 1024 256 512 256 256 128 32 64 32 64 32 16 16 8 256 16 32 128 64 256 8 256 128 8 64 8 8 16 1024 16 32",
    "512 256 128 64 32 16 8 4 512 32 256 128 64 32 8 16 8 256 16 16 4 512 256 64 32 256 16 128 256 128 32 32 32 32 256 4 4 512 4 8",
    "1024 512 256 128 64 32 16 8 1024 512 256 128 64 32 16 256 8 1024 512 64 256 64 256 64 128 32 16 128 64 16 8 16 64 256 8 64 1024 32 16 64",
    "512 256 128 64 32 32 16 8 4 4 4 512 256 128 128 128 64 4 32 128 512 32 8 4 128 4 16 16 128 16 8 8 128 32 32 32 8 128 64 128",
    "512 256 128 64 32 16 8 4 16 512 256 128 32 16 64 32 16 256 16 128 8 4 64 64 32 64 8 4 8 4 128 16 32 8 256 16 256 64 512 32",
    "1024 512 256 128 64 256 32 16 8 1024 64 512 256 256 128 64 64 8 32 16 64 32 16 8 1024 32 128 8 128 512 16 64 64 256 1024 256 32 32 32 8",
    "256 128 64 16 128 256 32 16 256 256 64 8 32 4 2 64 32 8 2 8 64 64 64 16 128 4 4 4 32 64 64 2 8 8 32 128 128 128 64 4",
    "256 128 64 32 32 16 8 4 16 8 2 256 256 64 16 256 16 256 2 32 2 32 128 256 2 32 2 32 32 128 2 4 32 64 256 4 128 128 4 8",
    "1024 512 256 128 64 32 32 16 8 1024 64 512 256 256 8 64 64 128 8 32 8 32 16 64 64 64 1024 64 64 64 8 128 64 32 256 256 256 128 64 8"
]

for i in range(10):
    test_result = play_2248(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
