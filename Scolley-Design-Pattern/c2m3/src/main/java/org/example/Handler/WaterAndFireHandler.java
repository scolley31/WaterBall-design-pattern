package org.example.Handler;

import org.example.World.Fire;
import org.example.World.Sprite;
import org.example.World.Water;

public class WaterAndFireHandler extends CollisionHandler {
    public WaterAndFireHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    protected Boolean match(Sprite c1, Sprite c2) {
        return (c1 instanceof Fire && c2 instanceof Water) || (c1 instanceof Water && c2 instanceof Fire);
    }

    @Override
    protected void doHandle(Sprite c1, Sprite c2) {
        world.removeSprite(c1, c2);
    }
}
