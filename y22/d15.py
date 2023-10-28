def get_distance(p1, p2):
    return abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])

def does_overlap(p1, d1, p2, d2):
    return True if get_distance(p1, p2) <= d1 + d2 else False

def check_coords(p):
    flag = True
    for l in input:
        if does_overlap(l[0], get_distance(l[0], l[1]), p, 0):
            flag = False
            break
    return flag

with open("y22/r15.txt") as f:
    input = []
    line = f.readline().strip()
    while line:
        sx = int(line[12 : line.index(',')])
        sy = int(line[line.index(',')+4 : line.index(':')])
        sub = line[line.index('is') :]
        bx = int(sub[8 : sub.index(',')])
        by = int(sub[sub.index(',')+4 :])
        input.append([[sy, sx], [by, bx]])
        line = f.readline().strip()

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

# star 2
mids = []
for a in range(len(input)-1):
    ra = get_distance(input[a][0], input[a][1])
    for b in range(a+1, len(input)):
        rb = get_distance(input[b][0], input[b][1])
        if not does_overlap(input[a][0], ra, input[b][0], rb) and does_overlap(input[a][0], ra+1, input[b][0], rb+1):
            if get_distance(input[a][0], input[b][0]) % 2 == 0:
            mid = [min(input[a][0][0], input[b][0][0]) + (max(input[a][0][0], input[b][0][0]) - min(input[a][0][0], input[b][0][0])) // 2, min(input[a][0][1], input[b][0][1]) + (max(input[a][0][1], input[b][0][1]) - min(input[a][0][1], input[b][0][1])) // 2]
            mids.append(mid)

print(mids)
dirs = [[-1,-1],[-1,1],[1,-1],[1,1]]
flag = False
center = [2000000, 2000000]
res = [0, 0]
for mid in mids:
    for dir in dirs:
        prev = [mid[0], mid[1]]
        p = [mid[0], mid[1]]
        while True:
            p = [p[0] + dir[0], p[1] + dir[1]]
            if 0 <= p[0] and p[0] <= 4000000 and 0 <= p[1] and p[1] <= 4000000:
                if check_coords(p):
                    res = p
                    flag = True
                    break
            elif get_distance(center, p) > get_distance(center, prev):
                print('{} and prev {}'.format(p, prev))
                break
            prev = [p[0], p[1]]
    if flag:
        break

print(res[1] * 4000000 + res[0])