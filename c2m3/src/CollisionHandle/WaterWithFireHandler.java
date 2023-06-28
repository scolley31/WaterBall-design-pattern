package CollisionHandle;

import Base.Fire;
import Base.Sprite;
import Base.Water;

public class WaterWithFireHandler extends CollisionHandler {
    public WaterWithFireHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    public void doHandling(Sprite collision, Sprite collisionee) {
        Water water = (Water) collision;
        Fire fire = (Fire) collisionee;
        water.removeFromWorld();
        fire.removeFromWorld();
        System.out.printf("Water and Fire are both removed\n");
    }

    @Override
    public boolean collisionCondition(Sprite collision, Sprite collisionee) {
        return collision instanceof Water && collisionee instanceof Fire;
    }
}
