package org.example.Handler;

import org.example.World.Hero;
import org.example.World.Sprite;
import org.example.World.Water;

public class HeroAndWaterHandler extends CollisionHandler {
    public HeroAndWaterHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    protected Boolean match(Sprite c1, Sprite c2) {
        return (c1 instanceof Hero && c2 instanceof Water) || (c1 instanceof Water && c2 instanceof Hero);
    }

    @Override
    protected void doHandle(Sprite c1, Sprite c2) {
        ((Hero) c1).addHP();
        world.removeSprite(c2);
    }

    @Override
    protected void collisionEffect(Hero c1, Sprite c2) {
        c1.move(c2.getCoord());
    }
}
