with open('y15/r16.txt') as f:
    sues = {}
    for x in f.readlines():
        l = x.strip().split(' ')
        # capacity durability flavor texture calories 
        sues[int(l[1][:-1])] = {l[2][:-1]: int(l[3][:-1]), l[4][:-1]: int(l[5][:-1]), l[6][:-1]: int(l[7])}

original_sue = { 'children': 3, 'cats': 7, 'samoyeds': 2, 'pomeranians': 3, 'akitas': 0, 'vizslas': 0, 'goldfish': 5, 'trees': 3, 'cars': 2, 'perfumes': 1 }

# star 1
for sue in sues:
    match = True
    for k in sues[sue]:
        if sues[sue][k] != original_sue[k]:
            match = False
            break
    if match:
        print(sue)
        break

# star 2
for sue in sues:
    match = True
    for k in sues[sue]:
        if k in ['cats', 'trees']:
            if sues[sue][k] <= original_sue[k]:
                match = False
                break
        elif k in ['pomeranians', 'goldfish']:
            if sues[sue][k] >= original_sue[k]:
                match = False
                break
        elif sues[sue][k] != original_sue[k]:
            match = False
            break
    if match:
        print(sue)
        break