package com.yangjun.c2h3.world;

public class Hero extends Sprite {
	private int hp;
	
	public Hero(int coordinate, int hp) {
		super(coordinate, 'H');
		this.hp = hp;
	}

	public int getHp() {
		return hp;
	}

	public void increaseHp(int count) {
		hp += count;
		System.out.printf("The hero gains %d health, current hp is %d. \n", count, hp);
	}
	
	public void decreaseHp(int count) {
		hp -= count;
		System.out.printf("The hero loses %d points of life, current hp is %d. \n", count, hp);
	}

	public boolean isDead() {
	    boolean dead = hp <= 0;
	    if (dead) {
	        System.out.println("Hero died.");
	    }
	    return dead;
	}
}
