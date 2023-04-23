import java.util.Collection;

public class ExchangeHands {

    Player exchanger;
    Player exchangee;

    public ExchangeHands(Player exchanger, Player exchangee) {
        this.exchanger = exchanger;
        this.exchangee = exchangee;
    }

    int turnCountDown = 3;

    public void turnDown() {

        System.out.println("Player exchanger: "+exchanger.getName()+" 與 Player exchangee: "+exchangee.getName()+"的牌，在 "+turnCountDown+" 回合後換回來");

        if (turnCountDown == 0) exchangeHandsBack();
        turnCountDown--;
    }

    private void exchangeHandsBack() {
        System.out.println("Player exchanger: "+exchanger.getName()+" 與 Player exchangee: "+exchangee.getName()+"的牌，換回來");

        Collection<Card> exchangerHandCardListBefore = exchanger.getHand().getCardList();
        exchangerHandCardListBefore.forEach(card ->
                System.out.println("Exchanger Name :"+exchanger.getName()+" HandCard : Rank = " +card.getRank() + " Suit = " + card.getSuit())
        );

        Collection<Card> exchangeeHandCardListBefore = exchangee.getHand().getCardList();
        exchangeeHandCardListBefore.forEach(card ->
                System.out.println("Exchangee Name :"+exchanger.getName()+" HandCard : Rank = " +card.getRank() + " Suit = " + card.getSuit())
        );

        Hand exchangerHand = exchanger.getHand();
        exchanger.setHand(exchangee.getHand());
        exchangee.setHand(exchangerHand);

        Collection<Card> exchangerHandCardListAfter = exchanger.getHand().getCardList();
        exchangerHandCardListAfter.forEach(card ->
                System.out.println("Exchanger Name :"+exchanger.getName()+" HandCard : Rank = " +card.getRank() + " Suit = " + card.getSuit())
        );

        Collection<Card> exchangeeHandCardListAfter = exchangee.getHand().getCardList();
        exchangeeHandCardListAfter.forEach(card ->
                System.out.println("Exchangee Name :"+exchanger.getName()+" HandCard : Rank = " +card.getRank() + " Suit = " + card.getSuit())
        );
    }


}
