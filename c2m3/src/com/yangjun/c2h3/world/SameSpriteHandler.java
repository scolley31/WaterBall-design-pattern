package com.yangjun.c2h3.world;

public class SameSpriteHandler extends CollisionHandler {

	SameSpriteHandler(CollisionHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public void doHandling(Sprite s1, Sprite s2, World world) {
		System.out.println("Same sprite, move failed.");
		return;
	}
	
	@Override
	public boolean match(String condition) {
		return condition.charAt(0) == condition.charAt(1);
	}

}
