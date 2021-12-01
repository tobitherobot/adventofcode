with open("y20/r09.txt", "r") as f:
    input = [int(x.strip()) for x in f.readlines()]

# star 1
pre = 25
invalid = -1
for i in range(pre, len(input)):
    flag = False
    for j in range(i-pre, i-1):
        for k in range(i-pre+1, i):
            if input[j]+input[k]==input[i]:
                flag = True
                break
        if flag:
            break
    if not flag:
        invalid = input[i]
        break
print(invalid)

# star 2
flag = False
res = -1
for i in range(len(input)-1):
    for j in range(i+1, len(input)):
        s = sum(input[i:j])
        if s==invalid:
            res = min(input[i:j])+max(input[i:j])
            flag = True
            break
        elif s>invalid:
            break
    if flag:
        break
print(res)
