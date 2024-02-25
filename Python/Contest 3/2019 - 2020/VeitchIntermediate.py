#
# ACSL 2019-2020 - Contest 3 - Veitch - Intermediate Division
#
#

def getVeitchDiagram(booleanExpression):

    veitch_diagram = [0] * 16
    grid = [
        "1100", "1110", "0110", "0100", "1101", "1111", "0111", "0101",
        "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"]
    expressions = booleanExpression.replace("+", " ").split(" ")

    for expression in expressions:
        binary_expression = convertExpression(expression)
        for k in range(16):
            if checkMatch(binary_expression, grid[k]):
                veitch_diagram[k] = 1

    result = ""
    for k in [0, 4, 8, 12]:
        value = 8 * veitch_diagram[k] + 4 * veitch_diagram[k+1] + 2 * veitch_diagram[k+2] + veitch_diagram[k+3]
        if value < 10:
            result += str(value)
        else:
            result += chr(ord('A') + value - 10)

    return result


def convertExpression(expression):

    binary_expression = ""

    for c in "ABCD":
        if ("~" + c) in expression:
            binary_expression += "0"
        elif c in expression:
            binary_expression += "1"
        else:
            binary_expression += "*"

    return binary_expression


def checkMatch(binary_expression, cell):

    match = True
    for j in range(4):
        if binary_expression[j] != '*' and binary_expression[j] != cell[j]:
            match = False
            break

    return match


test_input = [""] * 10
test_input[0] = "AB+~AB+~A~B"
test_input[1] = "AB~C~D+AB~CD+~A~B~CD"
test_input[2] = "AB~C~D+~AB~C~D+A~B~C~D"
test_input[3] = "B~D+~B~D"
test_input[4] = "~A~BD+~A~B~D"
test_input[5] = "B~D+~A~BD+A~B~C"
test_input[6] = "~B~C+BCD+B~C~D"
test_input[7] = "A~C+ACD+~A~CD"
test_input[8] = "AB~D+~ABD+A~BD+~A~B~D"
test_input[9] = "B~D+~A~CD+~A~B~C~D"

test_output = ["FF33", "8810", "9008", "F00F", "0033", "F0B8", "9699", "8DD8", "C3C3", "F111"]

for i in range(10):
    test_result = getVeitchDiagram(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
