import copy

with open("y21/r11.txt") as f:
    input = [[int(x) for x in list(l.strip())] for l in f.readlines()]

# star 1
octo = copy.deepcopy(input)
diff = [-1, 0, 1]
count = 0
for f in range(100):
    for i in range(len(octo)):
        for j in range(len(octo[i])):
            octo[i][j] += 1
    flag = True
    while flag:
        flag = False
        for i in range(len(octo)):
            for j in range(len(octo[i])):
                if 9<octo[i][j]:
                    octo[i][j] = -999
                    for x in diff:
                        for y in diff:
                            if 0<=i+x and i+x<len(octo) and 0<=j+y and j+y<len(octo[i]):
                                octo[i+x][j+y] += 1
                                if 9<octo[i+x][j+y]:
                                    flag = True
    for i in range(len(octo)):
        for j in range(len(octo[i])):
            if octo[i][j]<0:
                octo[i][j] = 0
                count += 1
print(count)
    
# star 2
octo = copy.deepcopy(input)
diff = [-1, 0, 1]
step = 0
sync = False
while not sync:
    sync = True
    for i in range(len(octo)):
        for j in range(len(octo[i])):
            if octo[i][j]!=0:
                sync = False
            octo[i][j] += 1
    if sync:
        break
    flag = True
    while flag:
        flag = False
        for i in range(len(octo)):
            for j in range(len(octo[i])):
                if 9<octo[i][j]:
                    octo[i][j] = -999
                    for x in diff:
                        for y in diff:
                            if 0<=i+x and i+x<len(octo) and 0<=j+y and j+y<len(octo[i]):
                                octo[i+x][j+y] += 1
                                if 9<octo[i+x][j+y]:
                                    flag = True
    for i in range(len(octo)):
        for j in range(len(octo[i])):
            if octo[i][j]<0:
                octo[i][j] = 0
                count += 1
    step += 1
print(step)