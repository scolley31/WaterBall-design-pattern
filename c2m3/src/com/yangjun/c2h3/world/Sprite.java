package com.yangjun.c2h3.world;

public abstract class Sprite {
	private int coordinate;
	private final char showChar;
	
	public Sprite(int coordinate, char showChar) {
		this.coordinate = coordinate;
		this.showChar = showChar;
	}
	
	public int getCoordinate() {
		return coordinate;
	}

	public char getShowChar() {
		return showChar;
	}
}
