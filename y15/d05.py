with open("y15/r05.txt") as f:
    inp = [x.strip() for x in f.readlines()]

# star 1
count = 0
for l in inp:
    vowels = len(list(filter(lambda ch: ch=='a' or ch=='e' or ch=='i' or ch=='o' or ch=='u', l)))
    contain = not ('ab' in l or 'cd' in l or 'pq' in l or 'xy' in l)
    dblchr = False
    for i in range(len(l)-1):
        if l[i] == l[i+1]:
            dblchr = True
            break
    count += 1 if (3 <= vowels and dblchr and contain) else 0
print(count)

# star 2
count = 0
for l in inp:
    pair = False
    between = False
    for i in range(len(l)-3):
        for j in range(i+2, len(l)-1):
            if l[i] == l[j] and l[i+1] == l[j+1]:
                pair = True
                break
        if pair:
            break
    for i in range(len(l)-2):
        if l[i] == l[i+2]:
            between = True
            break
    if pair and between:
        count += 1
print(count)