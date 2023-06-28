package Base;

public class Sprite {
    private int position;
    private World world;
    private String sign;

    public Sprite(int position) {
        this.position = position;
        this.sign = "EmptySprite";
    }
    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public void removeFromWorld() {
        System.out.println("The sprite " + this.getSign() + " is removed from the world.");
        world.removeSprite(this);
    }

}
