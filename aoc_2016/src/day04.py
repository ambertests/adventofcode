from collections import Counter

rooms = [line.strip() for line in open('input/day04.txt').readlines()]
alphabet = 'abcdefghijklmnopqrstuvwxyz'

def decrypt_name(name, id):
    decrypt = []
    for c in name:
        if c == '-': decrypt.append(' ')
        else:
            i = alphabet.index(c)
            for _ in range(id):
                if i == len(alphabet) - 1:
                    i = 0
                else:
                    i += 1
            decrypt.append(alphabet[i])
    return ''.join(decrypt)


def validate_rooms(rooms):
    ids = []
    np_storage = 0
    for room in rooms:
        tocheck = []
        r1, r2 = room.split('[')
        checksum = r2[:-1]
        r3 = r1.split('-')
        id = int(r3[-1])
        name = '-'.join(r3[:-1])
        counter = Counter(name.replace('-', ''))
        count_lookup = {}
        for c in counter.most_common():
            char = c[0]
            num = c[1]
            if num not in count_lookup:
                count_lookup[num] = [char]
            else:
                count_lookup[num].append(char)
        
        keys = list(count_lookup.keys())
        keys.sort(reverse=True)
        for k in keys:
            chars = count_lookup[k]
            chars.sort()
            tocheck.extend(chars)
            if len(tocheck) >= len(checksum):
                break
        if ''.join(tocheck[:5]) == checksum:
            decrypted = decrypt_name(name, id)
            if 'north' in decrypted:
                print(decrypted, id)
                np_storage = id
            ids.append(id)
    return sum(ids), np_storage

checksum, storage = validate_rooms(rooms)       
print("Part 1:", checksum)
print("Part 2:", storage)