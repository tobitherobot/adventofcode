with open("y22/r14.txt") as f:
    abc = [l.strip().split(' -> ') for l in f.readlines()]
    input = []
    for l in abc:
        input.append([[int(xy.split(',')[0]), int(xy.split(',')[1])] for xy in l])

def get_tiles(start):
    minx = 500
    miny = 0
    maxx = 500
    maxy = 0
    for line in input:
        for coords in line:
            minx = min(minx, coords[0])
            miny = min(miny, coords[1])
            maxx = max(maxx, coords[0])
            maxy = max(maxy, coords[1])
    canvas = [['.'] * (maxx - minx + 1) for i in range(maxy - miny + 1)]
    
    start[0] = 0 - miny
    start[1] = 500 - minx

    for line in input:
        anc = line[0]
        for coords in line:
            for x in range(min(anc[0], coords[0]), max(anc[0], coords[0]) + 1):
                for y in range(min(anc[1], coords[1]), max(anc[1], coords[1]) + 1):
                    canvas[y - miny][x - minx] = '#'
            anc = coords
    canvas[0 - miny][500 - minx] = '+'
    return canvas

start = [0, 500]
tiles = get_tiles(start)

# star 1
flag = True
count = 0

while flag:
    flag = False
    sand = [start[0], start[1]]
    while True:
        if sand[0] == len(tiles)-1:
            break
        elif tiles[sand[0]+1][sand[1]] == '.':
            sand[0] += 1
        elif tiles[sand[0]+1][sand[1]] == '#' or tiles[sand[0]+1][sand[1]] == 'o':
            if tiles[sand[0]+1][sand[1]-1] == '.':
                sand = [sand[0]+1, sand[1]-1]
            elif tiles[sand[0]+1][sand[1]+1] == '.':
                sand = [sand[0]+1, sand[1]+1]
            else:
                tiles[sand[0]][sand[1]] = 'o'
                flag = True
                count += 1
                break
print(count)        

# star 2
flag = True
tiles = get_tiles(start)
tiles.append(['.'] * len(tiles[0]))
tiles.append(['#'] * len(tiles[0]))

while flag:
    flag = False
    sand = [start[0], start[1]]
    while True:
        if sand[0] == len(tiles)-1:
            tiles[sand[0]][sand[1]] = 'o'
            flag = True
            count += 1
            break
        if tiles[sand[0]+1][sand[1]] == '.':
            sand[0] += 1
        elif tiles[sand[0]+1][sand[1]] == '#' or tiles[sand[0]+1][sand[1]] == 'o':
            # expand tiles
            if sand[1]-1 < 0:
                for i in range(len(tiles)):
                    if i == len(tiles)-1:
                        tiles[i].insert(0, '#')
                    else:
                        tiles[i].insert(0, '.')
                sand[1] += 1
                start[1] += 1
            elif len(tiles[0]) <= sand[1]+1:
                for i in range(len(tiles)):
                    if i == len(tiles)-1:
                        tiles[i].append('#')
                    else:
                        tiles[i].append('.')
            # fall sand
            if 0<sand[1] and tiles[sand[0]+1][sand[1]-1] == '.':
                sand = [sand[0]+1, sand[1]-1]
            elif sand[1] < len(tiles[0])-1 and tiles[sand[0]+1][sand[1]+1] == '.':
                sand = [sand[0]+1, sand[1]+1]
            else:
                tiles[sand[0]][sand[1]] = 'o'
                flag = False if start[0]==sand[0] and start[1]==sand[1] else True
                count += 1
                break
    # print('\n'.join(''.join(x) for x in tiles))
print(count)