with open("y20/r07.txt", "r") as f:
    input = [x.strip().replace(",","").replace(".","").split(" ") for x in f.readlines()]

# bags
bags = {}
for line in input:
    name = " ".join(line[0:3])
    if len(line[2])==4:
        name = name[:-1]
    contains = {}
    if 7<len(line):
        for x in range(4, len(line), 4):
            n = " ".join(line[x+1:x+4])
            i = int(line[x])
            if len(line[x+3])==4:
                n = n[:-1]
            contains[n] = i
    bags[name] = contains

# star 1
holds = {"shiny gold bag"}
adds = set()
count = 0
flag = True
while flag:
    flag = False
    for bag in bags:
        for hold in holds:
            if hold in bags[bag] and bag not in holds:
                adds.add(bag)
                flag = True
    for a in adds:
        holds.add(a)
print(len(holds)-1)

# star 2
def count_bags(name, bags):
    if len(bags[name])==0:
        return 1
    else:
        c = 1
        for bag in bags[name]:
            c += bags[name][bag] * count_bags(bag, bags)
        return c

print(count_bags("shiny gold bag", bags)-1)
