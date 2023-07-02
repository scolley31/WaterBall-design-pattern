package org.example.World;

abstract public class Sprite {

    protected String name;
    protected Coord coord;

    protected World world;

    public Sprite() {
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public World getWorld() {
        return world;
    }

    public Coord getCoord() {
        return coord;
    }

    public void move(Coord coord) {
        System.out.println("從 "+this.coord.x+ " 移動到 "+coord.x);
        this.coord = coord;
    }

}
