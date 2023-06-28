package Base;

public class Water extends Sprite {


    public Water(int position) {
        super(position);
        setSign();
    }

    private void setSign() {
        this.setSign("W");
    }
}
