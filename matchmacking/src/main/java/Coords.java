public class Coords {

    private int x;
    private int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double calcDistance(Coords coord) {
        return Math.sqrt((coord.getX()-this.getX())*(coord.getX()-this.getX()) + (coord.getY()-this.getY())*(coord.getY()-this.getY()));
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
