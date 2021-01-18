from itertools import permutations
W = {
    'Dagger':(8,4,0),
    'Shortsword':(10,5,0),
    'Warhammer':(25,6,0),
    'Longsword':(40,7,0),
    'Greataxe':(74,8,0)
}
A = {
    'No Armor':(0,0,0),
    'Leather':(13,0,1),
    'Chainmail':(31,0,2),
    'Splintmail':(53,0,3),
    'Bandedmail':(75,0,4),
    'Platemail':(102,0,5)
}
R = {
    'Nothing 1':(0,0,0),
    'Nothing 2':(0,0,0),
    'Damage +1':(25,1,0),
    'Damage +2':(50,2,0),
    'Damage +3':(100,3,0),
    'Defense +1':(20,0,1),
    'Defense +2':(40,0,2),
    'Defense +3':(80,0,3)
}

packages = set()

def create_packages():
    ring_combos = list(permutations(R.keys(),2))
    for kw in W:
        weapon = W[kw]
        for ka in A:
            armor = A[ka]
            for combo in ring_combos:
                rings = []
                for kr in combo:
                    rings.append(R[kr]) 
                package = (
                    weapon[0]+armor[0]+rings[0][0]+rings[1][0],
                    weapon[1]+armor[1]+rings[0][1]+rings[1][1],
                    weapon[2]+armor[2]+rings[0][2]+rings[1][2]
                )
                packages.add(package)

    
test_player = (8,5,5)
test_boss = (12,7,2)
player = (100,0,0)
boss = (100,8,2)

def play_game(player, boss, package=(0,0,0)):
    player_hp = player[0]
    player_damage = max([1,(player[1] + package[1]) - boss[2]])
    boss_hp = boss[0]
    boss_damage = max([1, (boss[1] - (player[2] + package[2]))])
    turn = 0
    while player_hp > 0 and boss_hp > 0:
        if not turn%2:
            boss_hp -= player_damage
        else:
            player_hp -= boss_damage
        turn += 1
    return player_hp > 0

#play_game(test_player, test_boss)
create_packages()
winners = set()
losers = set()
for package in packages:
    if play_game(player, boss, package):
        winners.add(package[0])
    else:
        losers.add(package[0])

print("Part 1:", min(winners))
print("Part 2:", max(losers))
