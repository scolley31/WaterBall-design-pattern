package Base;

public class Fire extends Sprite {

    public Fire(int position) {
        super(position);
        setSign();
    }

    private void setSign() {
        this.setSign("F");
    }
}
