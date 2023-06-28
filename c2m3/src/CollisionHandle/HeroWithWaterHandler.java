package CollisionHandle;

import Base.Fire;
import Base.Hero;
import Base.Sprite;
import Base.Water;

public class HeroWithWaterHandler extends CollisionHandler {
    public HeroWithWaterHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    public void doHandling(Sprite collision, Sprite collisionee) {
        Hero hero = (Hero) collision;
        Water water = (Water) collisionee;
        hero.cure(10);
        if (collision.getPosition() == hero.getPosition()) {
            hero.setPosition(water.getPosition());
        }
        water.removeFromWorld();
    }

    @Override
    public boolean collisionCondition(Sprite collision, Sprite collisionee) {
        return collision instanceof Hero && collisionee instanceof Water;
    }
}
