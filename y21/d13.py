import sys
import copy

class point:
    def __init__(self, y=None, x=None):
        self.x = x
        self.y = y

def print_points(points):
    minY = sys.maxsize
    minX = sys.maxsize
    maxY = -sys.maxsize-1
    maxX = -sys.maxsize-1
    for p in points:
        minY = min(minY, p.y)
        minX = min(minX, p.x)
        maxY = max(maxY, p.y)
        maxX = max(maxX, p.x)

    lenY = abs(maxY - minY) + 1
    lenX = abs(maxX - minX) + 1
    offY = 0 if 0<=minY else abs(minY)
    offX = 0 if 0<=minX else abs(minX)
    m = [[' '] * lenX for i in range(lenY)]

    for p in points:
        m[p.y+offY][p.x+offX] = '#'
    for r in m:
        print(''.join(r))

with open("y21/r13.txt") as f:
    points = []
    instr = []

    l = f.readline().strip()
    while 1<len(l):
        l = l.split(",")
        points.append(point(int(l[1]), int(l[0])))
        l = f.readline().strip()

    l = f.readline().strip()
    while 1<len(l):
        l = l.split(" ")[2].split("=")
        instr.append([l[0], int(l[1])])
        l = f.readline().strip()

# star 1
poi1 = copy.deepcopy(points)
i = instr[0]
if i[0]=='y':
    for p in poi1:
        if i[1]<p.y:
            p.y -= (p.y - i[1]) * 2
else:
    for p in poi1:
        if i[1]<p.x:
            p.x -= (p.x - i[1]) * 2
uniq = set()
for p in poi1:
    uniq.add(str(p.y) + "/" + str(p.x))
print(len(uniq))

# star 2
poi2 = copy.deepcopy(points)
for i in instr:
    if i[0]=='y':
        for p in poi2:
            if i[1]<p.y:
                p.y -= (p.y - i[1]) * 2
    else:
        for p in poi2:
            if i[1]<p.x:
                p.x -= (p.x - i[1]) * 2
print_points(poi2)
