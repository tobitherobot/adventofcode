with open("y21/r03.txt") as f:
    input = [x.strip() for x in f.readlines()]

def get_most(input):
    most = ""
    for i in range(len(input[0])):
        sum = 0
        for j in range(len(input)):
            sum += int(input[j][i])
        most += str(int(sum / len(input) + 0.5)) # 0.5 -> 1
    return most

# star 1
most = ""
for i in range(len(input[0])):
    sum = 0
    for j in range(len(input)):
        sum += int(input[j][i])
    most += str(round(sum / len(input)))
least = ''.join('1' if x=='0' else '0' for x in most)
print(int(most, 2) * int(least, 2))

# star 2
oxy = input.copy() # most
co2 = input.copy() # least
for i in range(len(oxy[0])):
    noxy = []
    for j in range(len(oxy)):
        if most[i]==oxy[j][i]:
            noxy.append(oxy[j])
    oxy = noxy.copy()
    if len(oxy)==1:
        break
    most = get_most(oxy)
    noxy.clear()
for i in range(len(co2[0])):
    nco2 = []
    for j in range(len(co2)):
        if least[i]==co2[j][i]:
            nco2.append(co2[j])
    co2 = nco2.copy()
    if len(co2)==1:
        break
    least = ''.join('1' if x=='0' else '0' for x in get_most(co2))
    nco2.clear()
print(int(oxy[0], 2) * int(co2[0], 2))
