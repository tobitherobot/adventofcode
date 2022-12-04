
class Intcode:
    
    def __init__(self, opcode):
        self.opcode = opcode
        self.pos = 0

        self.input = None
        self.output = None

        self.broken = False

    def run(self):
        while self.opcode[self.pos] != 99 and self.pos < len(self.opcode) and not self.broken:
            self.do_instr()
            # print(self)

    def get_value(self, value, mode):
        if mode == 1:
            return value
        else:
            return self.opcode[value]

    def do_instr(self):
        code = self.opcode[self.pos] % 100
        mode1 = (self.opcode[self.pos] % 1000) // 100
        mode2 = (self.opcode[self.pos] % 10000) // 1000
        mode3 = (self.opcode[self.pos] % 100000) // 10000
        if code == 1: # add
            a = self.get_value(self.opcode[self.pos + 1], mode1)
            b = self.get_value(self.opcode[self.pos + 2], mode2)
            self.opcode[self.opcode[self.pos + 3]] = a + b # no mode for result
            self.pos += 4
        elif code == 2: # mul
            a = self.get_value(self.opcode[self.pos + 1], mode1)
            b = self.get_value(self.opcode[self.pos + 2], mode2)            
            self.opcode[self.opcode[self.pos + 3]] = a * b # no mode for result
            self.pos += 4
        elif code == 3: # input
            # ???
            self.pos += 2
        elif code == 4: # output
            a = self.get_value(self.opcode[self.pos + 1], mode1)
            print(a)
            self.pos += 2
        else:
            print("error at " + str(self.pos))
            print(self.opcode)
            self.broken = True

    def __str__(self):
        return str(self.opcode)