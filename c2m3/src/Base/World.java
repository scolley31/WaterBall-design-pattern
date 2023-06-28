package Base;

import CollisionHandle.CollisionHandler;
import CollisionHandle.NullCollisionHandler;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Sprite> sprites = new ArrayList<>(30);
    private CollisionHandler collisionHandler;

    public World(CollisionHandler collisionHandler) {
        this.sprites = initSprites();
        sprites.forEach(sprite -> sprite.setWorld(this));
        this.collisionHandler = collisionHandler;
    }

    public void move(int From, int To) {
        Sprite collision = sprites.stream()
                .filter(sprite -> sprite.getPosition() == From)
                .findFirst()
                .orElse(null);
        Sprite collisionee = sprites.stream()
                .filter(sprite -> sprite.getPosition() == To)
                .findFirst()
                .orElse(new Sprite(To));
        collisionHandler.handleCollision(collision, collisionee);
    }

    public void removeSprite(Sprite sprite) {
        this.sprites.remove(sprite);
    }

    public static List<Sprite> initSprites() {
        List<Sprite> sprites = new ArrayList<>(30);
        for (int i = 0; i < 5; i++) {
            sprites.add(new Fire(i));
        }
        for (int i = 5; i < 10; i++) {
            sprites.add(new Water(i));
        }
        for (int i = 10; i < 15; i++) {
            sprites.add(new Hero(i));
        }
        shufflePosition(sprites);
        return sprites;
    }
    public static void shufflePosition(List<Sprite> sprites){
        List<Integer> positions = new ArrayList<>();
        while (true){
            int position = (int) (Math.random() * 30);
            if (positions.contains(position)){
                continue;
            }
            positions.add(position);
            if (positions.size() == 15){
                break;
            }
        }
        for (int i = 0; i < 15; i++) {
            sprites.get(i).setPosition(positions.get(i));
        }
    }
}
