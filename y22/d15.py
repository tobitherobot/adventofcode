with open("y22/r15.txt") as f:
    input = []
    line = f.readline().strip()
    while line:
        sx = int(line[12 : line.index(',')])
        sy = int(line[line.index(',')+4 : line.index(':')])
        sub = line[line.index('is') :]
        bx = int(sub[8 : sub.index(',')])
        by = int(sub[sub.index(',')+4 :])
        input.append([[sy, sx],[by, bx]])
        line = f.readline().strip()

def get_distance(p1, p2):
    return abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])

minx = 100000000
miny = 100000000
maxx = 0
maxy = 0

for pair in input:
    for p in pair:
        minx = min(minx, p[1])
        maxx = max(maxx, p[1])
        miny = min(miny, p[0])
        maxy = max(maxy, p[0])

# star 1
y = 2000000
res = []
for pair in input:
    d = get_distance(pair[0], pair[1])
    if pair[0][0] <= y and pair[0][0]+d >= y or pair[0][0] >= y and pair[0][0]-d <= y:
        diff = abs(pair[0][0] - y)
        imp = [pair[0][1] - (d-diff), pair[0][1] + (d-diff)]
        flag = True
        for r in res:
            if imp[0] <= r[0] and r[1] <= imp[1]:
                r[0] = imp[0]
                r[1] = imp[1]
                flag = False
                break
            elif r[0] <= imp[0] and imp[1] <= r[1]:
                flag = False
                break
            elif imp[0] <= r[0] and imp[1] <= r[1]:
                r[0] = imp[0]
                flag = False
                break
            elif r[0] <= imp[0] and r[1] <= imp[1]:
                r[1] = imp[1]
                flag = False
        if flag:
            res.append(imp)
sum = 0
for r in res:
    sum += (r[1] - r[0])
print(sum)

print(input)

# star 2
for i in range(len(input)-1):
    for j in range(i+1, len(input)):
        radius = get_distance(input[i][0], input[i][1]) + get_distance(input[j][0], input[j][1])
        distance = get_distance(input[i][0], input[j][0])
        if distance < radius:
            mid = [min(input[i][0][0], input[j][0][0]) + (max(input[i][0][0], input[j][0][0]) - min(input[i][0][0], input[j][0][0])) // 2, min(input[i][0][1], input[j][0][1]) + (max(input[i][0][1], input[j][0][1]) - min(input[i][0][1], input[j][0][1])) // 2]
            print('{}: {} and {} overlap!'.format(mid, input[i][0], input[j][0]))
