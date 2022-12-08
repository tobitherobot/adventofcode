import itertools

with open("y15/r09.txt") as f:
    inp = []
    cities = set()
    for l in f.readlines():
        spl = l.split(' ')
        inp.append([spl[0], spl[2], int(spl[4])])
        cities.add(spl[0])
        cities.add(spl[2])

routes = {}
cities = list(cities)
for city in cities:
    routes[city] = {}
for l in inp:
    routes[l[0]][l[1]] = l[2]
    routes[l[1]][l[0]] = l[2]

# star 1
mn = 99999999
for perm in itertools.permutations(cities):
    dist = 0
    for i in range(len(cities)-1):
        dist += routes[perm[i]][perm[i+1]]
    mn = min(mn, dist)
print(mn)

# star 2
mx = 0
for perm in itertools.permutations(cities):
    dist = 0
    for i in range(len(cities)-1):
        dist += routes[perm[i]][perm[i+1]]
    mx = max(mx, dist)
print(mx)