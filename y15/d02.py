import math

with open("y15/r02.txt") as f:
    inp = [[int(x) for x in l.strip().split('x')] for l in f.readlines()]

# star 1
sm = 0
for l in inp:
    a = l[0]*l[1]
    b = l[0]*l[2]
    c = l[1]*l[2]
    sm += a*2 + b*2 + c*2 + min(min(a, b), c)
print(sm)

# star 2
sm = 0
for l in inp:
    sm += 2*l[0] + 2*l[1] + 2*l[2] - 2*max(max(l[0], l[1]), l[2]) + l[0]*l[1]*l[2]
print(sm)