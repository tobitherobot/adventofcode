with open("y25/r01.txt") as f:
    input = [x.strip() for x in f.readlines()]

# star 1
start = 50
points_zero = 0
for line in input:
    distance = int(line[1:])
    if line[0] == 'L':
        start = (start + (100 - distance)) % 100
    else:
        start = (start + distance) % 100
    if start == 0:
        points_zero += 1
print(points_zero)
    
# star 2
start = 50
clicks_zero = 0
for line in input:
    distance = int(line[1:])
    if line[0] == 'L':
        if distance >= start:
            if start != 0:
                clicks_zero += 1
            distance -= start
            start = 100
        clicks_zero += (distance // 100)
        start -= (distance % 100)
    else:
        if (distance + start) >= 100:
            if start != 0:
                clicks_zero += 1
            distance = distance + start - 100
            start = 0
        clicks_zero += (distance // 100)
        start = distance % 100
    start = start % 100
print(clicks_zero)