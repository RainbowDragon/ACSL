#
# ACSL 2019-2020 - Contest 3 - Veitch - Senior Division
#
#

def get_boolean_expression(veitch_diagram):

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
        "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"
    ]
    grid = [
        "1100", "1110", "0110", "0100", "1101", "1111", "0111", "0101",
        "1001", "1011", "0011", "0001", "1000", "1010", "0010", "0000"
    ]

    number = int(veitch_diagram, 16)
    sb = []
    for binary_expression in binary_expressions_in_order:
        mask = get_integer_from_binary_expression(binary_expression, grid)
        if (number & mask) == mask:
            if len(sb) > 0:
                sb.append("+")
            sb.append(get_boolean_expression_from_binary_expression(binary_expression))
            number -= mask

    return "".join(sb)


def get_boolean_expression_from_binary_expression(binary_expression):

    result = ""
    inputs = "ABCD"
    for k in range(4):
        if binary_expression[k] == "1":
            result += inputs[k]
        elif binary_expression[k] == "0":
            result += "~" + inputs[k]

    return result


def get_integer_from_binary_expression(binary_expression, grid):

    result = 0
    for cell in grid:
        if check_match(binary_expression, cell):
            result += 1
        result <<= 1

    return result >> 1


def check_match(binary_expression, cell):

    match = True
    for j in range(4):
        if binary_expression[j] != '*' and binary_expression[j] != cell[j]:
            match = False
            break

    return match


test_input = ["FF33", "00CC", "6090", "8810", "9008", "F0B8", "9699", "8DD8", "C3C3", "F111"]

test_output = [
    "B+~A~B", "A~B",
    "BC~D+~B~CD", "AB~C+~A~B~CD",
    "B~C~D+A~B~C~D", "B~D+~A~BD+A~B~C",
    "~B~C+BCD+B~C~D", "A~C+ACD+~A~CD",
    "AB~D+~ABD+A~BD+~A~B~D", "B~D+~A~CD+~A~B~C~D"
]

for i in range(10):
    test_result = get_boolean_expression(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
