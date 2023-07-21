package org.example.Handler;

import org.example.World.Sprite;
import org.example.World.World;

abstract public class CollisionHandler {

    private CollisionHandler next;
    protected World world;

    public void setWorld(World world) {
        this.world = world;
    }

    public CollisionHandler(CollisionHandler next) {
        this.next = next;
    }

    public void handle(Sprite c1, Sprite c2) {
        if (match(c1, c2)) {
            doHandle(c1, c2);
            move(c1, c2);
        } else if (match(c2, c1)) {
            doHandle(c2, c1);
            move(c1, c2);
        } else {
            next.setWorld(this.world);
            next.handle(c1, c2);
        }
    }

    protected void move(Sprite c1, Sprite c2) {}

    protected abstract Boolean match(Sprite c1, Sprite c2);

    protected abstract void doHandle(Sprite c1, Sprite c2);

}

