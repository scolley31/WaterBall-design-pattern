package ShowDown;

import TemplateGame.AbstractPlayer;

abstract class Player extends AbstractPlayer<Card> {

    private int point = 0;

    public int getPoint() {
        return point;
    }

    public void gainPoint() {
        point++;
    }
}
