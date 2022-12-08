with open('y22/r08.txt') as f:
    inp = [[int(c) for c in l.strip()] for l in f.readlines()]

# star 1
count = 0
dirs = [[1,0],[-1,0],[0,1],[0,-1]] # down up right left
length = len(inp)
for i in range(length):
    for j in range(length):
        flag = False
        for dir in dirs:
            a = i
            b = j
            while 0<=a and 0<=b and a<length or b<length:
                if (a==0 or a==length-1) and dir[0]!=0 or (b==0 or b==length-1) and dir[1]!=0:
                    flag = True
                    break
                if inp[i][j] <= inp[a+dir[0]][b+dir[1]]:
                    break
                a += dir[0]
                b += dir[1]
            if flag:
                count += 1
                break
print(count)

# star 2
mx = 0
for i in range(length):
    for j in range(length):
        score = 1
        for dir in dirs:
            count = 0
            a = i + dir[0]
            b = j + dir[1]
            while (dir[0]==-1 and 0<=a) or (dir[0]==1 and a<length) or (dir[1]==-1 and 0<=b) or (dir[1]==1 and b<length):
                count += 1
                if inp[i][j] <= inp[a][b]: # reached tree
                    break
                a += dir[0]
                b += dir[1]
            score *= count  
        mx = max(mx, score)
print(mx)