package CollisionHandle;

import Base.Sprite;

public class NullCollisioneeHandler extends CollisionHandler {
    public NullCollisioneeHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    public void doHandling(Sprite collision, Sprite collisionee) {
        collision.setPosition(collisionee.getPosition());
        System.out.printf("There is no Sprite on this position,move Success! %s move to %s\n", collision.getSign(), collision.getPosition());
    }

    @Override
    public boolean collisionCondition(Sprite collision, Sprite collisionee) {
        return collisionee.getSign().equals("EmptySprite");
    }
}
