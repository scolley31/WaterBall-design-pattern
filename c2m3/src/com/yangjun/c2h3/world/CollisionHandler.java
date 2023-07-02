package com.yangjun.c2h3.world;

public abstract class CollisionHandler {
	CollisionHandler nextHandler;
	
	CollisionHandler(CollisionHandler nextHandler) {
		this.nextHandler = nextHandler;
	}
	
	public void handle(String collisionCondition, Sprite s1, Sprite s2, World world) {
		if (match(collisionCondition)) {
			doHandling(s1, s2, world);
		} else {
			nextHandler.handle(collisionCondition, s1, s2, world);
		}
	}
	
	public abstract void doHandling(Sprite s1, Sprite s2, World world);
	public abstract boolean match(String condition);
}
