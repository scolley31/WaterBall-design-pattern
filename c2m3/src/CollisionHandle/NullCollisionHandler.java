package CollisionHandle;

import Base.Sprite;

public class NullCollisionHandler extends CollisionHandler {

    public NullCollisionHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    public void doHandling(Sprite collision, Sprite collisionee) {
        System.out.println("There is no sprite on this position");
    }

    @Override
    public boolean collisionCondition(Sprite collision, Sprite collisionee) {
        return collision == null;
    }
}
