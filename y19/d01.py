with open("y19/r01.txt") as f:
    inp = [int(x.strip()) for x in f.readlines()]

# star 1
sm = 0
for i in inp:
    sm += i//3-2
print(sm)

# star 2
def get_fuel(n):
    f = n//3 - 2
    if 8 < f:
        f += get_fuel(f)
    return f

sm = 0
for i in inp:
    sm += get_fuel(i)
print(sm)