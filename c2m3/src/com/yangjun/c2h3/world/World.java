package com.yangjun.c2h3.world;

import static com.yangjun.c2h3.world.Util.*;

import java.util.Random;
import java.util.Scanner;

public class World {
	private static final Scanner in = new Scanner(System.in);
    private Sprite[] world;
    private CollisionHandler collisionHandler;

    public World(int size) {
        world = new Sprite[size];
        initWorld();
    }
    
    private void initWorld() {
    	collisionHandler = new SameSpriteHandler(new HeroAndFireHandler(new WaterAndFireHandler(new HeroAndWaterHandler(null))));
    	addRandomSpritesToWorld();
    }

	public static void main(String[] args) {
		World newWorld = new World(30);
		
        while (true) {
        	newWorld.printWorld();
            System.out.printf("Please enter two numbers separated by blanks: \n");
            String input = in.nextLine();
            String[] moveNum = input.trim().split("\\s+");

            if (moveNum.length == 2 && isNumeric(moveNum[0]) && isNumeric(moveNum[1])) {
                int num1 = Integer.parseInt(moveNum[0]);
                int num2 = Integer.parseInt(moveNum[1]);
                newWorld.moveSprite(num1, num2);
            } else {
                System.out.println("Invalid input. Please try again. \n");
            }
        }

	}

	public void moveSprite(int num1, int num2) {
        Sprite s1 = world[num1];
        Sprite s2 = world[num2];
        
        if (s1 == null) {
            System.out.printf("Position %d is empty. \n", num1);
            return;
        }
        
        if (s2 != null) {
        	String collisionCondition = getCollisionCondition(s1, s2);
            collisionHandler.handle(collisionCondition, s1, s2, this);
        }
        
        if (world[num2] == null) {
            world[num2] = s1;
            world[num1] = null;
        }
	}

	public void removeSprite(Sprite sprite) {
		world[sprite.getCoordinate()] = null;
	}
	
    public void printWorld() {
		StringBuilder spriteRepresentation = new StringBuilder();
		StringBuilder numberRepresentation = new StringBuilder();
		for (int i = 0; i < world.length; i++) {
			numberRepresentation.append("(").append(i).append(")" );
	        int paddingLength = i >= 10 ? 4 : 3;
			spriteRepresentation.append(centerPadding(world[i] == null ? "_" : String.valueOf(world[i].getShowChar()), paddingLength));
		}
		System.out.println(spriteRepresentation.toString());
		System.out.println(numberRepresentation.toString());
    }
    
    
    public void addRandomSpritesToWorld() {
        Random random = new Random();
        int spriteCount = 0;
        while (spriteCount < 10) {
            int randomIndex = random.nextInt(world.length);
            if (world[randomIndex] == null) {
                int randomSpriteType = random.nextInt(3);
                switch (randomSpriteType) {
                    case 0:
                        world[randomIndex] = new Fire(randomIndex);
                        break;
                    case 1:
                        world[randomIndex] = new Water(randomIndex);
                        break;
                    case 2:
                        world[randomIndex] = new Hero(randomIndex, 30);
                        break;
                }
                spriteCount++;
            }
        }
    }
}
