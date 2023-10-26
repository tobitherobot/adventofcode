with open("y22/r14.txt") as f:
    input = [[int(xy.split(',')[0]), int(xy.split(',')[1])] for xy in (l.strip().split(' -> ') for l in f.readlines())]

def get_tiles(input):
    tiles = []
    for line in input:
        pass


start = [500, 0]

# star 1
print(input)