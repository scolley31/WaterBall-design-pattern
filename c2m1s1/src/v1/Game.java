package v1;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.swap;

public class Game {
    private List<Hero> heros;

    public Game(Hero hero1, Hero hero2) {
        heros = asList(hero1, hero2);
    }

    public void start() {
        nextTurn();
    }

    private void nextTurn() {
        Hero attacker = heros.get(0);
        Hero attacked = heros.get(1);

        attacker.attack(attacked);
        swap(heros, 0, 1);
        if (attacked.isDead()) {
            System.out.printf( "英雄 %s 死亡, 英雄 %s 獲勝！\n", attacked.getName(), attacker.getName());
        } else {
            nextTurn();
        }
    }
}
