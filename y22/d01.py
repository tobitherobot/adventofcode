with open("y22/r01.txt", "r") as f:
    elves = []
    group = []
    for x in f.readlines():
        if not x.strip():
            elves.append(group)
            group = []
        else:
            group.append(int(x.strip()))
    if sum(group) > 0:
        elves.append(group)
elves.sort(key=lambda x: sum(x), reverse=True)

# star 1
print(sum(elves[0]))

# star 2
print(sum(elves[0]) + sum(elves[1]) + sum(elves[2]))
