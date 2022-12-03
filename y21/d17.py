import math

with open("y21/r17.txt") as f:
    s = f.readline()[15:].strip().split(', y=')
    xstart = int(s[0].split('..')[0])
    xend = int(s[0].split('..')[1]) + 1
    ystart = int(s[1].split('..')[0])
    yend = int(s[1].split('..')[1]) + 1

# star 1
maxy = abs(ystart) - 1
hgt = 0
for i in range(maxy + 1):
    hgt += i
print(hgt)


# star 2
xvels = []
for x in range(xend):
    xpos = 0
    flag = False
    for i in reversed(range(x+1)):
        xpos += i
        if xstart <= xpos and xpos < xend:
            flag = True
            break
    if flag:
        xvels.append(x)

yvels = []
for y in range(ystart, maxy+1):
    ypos = 0
    flag = False
    yvel = y
    while ystart <= ypos:
        ypos += yvel
        yvel -= 1
        if ystart <= ypos and ypos < yend:
            flag = True
            break
    if flag:
        yvels.append(y)

unique = set()
for x in xvels:
    for y in yvels:
        vel = [x, y]
        pos = [0, 0]
        while pos[0] < xend and ystart <= pos[1]:
            pos = [pos[0]+vel[0], pos[1]+vel[1]]
            vel = [vel[0]-1 if vel[0] > 0 else 0, vel[1]-1]
            if xstart <= pos[0] and pos[0] < xend and ystart <= pos[1] and pos[1] < yend:
                unique.add(str(x) + ", " + str(y))
print(len(unique))
