package v1;

public class Hero {
    private final AttackType attackType;
    private final String name;
    private int hp = 500;

    public Hero(String name, AttackType attackType) {
        this.name = name;
        this.attackType = attackType;
    }

    public void attack(Hero hero) {
        if (attackType.equals(AttackType.WaterBall)) {
            hero.damage((int) (hp * 0.5));
        } else if (attackType.equals(AttackType.FireBall)) {
            for (int i = 0; i < 3; i++) {
                hero.damage(50);
            }
        } else if (attackType.equals(AttackType.Earth)) {
            for (int i = 0; i < 10; i++) {
                hero.damage(20);
            }
        } else {
            throw new RuntimeException("v1.Hero must have one type of attackType.");
        }
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

    public enum AttackType {
        WaterBall, FireBall, Earth;

    }
}
