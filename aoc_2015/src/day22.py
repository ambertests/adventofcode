class Spell:
    def __init__(self, mana, effect, timer):
        self.mana = mana
        self.effect = effect
        self.timer = timer

S = {
    'Magic Missle': Spell(53, 'd+4', 0),
    'Drain': Spell(73, 'd+2:hp+2', 0),
    'Shield': Spell(113, 'a+7', 6),
    'Poison': Spell(173, 'd+3', 6),
    'Recharge': Spell(229, 'm+101', 5)
}

class Game:
    def __init__(self, hp, mana, boss_hp, boss_damage, hard_mode=False):
        self.hp = hp
        self.mana = mana
        self.armor = 0
        self.boss_hp = boss_hp
        self.boss_damage = boss_damage
        self.mana_used = 0
        self.spells_cast = []
        self.timers = { i : 0 for i in S.keys() }
        self.player_turn = True
        self.hard_mode = hard_mode

    def copy(self):
        c = Game(self.hp, self.mana, self.boss_hp, self.boss_damage)
        c.armor = self.armor
        c.mana_used = self.mana_used
        c.spells_cast = self.spells_cast.copy()
        c.timers = self.timers.copy()
        c.player_turn = self.player_turn
        c.hard_mode = self.hard_mode
        return c

    def update_timers(self):
        for t in self.timers:
            if self.timers[t] > 0:
                if t == 'Poison':
                    self.boss_hp -= 3
                elif t == 'Shield':
                    self.armor = 7
                elif t == 'Recharge':
                    self.mana += 101
                self.timers[t] -= 1
            elif t == 'Shield':
                self.armor = 0

    def dump(self):
        print('------------------')
        print('Player has %d hp, %d mana, and %d armor' % (self.hp, self.mana, self.armor))
        print('Boss has %d hp, %d damage' % (self.boss_hp, self.boss_damage))
        print('Spells cast:', self.spells_cast)
        print('Spell timers:', self.timers)
        print('Player turn:', self.player_turn)
        print('------------------')

    def can_cast(self, spell):
        can = self.player_turn and self.hp > 0 \
            and S[spell].mana < self.mana and (self.timers[spell] <= 0)
        return can
            
    def cast(self, spell):
        if self.hard_mode:
            self.hp -= 1
        if self.hp:
            self.mana -= S[spell].mana
            self.mana_used += S[spell].mana
            self.spells_cast.append(spell)
            self.timers[spell] = S[spell].timer
            if not S[spell].timer:
                if spell == 'Magic Missle':
                    self.boss_hp -= 4
                elif spell == 'Drain':
                    self.boss_hp -= 2
                    self.hp += 2
        self.player_turn = False

    def boss_turn(self):
        if self.boss_hp > 0:
            damage = self.boss_damage - self.armor
            self.hp -= damage
        self.player_turn = True

    def is_over(self):
        over = False
        if self.boss_hp <= 0:
            self.player_win = True
            over = True
        elif self.hp <= 0:
            self.player_win = False
            over = True
        elif self.player_turn and self.mana < min([S[s].mana for s in S]):
            self.player_win = False
            over = True
        return over



player = (50, 500)
boss = (71, 10)

winners = set()
losers = set()

def play_all_games(game:Game):
    if game.is_over():
        if game.player_win:
            winners.add(game.mana_used)
    else:
        new_game = game.copy()
        new_game.update_timers()
        if not new_game.player_turn:
            new_game.boss_turn()
            play_all_games(new_game)
        else:
            for s in S:
                if new_game.can_cast(s):
                    new_new = new_game.copy()
                    new_new.cast(s)
                    play_all_games(new_new)
        
def solve():
    hp, mana = player
    boss_hp, boss_damage = boss
    play_all_games(Game(hp, mana, boss_hp, boss_damage))
    print('Part 1:', min(winners))
    winners.clear()
    play_all_games(Game(hp, mana, boss_hp, boss_damage, True))
    print('Part 2:', min(winners))

solve()

# test_player = (10, 250)
# test_boss1 = (13, 8)
# test_boss2 = (14, 8)
# test_seq1 = ['Poison', 'Magic Missle']
# test_seq2 = ['Recharge', 'Shield', 'Drain', 'Poison', 'Magic Missle']

# def play_test_game(player, boss, spells):
#     hp, mana = player
#     boss_hp, boss_damage = boss
#     game = Game(hp, mana, boss_hp, boss_damage)
#     game.dump()
#     while not game.is_over():
#         game.update_timers()
#         if game.player_turn:
#             spell = spells.pop(0)
#             if game.can_cast(spell):
#                 game.cast(spell)
#         else:
#             game.boss_turn()
#         game.dump()

#     return game.player_win, game.mana_used


# # print(play_test_game(test_player, test_boss1, test_seq1))
# # print(play_test_game(test_player, test_boss2, test_seq2))