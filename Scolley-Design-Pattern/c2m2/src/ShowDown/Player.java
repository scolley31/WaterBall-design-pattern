package ShowDown;

import TemplateGame.AbstractPlayer;

abstract class Player extends AbstractPlayer<Card> {

    private int point = 0;
    protected Card showCard;

    public int getPoint() {
        return point;
    }

    public Card getShowCard() {
        return showCard;
    }

    public void gainPoint() {
        point++;
    }
}
