#
# ACSL 2019-2020 - Contest 3 - Veitch - Senior Division
#
#

def getBooleanExpression(veitchDiagram):

    binary_expressions_in_order = [
        "*1**", "***1", "*0**", "1***", "**1*", "0***", "**0*", "***0",
        "*1*0", "*1*1", "*0*1", "*0*0", "1*0*", "1*1*", "0*1*", "0*0*",
        "11**", "*11*", "01**", "1**1", "**11", "0**1", "10**", "*01*",
        "00**", "*10*", "**01", "*00*", "1**0", "**10", "0**0", "**00",
        "11*0", "*110", "01*0", "11*1", "*111", "01*1", "10*1", "*011",
        "00*1", "10*0", "*010", "00*0", "110*", "1*01", "100*", "111*",
        "1*11", "101*", "011*", "0*11", "001*", "010*", "0*01", "000*",
        "*100", "*101", "*001", "*000", "1*00", "1*10", "0*10", "0*00",
        "1100", "1110", "0110", "0100", "1101", "1111", "0111", "0101",
        "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"]
    grid = [
        "1100", "1110", "0110", "0100", "1101", "1111", "0111", "0101",
        "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"]

    number = int(veitchDiagram, 16)
    sb = []
    for binary_expression in binary_expressions_in_order:
        mask = getIntegerFromBinaryExpression(binary_expression, grid)
        if (number & mask) == mask:
            if len(sb) > 0:
                sb.append("+")
            sb.append(getBooleanExpressionFromBinaryExpression(binary_expression))
            number -= mask

    return "".join(sb)


def getBooleanExpressionFromBinaryExpression(binary_expression):

    result = ""
    inputs = "ABCD"
    for k in range(4):
        if binary_expression[k] == "1":
            result += inputs[k]
        elif binary_expression[k] == "0":
            result += "~" + inputs[k]

    return result


def getIntegerFromBinaryExpression(binary_expression, grid):

    result = 0
    for cell in grid:
        if checkMatch(binary_expression, cell):
            result += 1
        result <<= 1

    return result >> 1


def checkMatch(binary_expression, cell):

    match = True
    for j in range(4):
        if binary_expression[j] != '*' and binary_expression[j] != cell[j]:
            match = False
            break

    return match


test_input = ["FF33", "00CC", "6090", "8810", "9008", "F0B8", "9699", "8DD8", "C3C3", "F111"]

test_output = [""] * 10
test_output[0] = "B+~A~B"
test_output[1] = "A~B"
test_output[2] = "BC~D+~B~CD"
test_output[3] = "AB~C+~A~B~CD"
test_output[4] = "B~C~D+A~B~C~D"
test_output[5] = "B~D+~A~BD+A~B~C"
test_output[6] = "~B~C+BCD+B~C~D"
test_output[7] = "A~C+ACD+~A~CD"
test_output[8] = "AB~D+~ABD+A~BD+~A~B~D"
test_output[9] = "B~D+~A~CD+~A~B~C~D"

for i in range(10):
    test_result = getBooleanExpression(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
