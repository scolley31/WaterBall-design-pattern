package uno;

import Base.Deck;
import Base.Game;

import java.util.*;

import static java.util.spi.ToolProvider.findFirst;

/*
從牌堆中翻出第一張牌到檯面上。
由 P1 開始，出牌順序為 P1 → P2 → P3 → P4 → P1 以此類推。
玩家出的牌必須與檯面上最新的牌的顏色一樣，或是數字一樣。出完的牌就會成為檯面上最新的牌。
最快出完手中牌的人為遊戲的贏家。
如果玩家沒有任何可出的牌，玩家就必須從牌堆中抽一張牌，如果此時牌堆空了，則會先把檯面上除了最新的牌以外的牌放回牌堆中進行洗牌。
 */
public class Uno extends Game<Player, Card> {
    private Deck deck;
    private Deck discardDeck = new uno.Deck();
    private Card TopCard;

    protected Uno(uno.Deck deck, Set<uno.Player> player) {
        super(player, deck);
        this.deck = deck;
        this.players = player;
    }

    protected void showWinner() {
        Optional<Player> winner = players.stream()
                .filter(player -> player.getHand().getHandSize() == 0)
                .findFirst();
        System.out.println(winner.get().getName() + " is the winner!");
    }

    @Override
    protected void takeTurn(Player turnPlayer) {
        if (TopCard == null) {
            TopCard = (Card) deck.drawCard();
            System.out.println("First Card is " + TopCard.getColor() + ", " + TopCard.getNumber());
            discardDeck.addCard(TopCard);
        }
        if (isDeckEmpty()) {
            refreshedDeck(discardDeck);
        }
        if (!turnPlayer.haveValidCard()) {
            Card card = (Card) deck.drawCard();
            turnPlayer.addHandCard(card);
            System.out.printf("%s draw a card %s, %s%n", turnPlayer.getName(), card.getColor(), card.getNumber());
        } else
            TopCard = turnPlayer.takeTurn(TopCard);
            discardDeck.addCard(TopCard);
    }

    @Override
    protected int initializeHandsSize() {
        return 5;
    }

    private void refreshedDeck(Deck discardDeck) {
        discardDeck.removeCard(TopCard);
        deck.addCards(discardDeck.getCards());
        deck.shuffle();
        discardDeck.clear();
    }

    @Override
    protected boolean isGameOver() {
        return players.stream().anyMatch(player -> player.getHand().isEmpty());
    }

    boolean isDeckEmpty() {
        return deck.getCards().size() == 0;
    }

    public Card getTopCard() {
        return TopCard;
    }
}
