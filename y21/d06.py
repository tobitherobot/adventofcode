with open("y21/r06.txt") as f:
    input = [int(x) for x in f.readline().strip().split(",")]

# star 1
days = 80
count = [0] * (days+9)
for f in input:
    count[f] += 1
for i in range(days):
    count[i+7] += count[i]
    count[i+9] += count[i]
    count[i] = 0
print(sum(count[-9:]))

# star 2
days = 256
count = [0] * (days+9)
for f in input:
    count[f] += 1
for i in range(days):
    count[i+7] += count[i]
    count[i+9] += count[i]
    count[i] = 0
print(sum(count[-9:]))