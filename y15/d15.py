with open('y15/r15.txt') as f:
    inp = []
    for x in f.readlines():
        l = x.strip().split(' ')
        # capacity durability flavor texture calories 
        inp.append([int(l[2][:-1]), int(l[4][:-1]), int(l[6][:-1]), int(l[8][:-1]), int(l[10])])

for i in inp:
    print(i)

# star 1

# star 2