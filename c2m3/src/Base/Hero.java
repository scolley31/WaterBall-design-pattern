package Base;

import static java.lang.Math.max;

public class Hero extends Sprite {
    private int HP = 30;

    public Hero(int position) {
        super(position);
        setSign();
    }

    public void setHP(int HP) {
        this.HP = max(0, HP);
    }

    public int getHP() {
        return HP;
    }

    public boolean isDead() {
        return HP <= 0;
    }

    private void setSign() {
        this.setSign("H");
    }

    public void damaged(int attackPower) {
        this.setHP(this.getHP() - attackPower);
        System.out.println("The hero lost " + attackPower + " hp. Now his hp remains " + this.getHP() + ".");
        if (isDead()) {
            System.out.printf("The hero %s dies.", this.getSign());
            removeFromWorld();
        }
    }

    public void cure(int cure) {
        this.setHP(this.getHP() + cure);
        System.out.printf("The hero %s restores %d hp. Now his hp remains %d.\n", this.getSign(), cure, this.getHP());
    }
}
