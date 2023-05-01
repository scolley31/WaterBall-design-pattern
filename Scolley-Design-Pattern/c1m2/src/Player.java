import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

abstract public class Player {

    protected String name;

    protected int point = 0;

    protected boolean alreadyExchangeHands = false;

    protected ShowDown showDown;

    protected Hand hand;

    protected Card showCard;

    protected Collection<Player> otherPlayers = new HashSet<>();

    protected Collection<Player> exchangee = new ArrayList<>();

    protected Collection<ExchangeHands> exchangeHands = new HashSet<>();

    public Player() {
        hand = new Hand();
    }

    public int getPoint() {
        return point;
    }

    public boolean isAlreadyExchangeHands() {
        return alreadyExchangeHands;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public ShowDown getShowDown() {
        return showDown;
    }

    public Collection<Player> getOtherPlayers() {
        return otherPlayers;
    }

    public Collection<Player> getExchangee() {
        return exchangee;
    }

    public Collection<ExchangeHands> getExchangeHands() {
        return exchangeHands;
    }

    public void addExchangee(Player player) {
        exchangee.add(player);
    }
    public void addExchangeHands(ExchangeHands exchangeHands1) {
        exchangeHands.add(exchangeHands1);
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void setShowDown(ShowDown showDown) {
        this.showDown = showDown;
    }

    abstract void nameHimself();
    abstract Card showCard();
    abstract void makeExchangeHandsDecision();


    public void addHandCard(Card card) {
        hand.addCard(card);
    }

    public void takeTurn() {
        showCard = showCard();
        if (showCard == null) throw new IllegalArgumentException("Player: "+getName()+" 沒牌了") ;
    }

    protected void exchangeHands(Player player) {

        addExchangee(player);
        player.addExchangee(this);
        ExchangeHands newExchangeHands = new ExchangeHands(this, player);
        exchangeHands.add(newExchangeHands);
        player.getExchangeHands().add(newExchangeHands);

        System.out.println("Player exchanger: "+this.getName()+" 與 Player exchangee: "+player.getName()+"的牌，交換");

        Collection<Card> exchangerHandCardListBefore = this.getHand().getCardList();
        exchangerHandCardListBefore.forEach(card ->
                System.out.println("Before Exchanger Name :"+this.getName()+" HandCard : Rank = " +card.getRank() + " Suit = " + card.getSuit())
        );

        Collection<Card> exchangeeHandCardListBefore = player.getHand().getCardList();
        exchangeeHandCardListBefore.forEach(card ->
                System.out.println("Before Exchangee Name :"+player.getName()+" HandCard : Rank = " +card.getRank() + " Suit = " + card.getSuit())
        );

        Hand exchangerHand = this.getHand();
        this.setHand(player.getHand());
        player.setHand(exchangerHand);

        Collection<Card> exchangerHandCardListAfter = this.getHand().getCardList();
        exchangerHandCardListAfter.forEach(card ->
                System.out.println("After Exchanger Name :"+this.getName()+" HandCard : Rank = " +card.getRank() + " Suit = " + card.getSuit())
        );

        Collection<Card> exchangeeHandCardListAfter = player.getHand().getCardList();
        exchangeeHandCardListAfter.forEach(card ->
                System.out.println("After Exchangee Name :"+player.getName()+" HandCard : Rank = " +card.getRank() + " Suit = " + card.getSuit())
        );
    }

    public void addPoint() {
        point++;
    }

}
