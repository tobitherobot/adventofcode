from intcode import Intcode

with open("y19/r05.txt") as f:
    opcode = [int(x.strip()) for x in f.readline().split(',')]

# star 1
ic = Intcode(opcode[:])
ic.input = 1
ic.run()