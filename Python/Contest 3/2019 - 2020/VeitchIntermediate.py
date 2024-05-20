#
# ACSL 2019-2020 - Contest 3 - Veitch - Intermediate Division
#
#

def get_veitch_diagram(boolean_expression):

    veitch_diagram = [0] * 16
    grid = [
        "1100", "1110", "0110", "0100", "1101", "1111", "0111", "0101",
        "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"
    ]
    expressions = boolean_expression.replace("+", " ").split(" ")

    for expression in expressions:
        binary_expression = convert_expression(expression)
        for k in range(16):
            if check_match(binary_expression, grid[k]):
                veitch_diagram[k] = 1

    result = ""
    for k in [0, 4, 8, 12]:
        value = 8 * veitch_diagram[k] + 4 * veitch_diagram[k+1] + 2 * veitch_diagram[k+2] + veitch_diagram[k+3]
        if value < 10:
            result += str(value)
        else:
            result += chr(ord('A') + value - 10)

    return result


def convert_expression(expression):

    binary_expression = ""

    for c in "ABCD":
        if ("~" + c) in expression:
            binary_expression += "0"
        elif c in expression:
            binary_expression += "1"
        else:
            binary_expression += "*"

    return binary_expression


def check_match(binary_expression, cell):

    match = True
    for j in range(4):
        if binary_expression[j] != '*' and binary_expression[j] != cell[j]:
            match = False
            break

    return match


test_input = [
    "AB+~AB+~A~B", "AB~C~D+AB~CD+~A~B~CD",
    "AB~C~D+~AB~C~D+A~B~C~D", "B~D+~B~D",
    "~A~BD+~A~B~D", "B~D+~A~BD+A~B~C",
    "~B~C+BCD+B~C~D", "A~C+ACD+~A~CD",
    "AB~D+~ABD+A~BD+~A~B~D", "B~D+~A~CD+~A~B~C~D"
]

test_output = ["FF33", "8810", "9008", "F00F", "0033", "F0B8", "9699", "8DD8", "C3C3", "F111"]

for i in range(10):
    test_result = get_veitch_diagram(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
