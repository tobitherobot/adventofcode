class Dir:
    def get_size(self):
        for k in self.files.keys():
            self.size += self.files[k]
        for k in self.dirs.keys():
            self.size += self.dirs[k].get_size()
        return self.size

    def __init__(self, name):
        self.name = name
        self.size = 0
        self.dirs = {}
        self.files = {}

with open("y22/r07.txt") as f:
    root = Dir('')
    bread = [root] # breadcrumb
    dirs = []
    for l in f.readlines():
        spl = l.strip().split(' ')
        if l[0] == '$': # cmd
            if spl[1] == 'cd':
                if spl[2] == '..':
                    bread.remove(bread[-1])
                else:
                    name = spl[2]
                    curr = Dir(name)
                    bread[-1].dirs[name] = curr
                    bread.append(curr)
                    dirs.append(curr)
        else: # list of files
            if spl[0] != 'dir':
                bread[-1].files[spl[1]] = int(spl[0])
    root.get_size()

# star 1
sm = 0
for d in dirs:
    if d.size <= 100000:
        sm += d.size
print(sm)

# star 2
todel = 30000000 - (70000000 - root.size)
mn = 999999999
for d in dirs:
    if d.size > todel:
        mn = min(mn, d.size)
print(mn)