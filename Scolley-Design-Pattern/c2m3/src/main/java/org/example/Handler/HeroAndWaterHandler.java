package org.example.Handler;

import org.example.World.Fire;
import org.example.World.Hero;
import org.example.World.Sprite;
import org.example.World.Water;

public class HeroAndWaterHandler extends CollisionHandler {
    public HeroAndWaterHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    protected Boolean match(Sprite c1, Sprite c2) {
        return (c1 instanceof Hero && c2 instanceof Water);
    }

    @Override
    protected void doHandle(Sprite c1, Sprite c2) {
        Hero hero = (Hero) c1;
        Water water = (Water) c2;
        hero.addHP();
        world.removeSprite(water);
    }

    @Override
    protected void move(Sprite c1, Sprite c2) {
        super.move(c1, c2);
        if (c1 instanceof Hero) {
            System.out.println("Hero 移動成功");
            c1.move(c2.getCoord());
        }
    }
}
