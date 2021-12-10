with open("y21/r09.txt") as f:
    input = [list(x.strip()) for x in f.readlines()]

# star 1
res = 0
diff = [[-1, 0], [1, 0], [0, -1], [0, 1]]
for i in range(len(input)):
    for j in range(len(input[i])):
        flag = True
        x = int(input[i][j])
        for o in diff:
            if 0<=i+o[0] and 0<=j+o[1] and i+o[0]<len(input) and j+o[1]<len(input[i]):
                n = int(input[i+o[0]][j+o[1]])
                if n<=x:
                    flag = False
                    break
        if flag:
            res += x+1
print(res)

# star 2
def check(input, i, j):
    c = 1
    input[i][j] = 'x'
    for o in diff:
            if 0<=i+o[0] and 0<=j+o[1] and i+o[0]<len(input) and j+o[1]<len(input[i]):
                n = input[i+o[0]][j+o[1]]
                if n!='x' and n!='9':
                    c += check(input, i+o[0], j+o[1])
    return c

sizes = []
for i in range(len(input)):
    for j in range(len(input[i])):
        n = input[i][j]
        if n!='x' and n!='9':
            count = check(input, i, j)
            sizes.append(count)
sizes.sort(reverse=True)
print(sizes[0] * sizes[1] * sizes[2])