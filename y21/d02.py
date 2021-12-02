with open("y21/r02.txt", "r") as f:
    input = [x.strip().split(" ") for x in f.readlines()]

# star 1
hor = 0
ver = 0
for line in input:
    if line[0]=='forward':
        hor += int(line[1])
    elif line[0]=='down':
        ver += int(line[1])
    else:
        ver -= int(line[1])
print(hor*ver)

# star 2
hor = 0
ver = 0
aim = 0
for line in input:
    if line[0]=='forward':
        hor += int(line[1])
        ver += aim * int(line[1])
    elif line[0]=='down':
        aim += int(line[1])
    else:
        aim -= int(line[1])
print(hor*ver)