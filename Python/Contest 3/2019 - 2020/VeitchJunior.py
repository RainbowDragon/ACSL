#
# ACSL 2019-2020 - Contest 3 - Veitch - Junior Division
#
#

def get_boolean_expression(veitch_diagram):

    masks_in_order = [
        240, 15, 204, 102, 51, 153,
        192, 96, 48, 12, 6, 3,
        136, 68, 34, 17, 144, 9,
        128, 64, 32, 16,
        8, 4, 2, 1
    ]

    boolean_expressions_in_order = [
        "B", "~B", "A", "C", "~A", "~C",
        "AB", "BC", "~AB", "A~B", "~BC", "~A~B",
        "A~C", "AC", "~AC", "~A~C", "B~C", "~B~C",
        "AB~C", "ABC", "~ABC", "~AB~C",
        "A~B~C", "A~BC", "~A~BC", "~A~B~C"
    ]

    number = int(veitch_diagram, 16)
    sb = []

    for k in range(len(boolean_expressions_in_order)):
        number = check_match(number, masks_in_order[k], boolean_expressions_in_order[k], sb)

    return "".join(sb)


def check_match(number, mask, expression, sb):

    if (number & mask) == mask:
        if len(sb) > 0:
            sb.append("+")
        sb.append(expression)
        number -= mask

    return number


test_input = [
    "33", "3C", "94", "77", "95", "F0", "1D", "9D", "E9", "E7"
]

test_output = [
    "~A", "~AB+A~B", "B~C+A~BC", "C+~A~C", "~A~C+AB~C+A~BC",
    "B", "A~B+~A~C", "~C+A~BC", "AB+~B~C+~ABC", "C+AB~C+~A~B~C"
]

for i in range(10):
    test_result = get_boolean_expression(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
