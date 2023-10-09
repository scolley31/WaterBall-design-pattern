package v2;

public class Waterball implements AttackType {

    public void attack(Hero attacker, Hero attacked) {
        System.out.printf("英雄 %s 對英雄 %s 使出水球攻擊！\n", attacker.getName(), attacked.getName());
        attacked.damage((int)(attacker.getHp() * 0.5));
    }
}
