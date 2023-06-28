package CollisionHandle;

import Base.Sprite;

public class SpriteWithSameTypeHandler extends CollisionHandler {
    public SpriteWithSameTypeHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    public void doHandling(Sprite collision, Sprite collisionee) {
        System.out.printf("There is a Sprite with same type on this position, %s move failed\n", collision.getSign());
    }

    @Override
    public boolean collisionCondition(Sprite collision, Sprite collisionee) {
        return collision.getClass() == collisionee.getClass();
    }
}
