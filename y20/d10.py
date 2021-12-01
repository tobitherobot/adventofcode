with open("y20/r10.txt", "r") as f:
    input = [int(x.strip()) for x in f.readlines()]

input.append(0)
input.sort()
input.append(input[-1]+3)

# star 1
diff1 = 0
diff3 = 0
for i in range(len(input)-1):
    if input[i+1]-input[i]==1:
        diff1 += 1
    elif input[i+1]-input[i]==3:
        diff3 += 1
print(diff1*diff3)

# star 2 TODO
'''
res = 1
for i in range(len(input)-1, 0, -1):
    c = 0
    try:
        if input[i]-1 in input:
            c += 1
        if input[i]-2 in input:
            c += 1
        if input[i]-3 in input:
            c += 1
    except:
        pass
    print(str(input[i]) + " " + str(c))
    if c>0:
        res *= c
print(res)
'''