import hashlib

inp = 'ugkcyxxp'

# star 1
n = 0
res = ''
while len(res) < 8:
    hsh = hashlib.md5((inp + str(n)).encode('utf-8')).hexdigest()
    if hsh[:5] == '00000':
        res += hsh[5]
    n += 1
print(res)

# star 2
n = 0
res = '________'
while '_' in res:
    hsh = hashlib.md5((inp + str(n)).encode('utf-8')).hexdigest()
    if hsh[:5] == '00000' and hsh[5].isdigit():
        idx = int(hsh[5])
        if idx < 8 and res[idx]== '_':
            res = res[:idx] + hsh[6] + res[idx+1:]
    n += 1
print(res)