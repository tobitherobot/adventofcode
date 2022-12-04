with open("y22/r04.txt") as f:
    input = []
    for l in f.readlines():
        res = []
        spl = l.strip().split(',')
        res.append([int(spl[0].split('-')[0]), int(spl[0].split('-')[1])])
        res.append([int(spl[1].split('-')[0]), int(spl[1].split('-')[1])])
        input.append(res)

# star 1
contain = 0
for pair in input:
    if pair[0][0] <= pair[1][0] and pair[1][1] <= pair[0][1] or pair[1][0] <= pair[0][0] and pair[0][1] <= pair[1][1]:
        contain += 1
print(contain)

# star 2
overlap = 0
for pair in input:
    if pair[0][0] <= pair[1][0] and pair[1][0] <= pair[0][1] or pair[1][0] <= pair[0][0] and pair[0][0] <= pair[1][1]:
        overlap += 1
print(overlap)