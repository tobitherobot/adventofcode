import copy

class Monkey:
    def __init__(self, id, items, operation, test, iftrue, iffalse):
        self.id = id
        self.items = items
        self.operation = operation
        self.test = test
        self.iftrue = iftrue
        self.iffalse = iffalse
        self.inspect_count = 0

    def do_simple_operation(self, item_idx):
        
        a = self.items[item_idx][0] if self.operation[0] == 'old' else int(self.operation[0])
        b = self.items[item_idx][0] if self.operation[2] == 'old' else int(self.operation[2])
        res = -1

        if self.operation[1] == '+':
            res = a+b
        elif self.operation[1] == '-':
            res = a-b
        elif self.operation[1] == '*':
            res = a*b
        else:
            res = a//b

        self.items[item_idx][0] = res // 3
        self.inspect_count += 1

    def do_advanced_operation(self, item_idx):

        b = -1 if self.operation[2] == 'old' else int(self.operation[2])
        res = -1

        if self.operation[1] == '+':
            for i in range(len(div_list)):
                self.items[item_idx][1][i] = (self.items[item_idx][1][i] + b) % div_list[i]
        elif self.operation[1] == '-':
            for i in range(len(div_list)):
                self.items[item_idx][1][i] = (self.items[item_idx][1][i] + div_list[i] - b) % div_list[i]
        else:
            for i in range(len(div_list)):
                self.items[item_idx][1][i] = (self.items[item_idx][1][i] * (b if 0<=b else self.items[item_idx][1][i])) % div_list[i]
        self.inspect_count += 1
        self.items[item_idx][0] = res

    def __str__(self):
        # return "Monkey {}: {} ({})".format(self.id, self.items, ' '.join(self.operation))
        return "Monkey {}: {}".format(self.id, self.inspect_count)

initial_monkeys = {}
div_list = []

with open("y22/r11.txt") as f:

    line = f.readline()
    while line:
        id = int(line.strip().split(' ')[1][:-1])
        items = [[int(x)] for x in f.readline().strip()[16:].split(', ')]
        operation = f.readline().strip()[17:].split(' ')
        test = int(f.readline().strip()[19:])
        div_list.append(test)
        iftrue = int(f.readline().strip()[25:])
        iffalse = int(f.readline().strip()[25:])
        f.readline()

        initial_monkeys[id] = Monkey(id, items, operation, test, iftrue, iffalse)
        line = f.readline()

for m in initial_monkeys:
    for i in range(len(initial_monkeys[m].items)):
        l = []
        for div in div_list:
            l.append(initial_monkeys[m].items[i][0] % div)
        initial_monkeys[m].items[i].append(l)

# star 1
monkeys = copy.deepcopy(initial_monkeys)
rounds = 20

for x in range(rounds):
    for m in range(len(monkeys)):
        # throw items
        for i in range(len(monkeys[m].items)):
            
            monkeys[m].do_simple_operation(i)

            if monkeys[m].items[i][0] % monkeys[m].test == 0:
                monkeys[monkeys[m].iftrue].items.append(monkeys[m].items[i])
            else:
                monkeys[monkeys[m].iffalse].items.append(monkeys[m].items[i])

        monkeys[m].items.clear()

inspect_counts = []
for idx in monkeys:
    inspect_counts.append(monkeys[idx].inspect_count)
inspect_counts.sort()
print(inspect_counts[-1] * inspect_counts[-2])

# star 2
monkeys = copy.deepcopy(initial_monkeys)
rounds = 10000

for x in range(rounds):
    for m in range(len(monkeys)):
        # throw items
        for i in range(len(monkeys[m].items)):
            
            monkeys[m].do_advanced_operation(i)

            if monkeys[m].items[i][1][m] == 0:
                monkeys[monkeys[m].iftrue].items.append(monkeys[m].items[i])
            else:
                monkeys[monkeys[m].iffalse].items.append(monkeys[m].items[i])

        monkeys[m].items.clear()

inspect_counts = []
for idx in monkeys:
    inspect_counts.append(monkeys[idx].inspect_count)
inspect_counts.sort()
print(inspect_counts[-1] * inspect_counts[-2])