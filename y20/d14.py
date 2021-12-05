import math

with open("y20/r14.txt") as f:
    input = [x.strip().split(" = ") for x in f.readlines()]

def get_floating_addresses(binary):
    addresses = []
    count = binary.count('X')
    for i in range(int(math.pow(2, count))):
        bc = binary.copy()
        b = "{0:b}".format(i).zfill(count)
        idx = 0
        for j in range(len(binary)):
            if bc[j]=='X':
                bc[j] = b[idx]
                idx += 1
        addresses.append(int(''.join(bc), 2))
    return addresses

# star 1
memory = {}
mask = ""
for line in input:
    if line[0]=="mask":
        mask = line[1]
    else:
        mem = int(line[0][4:-1])
        val = int(line[1])
        binary = list("{0:b}".format(val).zfill(36))
        for i in range(len(mask)):
            if mask[i]!='X':
                binary[i] = mask[i]
        n = int(''.join(binary), 2)
        memory[mem] = n
sum = 0
for m in memory:
    sum += memory[m]
print(sum)

# star 2
memory = {}
mask = ""
for line in input:
    if line[0]=="mask":
        mask = line[1]
    else:
        mem = int(line[0][4:-1])
        val = int(line[1])
        binary = list("{0:b}".format(mem).zfill(36))
        for i in range(len(mask)):
            if mask[i]!='0':
                binary[i] = mask[i]
        addresses = get_floating_addresses(binary)
        for address in addresses:
            memory[address] = val
sum = 0
for m in memory:
    sum += memory[m]
print(sum)
