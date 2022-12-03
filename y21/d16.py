import math

with open("y21/r16.txt") as f:
    l = f.readline()
    bnry = bin(int(l, 16))[2:]
    input = (len(l) * 4 - len(bnry)) * '0' + bnry # goddamn leading zeroes

class Packet:
    def __init__(self, input, start):
        self.input = input
        self.start = start
        self.version = int(input[start:start+3], 2)
        self.typeid = int(input[start+3:start+6], 2)
        self.subs = []

    def parse(self):
        self.length = 6

        if self.typeid == 4: # literal value
            pkts = []
            ptr = self.start + 6
            while True:
                pkt = input[ptr:ptr+5]
                pkts.append(pkt[1:])
                ptr += 5
                if pkt[0] == '0':
                    break
            self.value = int(''.join(pkts), 2)
            self.length += len(pkts) * 5
        else:
            lengthid = int(input[self.start+6])
            self.length += 1

            if lengthid == 0: # length in bits
                bits = int(input[self.start+7:self.start+22], 2)
                self.length += 15
                ptr = self.start + self.length
                while self.length < bits + 22:
                    sub = Packet(input, ptr)
                    self.subs.append(sub)
                    sub.parse()
                    self.length += sub.length
                    ptr += sub.length
            else: # number of packets
                number = int(input[self.start+7:self.start+18], 2)
                self.length += 11
                ptr = self.start + self.length
                for i in range(number):
                    sub = Packet(input, ptr)
                    self.subs.append(sub)
                    sub.parse()
                    self.length += sub.length
                    ptr += sub.length  
        # print(self)
    
    def get_value(self):
        # print(self)

        if self.typeid == 0: # sum
            return sum([sub.get_value() for sub in self.subs])
        elif self.typeid == 1: # product
            return math.prod([sub.get_value() for sub in self.subs])
        elif self.typeid == 2: # min
            self.subs.sort(key=lambda x: x.get_value())
            return self.subs[0].get_value()
        elif self.typeid == 3: # max
            self.subs.sort(key=lambda x: x.get_value())
            return self.subs[-1].get_value()
        elif self.typeid == 4: # literal value
            return self.value
        elif self.typeid == 5: # gt
            return 1 if self.subs[0].get_value() > self.subs[1].get_value() else 0
        elif self.typeid == 6: # ls
            return 1 if self.subs[0].get_value() < self.subs[1].get_value() else 0
        else: # eq
            return 1 if self.subs[0].get_value() == self.subs[1].get_value() else 0

    
    def get_version_sum(self):
        return self.version + sum([x.get_version_sum() for x in self.subs])

    def __str__(self):
        return "v" + str(self.version) + ": " + str(self.typeid) + " (" + str(self.length) + ")"

packet = Packet(input, 0)
packet.parse()

# star 1
print(packet.get_version_sum())

# star 2
print(packet.get_value())