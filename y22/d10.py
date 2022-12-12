with open('y22/r10.txt') as f:
    inp = [x.strip() for x in f.readlines()]

# star 1
cycle = 1
x = 1
strength = 0
val = [20, 60, 100, 140, 180, 220]
while cycle <= val[-1]:
    for l in inp:
        if cycle in val:
            strength += cycle * x
        if l == 'noop':
            cycle += 1
        else:
            n = int(l.split(' ')[1])
            cycle += 1
            if cycle in val:
                strength += cycle * x
            cycle += 1
            x += n
print(strength)

# star 2
cycle = 0
idx = 0
x = 1
double = False
s = ''
while idx < len(inp):
    cycle += 1
    if x-1 <= (cycle % 40)-1 and (cycle % 40)-1 <= x+1:
        s += '#'
    else:
        s += ' '
    if inp[idx] == 'noop':
        idx += 1
    else:
        if double:
            double = False
            val = int(inp[idx].split(' ')[1])
            x += val
            idx += 1
        else:
            double = True
    if cycle % 40 == 0:
        print(s)
        s = ''