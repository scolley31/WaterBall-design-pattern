package org.example.Handler;

import org.example.World.Fire;
import org.example.World.Hero;
import org.example.World.Sprite;

public class HeroAndFireHandler extends CollisionHandler {
    public HeroAndFireHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    protected Boolean match(Sprite c1, Sprite c2) {
        return (c1 instanceof Hero && c2 instanceof Fire) || (c1 instanceof Fire && c2 instanceof Hero);
    }

    @Override
    protected void doHandle(Sprite c1, Sprite c2) {
        ((Hero) c1).reduceHP();
        world.removeSprite(c2);
    }

    @Override
    protected void collisionEffect(Hero c1, Sprite c2) {
        c1.move(c2.getCoord());
    }
}
