import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest {


    @Test
    void test() {
        assertTrue(true);
    }

    @Test
    void whenShowdownStart_thenPlayerNameHimself() {
        Showdown showdown = new Showdown();
        showdown.initPlayers();

        for (Player p : showdown.getAllPlayer()) {
            assertTrue(!p.getName().isEmpty());
        }
    }

    @Test
    void whenNewDeck_thenDeckShuffled() {
        Deck deck = new Deck();
        deck.shuffle();
        assertTrue(deck.cards.size() == Deck.FULL_DECK_SIZE);
        assertTrue(deck.isShuffled());
    }

    @Test
    void whenDeckShuffled_thenShowdownDrawCardToPlayer() {
        Showdown showdown = new Showdown();
        showdown.start();
        for (Player p : showdown.getAllPlayer()) {
            assertTrue(p.getHands().size() == Player.PLAYER_CARDS_SIZE);
        }
    }

    @Test
    void whenEndGame_thenHaveAWinner() {
        Showdown showdown = new Showdown();
        showdown.start();
        assertTrue(showdown.getWinner() != null);
    }

    @Test
    void whenPlayerExchangeHandsWithTheOtherPlayer_thenTheHandsOfThePlayersShouldBeExchanged() {
        List<Card> p1Hands;
        Showdown showdown = new Showdown();
        showdown.initPlayers();
        showdown.drawing();

        Player p1 = showdown.getPlayers().get(0);
        p1Hands = p1.getHands();
        Player p2 = showdown.getPlayers().get(1);
        p1.exchangeHandWith(p2);

        assertEquals(p1Hands, p2.getHands());
        assertTrue(p1.isExchanged());
    }

    @Test
    void whenPlayerExchangeHandsWithTheOtherPlayerAfterThreeTurn_thenTheHandsOfThePlayersShouldBeRestore() {
        List<Card> p1Hands;
        Showdown showdown = new Showdown();
        showdown.initPlayers();
        showdown.drawing();

        Player p1 = showdown.getPlayers().get(0);
        p1Hands = p1.getHands();
        Player p2 = showdown.getPlayers().get(1);
        p1.exchangeHandWith(p2);
        assertEquals(p1Hands, p2.getHands());
        assertTrue(p1.isExchanged());

        p1.getExchangeHand().restoreHands();
        assertEquals(p1.getHands(), p1Hands);
    }
}
