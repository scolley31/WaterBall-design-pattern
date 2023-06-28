package CollisionHandle;

import Base.Fire;
import Base.Hero;
import Base.Sprite;

public class HeroWithFireHandler extends CollisionHandler {
    public HeroWithFireHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    public void doHandling(Sprite collision, Sprite collisionee) {
        Hero hero = (Hero) collision;
        Fire fire = (Fire) collisionee;
        hero.damaged(10);
        if (collision.getPosition() == hero.getPosition()) {
            hero.setPosition(fire.getPosition());
        }
        fire.removeFromWorld();
    }

    @Override
    public boolean collisionCondition(Sprite collision, Sprite collisionee) {
        return collision instanceof Hero && collisionee instanceof Fire;
    }
}
