#
# ACSL 2023-2024 - Contest 4 - ACSL 2248 - Senior Division
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
    for x in range(row_count):
        for y in range(col_count):
            board[x][y] = int(board_value_list[x*col_count + y])

    for k in range(3):
        play_one_round(board)

    result = []
    for x in range(row_count):
        for y in range(col_count):
            if x > 0 or y > 0:
                result.append(" ")
            result.append(str(board[x][y]))

    return "".join(result)


row_count = 8
col_count = 5
max_len = 0
dr = [0, -1, 0, 1, -1, -1, 1, 1]
dc = [-1, 0, 1, 0, -1, 1, 1, -1]


def play_one_round(board):

    global max_len
    max_len = 0
    all_path = []

    for x in range(row_count):
        for y in range(col_count):
            find_all_path(board, all_path, x, y, "", True, -1)

    if len(all_path) > 1:
        all_path.sort()

    path_list = all_path[0].split(" ")

    total = 0
    row = 0
    col = 0
    for k in range(len(path_list)):
        location = int(path_list[k])
        row = location // 10
        col = location % 10
        total += board[row][col]
        board[row][col] = 0

    board[row][col] = get_last_value(total)

    max_value = get_max_value(board)
    min_value = max_value // 128
    replenish_board(board, min_value)

    shift_board(board)

    fill_board(board, max_value, min_value)


def find_all_path(board, all_path, row, col, path, is_first, last_value):

    global max_len
    found = False
    for k in range(8):
        next_row = row + dr[k]
        next_col = col + dc[k]
        if 0 <= next_row < row_count and 0 <= next_col < col_count:
            if is_first:
                if board[next_row][next_col] == board[row][col]:
                    value = board[row][col]
                    board[row][col] = board[next_row][next_col] = 0
                    find_all_path(board, all_path, next_row, next_col, str(row)+str(col)+" "+str(next_row)+str(next_col), False, value)
                    board[row][col] = board[next_row][next_col] = value
            else:
                if board[next_row][next_col] == last_value or board[next_row][next_col] == (last_value * 2):
                    found = True
                    value = board[next_row][next_col]
                    board[next_row][next_col] = 0
                    find_all_path(board, all_path, next_row, next_col, path+" "+str(next_row)+str(next_col), False, value)
                    board[next_row][next_col] = value

    if (not is_first) and (not found):
        if len(path) > max_len:
            all_path.clear()
            all_path.append(path)
            max_len = len(path)
        elif len(path) == max_len:
            all_path.append(path)


def get_last_value(total):

    last_value = 2
    while last_value < total:
        last_value *= 2

    return last_value


def get_max_value(board):

    max_value = 0
    for x in range(row_count):
        for y in range(col_count):
            max_value = max(max_value, board[x][y])

    return max_value


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
    "4 128 4 128 32 16 16 4 256 16 32 4 16 64 4 8 64 64 256 8 16 2 2 256 4 32 128 2 64 8 256 32 128 16 2 8 32 32 4 32",
    "256 128 64 128 32 32 16 8 256 16 4 2 16 64 4 4 128 64 256 8 16 16 2 256 4 32 64 2 64 8 256 2 128 16 2 8 128 256 4 32",
    "256 16 256 2 32 2 16 2 16 8 32 2 256 64 16 4 2 128 2 32 8 8 32 256 2 2 4 8 32 128 16 16 32 64 256 4 16 128 4 8",
    "256 8 4 16 128 64 4 32 256 256 8 32 8 4 2 64 128 8 2 8 64 16 64 16 128 4 4 4 4 64 64 2 8 8 32 128 128 128 64 4",
    "4 16 8 2 32 2 2 8 32 4 2 16 16 4 128 128 8 4 2 128 128 64 8 128 128 4 2 16 32 16 8 8 128 16 32 32 8 128 2 128",
    "2 2 32 16 2 2 8 256 2 128 32 16 64 16 32 8 8 4 16 4 8 8 32 64 2 64 8 64 8 256 128 256 16 8 32 16 2 2 128 2",
    "2 4 256 128 4 256 32 8 64 64 64 128 16 256 64 64 4 4 64 64 64 4 8 64 2 32 4 2 128 256 16 64 64 2 8 256 32 32 32 2",
    "32 4 128 128 4 2 128 16 2 128 8 256 256 16 256 256 128 8 256 2 64 64 256 64 128 64 16 8 8 64 256 256 16 2 8 2 64 256 32 256",
    "2 2 32 32 8 2 32 128 256 32 64 128 64 32 32 64 128 16 8 64 8 4 256 64 64 64 256 64 64 4 8 128 64 32 256 256 256 128 64 8",
    "256 128 64 32 16 64 4 64 64 64 16 64 32 4 64 256 128 8 16 16 16 64 32 8 64 8 2 2 128 128 128 4 64 256 8 64 256 8 128 256"
]

test_output = [
    "4096 2048 1024 512 256 4096 128 64 32 4096 32 2048 1024 512 256 512 128 64 32 4096 32 2048 1024 256 512 256 256 256 256 64 32 128 64 256 1024 1024 128 256 64 32",
    "4096 4096 2048 1024 256 4096 512 256 128 4096 32 64 32 4096 256 512 2048 1024 2048 4096 32 128 64 512 512 4096 2048 1024 32 64 32 128 64 512 1024 1024 2048 1024 32 32",
    "8192 4096 2048 1024 512 256 128 64 8192 4096 2048 1024 512 256 128 1024 2048 1024 512 64 256 128 64 128 256 2048 8192 256 1024 64 64 512 64 1024 512 256 128 128 1024 256",
    "2048 1024 512 256 128 64 32 16 2048 1024 512 512 256 128 64 32 256 32 16 2048 256 16 1024 512 256 64 2048 128 64 128 64 512 128 16 64 128 128 128 64 32",
    "1024 512 256 128 64 64 32 16 128 512 1024 8 1024 1024 32 32 512 256 64 1024 256 16 8 8 32 128 256 128 64 32 128 128 128 1024 256 32 64 16 32 128",
    "1024 1024 512 256 64 32 512 128 64 1024 512 16 256 32 128 1024 256 8 16 32 128 16 256 128 512 64 8 32 8 32 128 8 64 8 256 16 256 16 128 32",
    "4096 2048 1024 512 256 256 128 64 32 2048 4096 2048 1024 512 256 4096 2048 1024 512 64 128 64 32 4096 1024 1024 512 1024 512 128 256 32 128 64 4096 256 2048 32 128 1024",
    "32768 16384 8192 4096 2048 1024 512 256 32768 16384 8192 4096 2048 1024 512 256 32768 16384 8192 512 32768 4096 2048 1024 8192 512 256 4096 2048 1024 2048 256 256 2048 1024 256 256 256 8192 256",
    "16384 8192 4096 2048 1024 512 256 128 16384 8192 4096 2048 1024 512 256 128 16384 8192 4096 1024 2048 1024 512 256 256 1024 256 128 2048 2048 16384 2048 1024 512 1024 512 2048 2048 256 256",
    "8192 4096 2048 1024 512 256 128 64 8192 4096 2048 1024 512 256 128 128 64 8192 256 2048 2048 4096 2048 128 256 64 1024 1024 128 64 8192 512 512 256 64 256 256 512 128 2048"
]

for i in range(10):
    test_result = play_2248(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
