package showDown;

import Base.Game;

public abstract class Player extends Base.Player<Card> {
    private int point = 0;
    private Game showDown;
    public Player(String name) {
        super(name);
    }
    public abstract void nameHimself();

    @Override
    public String toString() {
        return name;
    }

    public void gainPoint() {
        this.point++;
    }

    public Card takeTurn() {
        Card card =showCard(null);
        return card;
    }
    public int getPoint() {
        return point;
    }

    public void setShowDown(Game showDown) {
        this.showDown = showDown;
    }


}
