with open("y21/r05.txt") as f:
    input = [[[int(z) for z in y.split(",")] for y in x.strip().split(" -> ")] for x in f.readlines()]

# star 1
points = {}
count = 0
for pipe in input:
    if pipe[0][0]==pipe[1][0] or pipe[0][1]==pipe[1][1]:
        for i in range(min(pipe[0][0], pipe[1][0]), max(pipe[0][0], pipe[1][0])+1):
            for j in range(min(pipe[0][1], pipe[1][1]), max(pipe[0][1], pipe[1][1])+1):
                coords = [i, j]
                if str(coords) in points:
                    if points[str(coords)]==1:
                        count += 1
                    points[str(coords)] += 1
                else:
                    points[str(coords)] = 1
print(count)

# star 2
points = {}
count = 0
for pipe in input:
    if pipe[0][0]==pipe[1][0] or pipe[0][1]==pipe[1][1]:
        for i in range(min(pipe[0][0], pipe[1][0]), max(pipe[0][0], pipe[1][0])+1):
            for j in range(min(pipe[0][1], pipe[1][1]), max(pipe[0][1], pipe[1][1])+1):
                coords = [i, j]
                if str(coords) in points:
                    if points[str(coords)]==1:
                        count += 1
                    points[str(coords)] += 1
                else:
                    points[str(coords)] = 1
    else:
        diff = [0, 0]
        if pipe[0][0]<pipe[1][0]:
            diff[0] = 1
        elif pipe[0][0]>pipe[1][0]:
            diff[0] = -1
        if pipe[0][1]<pipe[1][1]:
            diff[1] = 1
        elif pipe[0][1]>pipe[1][1]:
            diff[1] = -1
        coords = [pipe[0][0], pipe[0][1]]

        for i in range(abs(pipe[0][0]-pipe[1][0])+1):
            if str(coords) in points:
                if points[str(coords)]==1:
                    count += 1
                points[str(coords)] += 1
            else:
                points[str(coords)] = 1
            coords[0] += diff[0]
            coords[1] += diff[1]
print(count)
