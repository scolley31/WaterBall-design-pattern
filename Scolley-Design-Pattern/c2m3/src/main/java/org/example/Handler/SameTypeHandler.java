package org.example.Handler;

import org.example.World.Sprite;

public class SameTypeHandler extends CollisionHandler {
    public SameTypeHandler(CollisionHandler next) {
        super(next);
    }

    @Override
    protected Boolean match(Sprite c1, Sprite c2) {
        return c1.getClass().equals(c2.getClass());
    }

    @Override
    protected void doHandle(Sprite c1, Sprite c2) {
        System.out.println("移動失敗");
    }

}
