package OldUNO;

public class AIPlayer extends Player {
    @Override
    void nameHimself() {
        name = String.valueOf(this.hashCode());
        System.out.println("AI OldUNO.Player name himself, name is " + name);
    }

    @Override
    Card showCard() {
        Card showCard = null;
        for (Card card: getHand().getCardList()) {
            if (Card.isTheSameType(card, uno.getCardOnTable())){
                showCard = card;
            }
        }
        hand.getCardList().remove(showCard);
        System.out.println("ShowCard " +showCard);
        System.out.println("name " + name + " 手上還有 " + hand.size() + " 張牌");
        return showCard;
    }
}
