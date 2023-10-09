package v2;

public class Hero {
    private final AttackType attackType;
    private final String name;
    private int hp = 500;

    public Hero(String name, AttackType attackType) {
        this.name = name;
        this.attackType = attackType;
    }

    public void attack(Hero hero) {
        attackType.attack(this, hero);
    }

    public void damage(int value) {
        hp -= value;
        if(hp<0) {
            hp = 0;
        }
        System.out.printf("英雄 %s 受到 %d 點傷害，剩餘 %d 點生命值。\n", name, value, hp);
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public String getName() {
        return name;
    }
    public int getHp() {
        return hp;
    }
}
