import hashlib

inp = 'iwrupvqb'

# star 1
n = 1
hsh = hashlib.md5((inp + str(n)).encode('utf-8')).hexdigest()
while hsh[:5] != '00000':
    n += 1
    hsh = hashlib.md5((inp + str(n)).encode('utf-8')).hexdigest()
print(n)

# star 2
n = 1
hsh = hashlib.md5((inp + str(n)).encode('utf-8')).hexdigest()
while hsh[:6] != '000000':
    n += 1
    hsh = hashlib.md5((inp + str(n)).encode('utf-8')).hexdigest()
print(n)