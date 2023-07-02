package org.example.World;

public class Coord {
    int x;

    public Coord(int x) {
        this.x = x;
    }

    public static Coord[] fromSizes(int size) {
        Coord[] coords = new Coord[size];
        for (int i = 0; i < size; i++) {
            coords[i] = new Coord(i);
        }
        return coords;
    }
}
