package com.yangjun.c2h3.world;

public class WaterAndFireHandler extends CollisionHandler {

	WaterAndFireHandler(CollisionHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public void doHandling(Sprite s1, Sprite s2, World world) {
		world.removeSprite(s1);
		world.removeSprite(s2);
	}

	@Override
	public boolean match(String condition) {
		return "FW".equals(condition);
	}
}
