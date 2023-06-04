package UNO;

import TemplateGame.Game;

public class AI extends Player{
    @Override
    protected void nameHimself() {
        name = String.valueOf(this.hashCode());
        System.out.println("AI OldUNO.Player name himself, name is " + name);
    }

    @Override
    public Card showCard() {
        Card showCard = null;
        for (Card card: getHands().getHandCards()) {
            if (Card.isTheSameType(card, ((UNO) game).getCardOnTable())){
                showCard = card;
            }
        }
        getHands().getHandCards().remove(showCard);
        System.out.println("ShowCard " +showCard);
        System.out.println("name " + name + " 手上還有 " + getHands().size() + " 張牌");
        return showCard;
    }
}
