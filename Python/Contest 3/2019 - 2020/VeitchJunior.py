#
# ACSL 2019-2020 - Contest 3 - Veitch - Junior Division
#
#

def getBooleanExpression(veitchDiagram):

    value = getIntegerFromVeitchDiagram(veitchDiagram)

    sb = []

    value = checkMatch(value, 240, "B", sb)
    value = checkMatch(value, 15, "~B", sb)
    value = checkMatch(value, 204, "A", sb)
    value = checkMatch(value, 102, "C", sb)
    value = checkMatch(value, 51, "~A", sb)
    value = checkMatch(value, 153, "~C", sb)

    value = checkMatch(value, 192, "AB", sb)
    value = checkMatch(value, 96, "BC", sb)
    value = checkMatch(value, 48, "~AB", sb)
    value = checkMatch(value, 12, "A~B", sb)
    value = checkMatch(value, 6, "~BC", sb)
    value = checkMatch(value, 3, "~A~B", sb)
    value = checkMatch(value, 136, "A~C", sb)
    value = checkMatch(value, 68, "AC", sb)
    value = checkMatch(value, 34, "~AC", sb)
    value = checkMatch(value, 17, "~A~C", sb)
    value = checkMatch(value, 144, "B~C", sb)
    value = checkMatch(value, 9, "~B~C", sb)

    value = checkMatch(value, 128, "AB~C", sb)
    value = checkMatch(value, 64, "ABC", sb)
    value = checkMatch(value, 32, "~ABC", sb)
    value = checkMatch(value, 16, "~AB~C", sb)
    value = checkMatch(value, 8, "A~B~C", sb)
    value = checkMatch(value, 4, "A~BC", sb)
    value = checkMatch(value, 2, "~A~BC", sb)
    checkMatch(value, 1, "~A~B~C", sb)

    return "".join(sb)


def checkMatch(value, mask, expression, sb):

    if (value & mask) == mask:
        if len(sb) > 0:
            sb.append(" + ")
        sb.append(expression)
        value = value & (~mask)

    return value


def getIntegerFromVeitchDiagram(veitchDiagram):

    front = int(veitchDiagram[0], 16)
    back = int(veitchDiagram[1], 16)

    return (front << 4) + back


test_input = ["33", "3C", "94", "77", "95", "F0", "1D", "9D", "E9", "E7"]

test_output = ["~A", "~AB + A~B", "B~C + A~BC", "C + ~A~C", "~A~C + AB~C + A~BC", "B", "A~B + ~A~C", "~C + A~BC", "AB + ~B~C + ~ABC", "C + AB~C + ~A~B~C"]

for i in range(10):
    test_result = getBooleanExpression(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
