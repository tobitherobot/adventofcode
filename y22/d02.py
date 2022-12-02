with open("y22/r02.txt") as f:
    input = [x.split() for x in f.readlines()]

# rock: 1AX, paper: 2BY, scissors: 3CZ
# win: 6, draw: 3, loss: 0
def get_score(input):
    scoreyou = 0
    scoreme = 0
    scores = [1,2,3]
    for l in input:
        you = ord(l[0]) - ord('A')
        me = ord(l[1]) - ord('X')

        if you == me:
            scoreyou += 3
            scoreme += 3
        elif you - me > -2 and you - me < 2:
            if you - me < 0: # i won
                scoreme += 6
            else:
                scoreyou += 6
        else:
            if me < you:
                scoreme += 6
            else:
                scoreyou += 6

        scoreyou += scores[you]
        scoreme += scores[me]
    return scoreme

# star 1
print(get_score(input))

# star 2
# X: loose, Y: draw, Z: win
conv = []
for l in input:
    if l[1] == 'Y':
        conv.append([l[0], chr(ord(l[0]) - ord('A') + ord('X'))])
    elif l[1] == 'Z':
        choose = chr((ord(l[0]) - ord('A') + 1) % 3 + ord('X'))
        conv.append([l[0], choose])
    else:
        choose = chr((ord(l[0]) - ord('A') + 2) % 3 + ord('X'))
        conv.append([l[0], choose])
print(get_score(conv))
