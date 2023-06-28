package CollisionHandle;

import Base.Sprite;

public abstract class CollisionHandler {
    protected CollisionHandler next;

    public CollisionHandler(CollisionHandler next) {
        this.next = next;
    }

    public void handleCollision(Sprite collision, Sprite collisionee) {
        if (collisionCondition(collision, collisionee)) {
            doHandling(collision, collisionee);
        } else if (collisionCondition(collisionee, collision)) {
            doHandling(collisionee, collision);
        } else
            next.handleCollision(collision, collisionee);
    }

    protected abstract void doHandling(Sprite collision, Sprite collisionee);

    protected abstract boolean collisionCondition(Sprite collision, Sprite collisionee);

}
