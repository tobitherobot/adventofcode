with open("y18/r01.txt") as f:
    input = [int(x) for x in f.readlines()]

# star 1
res = 0
for n in input:
    res += n
print(res)

# star 2
res = input[0]
prev = set()
i = 0
while res not in prev:
    prev.add(res)
    i += 1
    res += input[i % len(input)]
print(res)