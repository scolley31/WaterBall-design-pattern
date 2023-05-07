public class Coord {

    double x;
    double y;

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    static double calculateDistance(Coord coord1, Coord coord2) {
        return Math.sqrt( Math.pow((coord2.x - coord1.x), 2) + Math.pow((coord2.y - coord1.y), 2) );
    }

}
