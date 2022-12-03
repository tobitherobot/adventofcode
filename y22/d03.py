with open("y22/r03.txt") as f:
    input = [x.strip() for x in f.readlines()]

def get_prio(ch):
    if ord(ch) >= ord('a') and ord(ch) <= ord('z'):
        return ord(ch) - ord('a') + 1
    else:
        return ord(ch) - ord('A') + 27

# star 1
sum = 0
for l in input:
    flag = False
    for i in range(len(l) // 2):
        for j in range(len(l) // 2, len(l)):
            if l[i] == l[j]:
                sum += get_prio(l[i])
                flag = True
                break
        if flag:
            break
print(sum)

# star 2
sum = 0
for x in range(len(input) // 3):
    flag = False
    # print(len(input) // 3)
    for ic in input[x*3]:
        for jc in input[x*3+1]:
            for kc in input[x*3+2]:
                if ic == jc and ic == kc:
                    sum += get_prio(ic)
                    flag = True
                    break
            if flag:    
                break
        if flag:
            break
print(sum)