def parse(s):
    stack = []
    for i in range(len(s)):
        if s[i] == '[':
            if len(stack) == 0:
                stack.append([])
            else:
                l = []
                stack[-1].append(l)
                stack.append(l)
        elif s[i] == ']':
            if i+1 == len(s):
                return stack[0]
            else:
                stack.pop()
        elif ord('0') <= ord(s[i]) and ord(s[i]) <= ord('9'):
            comma = s.index(',', i) if ',' in s[i:] else 100000
            bracket = s.index(']', i) if ']' in s[i:] else 100000
            n = int(s[i : min(comma, bracket)])
            stack[-1].append(n)
        
    return stack[0]

def compare(a1, a2):
    if type(a1) is int and type(a2) is int:
        if a1 == a2:
            return None
        else:
            return a1 < a2
    elif type(a1) is list and type(a2) is int:
        return compare(a1, [a2])
    elif type(a1) is int and type(a2) is list:
        return compare([a1], a2)
    else:
        l1 = len(a1)
        l2 = len(a2)
        for i in range(min(l1, l2)):
            res = compare(a1[i], a2[i])
            if res != None:
                return res
        if l1 == l2:
            return None
        else:
            return l1 < l2

with open("y22/r13.txt") as f:
    input = []
    line = f.readline().strip()
    while line:
        input.append(parse(line))
        input.append(parse(f.readline().strip()))
        f.readline()
        line = f.readline().strip()

# star 1
sum = 0
for p in range(len(input) // 2):
    sum += (p+1) if compare(input[p*2], input[p*2+1]) else 0
print(sum)

# star 2
input.append([[2]])
input.append([[6]])

flag = True
while flag:
    flag = False
    for i in range(len(input)-1):
        res = compare(input[i], input[i+1])
        if not res:
            var = input[i]
            input[i] = input[i+1]
            input[i+1] = var
            flag = True

print((input.index([[2]]) + 1) * (input.index([[6]]) + 1))