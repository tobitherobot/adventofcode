from intcode import Intcode

with open("y19/r02.txt") as f:
    opcode = [int(x.strip()) for x in f.readline().split(',')]

# star 1
ic = Intcode(opcode[:])
ic.opcode[1] = 12
ic.opcode[2] = 2
ic.run()
print(ic.opcode[0])

# star 2
res = -1
for i in range(100):
    for j in range(100):
        ic = Intcode(opcode[:])
        ic.opcode[1] = i
        ic.opcode[2] = j
        ic.run()
        if ic.opcode[0] == 19690720:
            res = 100 * i + j
            break
print(res)