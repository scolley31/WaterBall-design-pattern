package c2h3.world;

public abstract class Sprite {
	
	private int coordinate;

	public Sprite(int coordinate) {
		this.coordinate = coordinate;
	}
	
	public abstract String toString();
	
	public int getCoordinate() {
		return coordinate;
	}
}
