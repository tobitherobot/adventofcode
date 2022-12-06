with open("y15/r08.txt") as f:
    inp = [x.strip() for x in f.readlines()]

# star 1
chars = 0
total = 0
for l in inp:
    i = 1
    total += len(l)
    while i < len(l)-1:
        if l[i] == '\\':
            if l[i+1] == 'x':
                i += 4
            else:
                i += 2
            chars += 1
        else:
            chars += 1
            i += 1
print(total - chars)

# star 2
chars = 0
total = 0
for l in inp:
    i = 0
    chars += len(l)
    total += len(l) + 2
    while i < len(l):
        if l[i] == '\"' or l[i] == '\\':
            total += 1
        i += 1
print(total - chars)