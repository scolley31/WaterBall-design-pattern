package org.example.World;

import org.example.CommandLine;
import org.example.Handler.CollisionHandler;

import java.util.*;

public class World implements CommandLine {

    private Coord[] coords;
    private Sprite[] sprites;

    private CollisionHandler collisionHandler;

    public World(CollisionHandler collisionHandler, Coord[] coords, Sprite... sprites) {
        this.collisionHandler = collisionHandler;
        collisionHandler.setWorld(this);
        this.coords = coords;
        this.sprites = sprites;

        for (Sprite sprite : sprites) {
            Random random = new Random();
            int index = random.nextInt(coords.length);
            sprite.setCoord(coords[index]);
        }

        showCoords();
        showSprites();

    }

    public void moveSprite(int x1, int x2) {

        if (Arrays.stream(coords).allMatch(coord -> coord.x != x1)) {
            System.out.println("沒有這個 " + x1 + " 這個位置");
            start();
            return;
        }
        if (Arrays.stream(coords).allMatch(coord -> coord.x != x2)) {
            System.out.println("沒有這個 " + x2 + " 這個位置");
            start();
            return;
        }

        Sprite spriteInX1 = Arrays.stream(sprites)
                .filter(sprite -> sprite.getCoord().x == x1)
                .findFirst()
                .orElse(null);

        if (spriteInX1 == null) {
            System.out.println("這個位置 " + x1 + " 沒有生命");
            start();
            return;
        }

        Sprite spriteInX2 = Arrays.stream(sprites)
                .filter(sprite -> sprite.getCoord().x == x2)
                .findFirst()
                .orElse(null);

        if (spriteInX2 == null) {
            System.out.println("這個位置 " + x2 + " 沒有生命");
            spriteInX1.move(coords[x2]);
            showSprites();
        } else {
            System.out.println("這個位置 " + x2 + " 有生命，確認碰撞處理。");
            collisionHandler.handle(spriteInX1, spriteInX2);
            showSprites();
        }

        start();
    }


    public void removeSprite(Sprite spriteToDelete) {
        System.out.println("刪除 " + spriteToDelete.name + " 在 " + spriteToDelete.coord.x);
        sprites = Arrays.stream(sprites)
                .filter(sprite -> sprite != spriteToDelete)
                .toArray(Sprite[]::new);
    }

    public void removeSprite(Sprite spriteToDelete1, Sprite spriteToDelete2) {
        System.out.println("刪除 " + spriteToDelete1.name + " 在 " + spriteToDelete1.coord.x);
        System.out.println("刪除 " + spriteToDelete2.name + " 在 " + spriteToDelete2.coord.x);
        sprites = Arrays.stream(sprites)
                .filter(sprite -> sprite != spriteToDelete1 && sprite != spriteToDelete2)
                .toArray(Sprite[]::new);
    }


    public void start() {
        System.out.print("請輸入你兩個位置，第一個位置: ");
        int x1 = inputIntByUser();
        System.out.print("請輸入你兩個位置，第二個位置: ");
        int x2 = inputIntByUser();
        moveSprite(x1, x2);
    }

    @Override
    public int inputIntByUser() {
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }

    private void showSprites() {
        System.out.println("英雄列表");
        for (Sprite sprite : this.sprites) {
            System.out.print(sprite.name + " ");
            System.out.print("在 " + sprite.coord.x + "  ");
        }
        System.out.println();
    }

    private void showCoords() {
        System.out.println("座標列表");
        for (Coord coord : this.coords) {
            System.out.print(coord.x + " ");
        }
        System.out.println();
    }
}
