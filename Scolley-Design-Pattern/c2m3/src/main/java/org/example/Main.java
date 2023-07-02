package org.example;

import org.example.Handler.*;
import org.example.World.*;

public class Main {
    public static void main(String[] args) {
        CollisionHandler collisionHandler = new HeroAndWaterHandler(new HeroAndFireHandler(new WaterAndFireHandler(new SameTypeHandler(null))));
        Coord[] coords = Coord.fromSizes(30);
        World world = new World(collisionHandler, coords, new Hero(), new Water(), new Fire(), new Hero(), new Water(), new Fire(), new Hero(), new Water(), new Fire(), new Fire());
        world.start();
    }
}