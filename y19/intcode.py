
class Intcode:
    
    def __init__(self, opcode):
        self.opcode = opcode
        self.pos = 0
        self.broken = False

    def run(self):
        while self.opcode[self.pos] != 99 and self.pos < len(self.opcode) and not self.broken:
            self.do_instr()
            # print(self)

    def do_instr(self):
        if 1 == self.opcode[self.pos]: # add
            sm = self.opcode[self.opcode[self.pos + 1]] + self.opcode[self.opcode[self.pos + 2]]
            self.opcode[self.opcode[self.pos + 3]] = sm
            self.pos += 4
        elif 2 == self.opcode[self.pos]: # mul
            prd = self.opcode[self.opcode[self.pos + 1]] * self.opcode[self.opcode[self.pos + 2]]
            self.opcode[self.opcode[self.pos + 3]] = prd
            self.pos += 4
        else:
            print("error at " + str(self.pos))
            self.broken = True

    def __str__(self):
        return str(self.opcode)