
with open("y21/r18.txt") as f:
    input = [l.strip() for l in f.readlines()]

class Pair: # messy af
    def parse(self, input):
        if input[self.start+1] == '[':
            self.first = Pair(input, self.start+1, self.depth+1)
            self.length += self.first.length + 1
            self.first.previous = self
        else:
            self.first = ord(input[self.start+1]) - ord('0')
            self.length += 1 + 1

        if input[self.start+self.length] == '[':
            self.second = Pair(input, self.start+self.length, self.depth+1)
            self.length += self.second.length + 1
            self.second.previous = self
        else:
            self.second = ord(input[self.start+self.length]) - ord('0')
            self.length += 1 + 1

    def update(self, depth):
        self.set_depth(depth) # depth = depth
        flag = False
        
        if isinstance(self.first, Pair):
            flag = flag or self.first.update(depth+1)
        if isinstance(self.second, Pair):
            flag = flag or self.second.update(depth+1)

        if 4 <= self.depth and isinstance(self.first, int) and isinstance(self.second, int):
            self.explode()
            flag = True
        elif isinstance(self.first, int) and 9 < self.first:
            pair = Pair("[0,0]", 0, self.depth+1)
            pair.first = self.first // 2
            pair.second = self.first // 2 + 1
            pair.previous = self
            self.first = pair
            flag = True
        if isinstance(self.second, int) and 9 < self.second:
            pair = Pair("[0,0]", 0, self.depth+1)
            pair.first = self.second // 2
            pair.second = self.second // 2 + 1
            pair.previous = self
            self.second = pair
            flag = True
        
        return flag

    def explode(self):
        if isinstance(self.first, Pair):
            self.first.explode()
            return True
        elif isinstance(self.second, Pair):
            self.second.explode()
            return True
        else:        
            me = self
            value = self.first
            while me.previous != None:
                if me.previous.second == me: # add first value
                    if isinstance(me.previous.first, Pair):
                        me.previous.first.add_second(value)
                    else:
                        me.previous.first += value
                    break
                else:
                    me = me.previous

            me = self
            value = self.second
            while me.previous != None:
                if me.previous.first == me: # add second value
                    if isinstance(me.previous.second, Pair):
                        me.previous.second.add_first(value)
                    else:
                        me.previous.second += value
                    break
                else:
                    me = me.previous
            
            if self.previous.first == self:
                self.previous.first = 0
            else:
                self.previous.second = 0
            return True
    
    def add(self, pair):
        res = Pair("[0,0]", 0, 0)
        pair.previous = res
        res.second = pair
        self.previous = res
        res.first = self
        return res

    def set_depth(self, depth):
        self.depth = depth
        if isinstance(self.first, Pair):
            self.first.set_depth(depth+1)
        if isinstance(self.second, Pair):
            self.second.set_depth(depth+1)

    def add_first(self, value):
        if isinstance(self.first, Pair):
            self.first.add_first(value)
        else:
            self.first += value

    def add_second(self, value):
        if isinstance(self.second, Pair):
            self.second.add_second(value)
        else:
            self.second += value

    def __init__(self, input, start, depth):
        self.previous = None
        self.first = None
        self.second = None
        self.depth = depth

        self.start = start
        self.length = 1
        
        self.parse(input)

    def __str__(self):
        return '[' + str(self.first) + "," + str(self.second) + "]"#(" + str(self.depth) + ")"

# star 1
p = Pair(input[0], 0, 0)
print(p)
for i in range(1, len(input)):
    p = p.add(Pair(input[i], 0, 0))
    flag = True
    # while flag: