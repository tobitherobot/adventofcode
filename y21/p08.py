with open("y21/r08.txt") as f:
    input = []
    n = []
    for l in f.readlines():
        if len(n)==0:
            n.append(l[:-2].strip().split(" "))
        else:
            n.append(l.strip().split(" "))
            input.append(n.copy())
            n.clear()

# star 1 TODO
count = 0
for l in input:
    for x in l[1]:
        if len(x)==2 or len(x)==4 or len(x)==3 or len(x)==7:
            count += 1
print(count)