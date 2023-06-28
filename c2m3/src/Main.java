import Base.*;
import CollisionHandle.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new World(new HeroWithFireHandler(new HeroWithWaterHandler(new NullCollisionHandler(new NullCollisioneeHandler(new SpriteWithSameTypeHandler(new WaterWithFireHandler(null))))))));
        game.start();
    }
}