package UNO;

import TemplateGame.AbstractPlayer;
import TemplateGame.Game;

public class UNO extends Game<Card> {

    private Card cardOnTable;

    public UNO(Player player1, Player player2, Player player3, Player player4, int originHandCardNumber) {
        super(player1, player2, player3, player4, originHandCardNumber);
    }

    @Override
    protected void turnEnd() {

    }

    @Override
    protected void addCardsToDeck() {
        for (Color color : Color.values()) {
            for (Number number : Number.values()) {
                Card card = new Card(color, number);
                deck.addCard(card);
            }
        }
        deck.getCardStack().forEach( card ->
                System.out.println("Card = "+card)
        );
        System.out.println("UNO.Deck already init");
    }

    public Card getCardOnTable() {
        return cardOnTable;
    }

    @Override
    protected void needToDoBeforeGame() {
        cardOnTable = deck.drawCard();
        System.out.println("cardOnTable = "+cardOnTable);
    }

    @Override
    protected void takeTurn(AbstractPlayer<Card> player) {
        System.out.println("cardOnTable = "+cardOnTable);
        if (canShowCard(player)) {
            alreadyShowCardStack.add(cardOnTable);
            cardOnTable = player.showCard();
        } else {
            if (deck.cardStackNumber() == 0) {
                deck.addCard(alreadyShowCardStack);
                deck.shuffle();
                System.out.println("重新洗牌");
            }
            player.addHandCard(deck.drawCard());
            System.out.println("name " + player.getName() + " 手上還有 " + player.getHands().size() + " 張牌");
        }
    }

    @Override
    protected boolean isGameOver() {
        System.out.println("isGameOver = "+players.stream().anyMatch(player -> player.getHands().getHandCards().size() == 0));
        return players.stream().anyMatch(player -> player.getHands().getHandCards().size() == 0);
    }

    private boolean canShowCard(AbstractPlayer<Card> player) {
        for (Card card: player.getHands().getHandCards()) {
            if (Card.isTheSameType(card, cardOnTable)) return true;
        }
        return false;
    }

    @Override
    protected void showWinner() {
        winner = players.stream()
                .filter(player -> player.getHands().getHandCards().size() == 0)
                .findFirst()
                .orElse(null);
        assert winner != null;
        System.out.println("Winner's name " + winner.getName());
    }
}
