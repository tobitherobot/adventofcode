with open("y15/r07.txt") as f:
    instr = {}
    for l in f.readlines():
        spl = l.split(' -> ')
        instr[spl[1].strip()] = spl[0].split(' ')

# star 1
def calc_value(ch, instr, values):
    if ch not in values.keys():
        if len(instr[ch]) == 1: # value
            values[ch] = get_value(instr[ch][0], instr, values)
        elif len(instr[ch]) == 2: # not
            c = get_value(instr[ch][1], instr, values)
            values[ch] = 65536 + (~ c)
        elif instr[ch][1] == 'AND':
            c1 = get_value(instr[ch][0], instr, values)
            c2 = get_value(instr[ch][2], instr, values)
            values[ch] = c1 & c2
        elif instr[ch][1] == 'OR':
            c1 = get_value(instr[ch][0], instr, values)
            c2 = get_value(instr[ch][2], instr, values)
            values[ch] = c1 | c2
        elif instr[ch][1] == 'LSHIFT':
            c1 = get_value(instr[ch][0], instr, values)
            c2 = get_value(instr[ch][2], instr, values)
            values[ch] = c1 << c2
        else: # rshift
            c1 = get_value(instr[ch][0], instr, values)
            c2 = get_value(instr[ch][2], instr, values)
            values[ch] = c1 >> c2           

def get_value(ch, instr, values):
    if ord('0') <= ord(ch[0]) and ord(ch[0]) <= ord('9'):
        return int(ch)
    else:
        if ch not in values:
            calc_value(ch, instr, values)
        return values[ch]

values = {}
for k in instr.keys():
    calc_value(k, instr, values)
a = values['a']
print(a)

# star 2
values = {}
values['b'] = a
for k in instr.keys():
    calc_value(k, instr, values)
print(values['a'])