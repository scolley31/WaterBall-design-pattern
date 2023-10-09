package v2;

public class Earth implements AttackType {
    public void attack(Hero attacker, Hero attacked){
        System.out.printf("英雄 %s 對英雄 %s 使出地震！\n", attacker.getName(), attacked.getName());
        for (int i = 0; i < 10; i++) {
            attacked.damage(20);
        }
    }
}
