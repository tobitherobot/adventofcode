with open("y20/r03.txt", "r") as f:
    slopes = [x.strip() for x in f.readlines()]

# star 1
count = 0
x = 0
for s in slopes:
    if s[x]=='#':
        count += 1
    x = (x+3) % len(s)
print(count)

# star 2
prod = 1
steps = [[1,1],[1,3],[1,5],[1,7],[2,1]]
for yx in steps:
    count = 0
    y = yx[0]
    x = yx[1]
    while y<len(slopes):
        if slopes[y][x]=='#':
            count += 1
        y = (y+yx[0])
        x = (x+yx[1]) % len(s)
    prod *= count
print(prod)