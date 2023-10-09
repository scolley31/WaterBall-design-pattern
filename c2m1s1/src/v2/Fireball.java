package v2;

public class Fireball implements AttackType{
    public void attack(Hero attacker, Hero attacked) {
        System.out.printf("英雄 %s 對英雄 %s 使出火球攻擊！\n", attacker.getName(), attacked.getName());
        for (int i = 0; i < 3; i++) {
            attacked.damage(50);
        }
    }
}
