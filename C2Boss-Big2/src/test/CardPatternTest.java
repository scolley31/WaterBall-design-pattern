package test;

import Base.Card;
import Base.CardPattern;
import Base.CardPatternType.*;
import CardPatternHandle.cardPatternHandler.FullHouseHandler;
import CardPatternHandle.cardPatternHandler.PairHandler;
import CardPatternHandle.cardPatternHandler.SingleHandler;
import CardPatternHandle.cardPatternHandler.StraightHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardPatternTest {
    private CardPattern single;
    private CardPattern pair;
    private CardPattern fullHouse;
    private CardPattern straight;

    @BeforeEach
    public void initCardPattern() {
        single = new Single(Arrays.asList(new Card(Card.Rank.THREE, Card.Suit.CLUB)));
        pair = new Pair(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND)));
        fullHouse = new FullHouse(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND)));
        straight = new Straight(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND),
                new Card(Card.Rank.FIVE, Card.Suit.CLUB),
                new Card(Card.Rank.SIX, Card.Suit.CLUB),
                new Card(Card.Rank.SEVEN, Card.Suit.CLUB)
        ));
    }

    @Test
    public void containsClubThreeTrueTest() {
        assertTrue(single.containsClub3());
        assertTrue(pair.containsClub3());
        assertTrue(fullHouse.containsClub3());
        assertTrue(straight.containsClub3());
    }

    @Test
    public void containsClubThreeFalseTest() {
        Single single = new Single(Arrays.asList(new Card(Card.Rank.FOUR, Card.Suit.CLUB)));

        Pair pair = new Pair(Arrays.asList(
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND)));

        FullHouse fullHouse = new FullHouse(Arrays.asList(
                new Card(Card.Rank.FIVE, Card.Suit.CLUB),
                new Card(Card.Rank.FIVE, Card.Suit.DIAMOND),
                new Card(Card.Rank.FIVE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND)));
        Straight straight = new Straight(Arrays.asList(
                new Card(Card.Rank.EIGHT, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND),
                new Card(Card.Rank.FIVE, Card.Suit.CLUB),
                new Card(Card.Rank.SIX, Card.Suit.CLUB),
                new Card(Card.Rank.SEVEN, Card.Suit.CLUB)
        ));
        assertFalse(single.containsClub3());
        assertFalse(pair.containsClub3());
        assertFalse(fullHouse.containsClub3());
        assertFalse(straight.containsClub3());
    }

    @Test
    public void cardsConvertToSingleTest() {
        List<Card> club3 = Arrays.asList(new Card(Card.Rank.THREE, Card.Suit.CLUB));
        List<Card> spade2 = Arrays.asList(new Card(Card.Rank.TWO, Card.Suit.SPADE));
        assertEquals(new Single(club3), new SingleHandler(null).convertCardPattern(club3));
        assertEquals(new Single(spade2), new SingleHandler(null).convertCardPattern(spade2));
    }

    @Test
    public void cardsConvertToPairTest() {
        List<Card> rightCards = Arrays.asList(
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND));

        List<Card> wrongCards = Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND));

        assertEquals(new Pair(rightCards), new PairHandler(null).convertCardPattern(rightCards));
        assertEquals(null, new PairHandler(null).convertCardPattern(wrongCards));
    }

    @Test
    public void rightCardsConvertToFullHouseTest() {
        List<Card> normalFullHouse1 = Arrays.asList(
                new Card(Card.Rank.FIVE, Card.Suit.CLUB),
                new Card(Card.Rank.FIVE, Card.Suit.DIAMOND),
                new Card(Card.Rank.FIVE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND));

        List<Card> normalFullHouse2 = Arrays.asList(
                new Card(Card.Rank.KING, Card.Suit.CLUB),
                new Card(Card.Rank.KING, Card.Suit.DIAMOND),
                new Card(Card.Rank.KING, Card.Suit.HEART),
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND)
        );

        assertEquals(new FullHouse(Arrays.asList(
                new Card(Card.Rank.FIVE, Card.Suit.CLUB),
                new Card(Card.Rank.FIVE, Card.Suit.DIAMOND),
                new Card(Card.Rank.FIVE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND)
        )), new FullHouseHandler(null).convertCardPattern(normalFullHouse1));

        assertEquals(new FullHouse(Arrays.asList(
                new Card(Card.Rank.KING, Card.Suit.CLUB),
                new Card(Card.Rank.KING, Card.Suit.DIAMOND),
                new Card(Card.Rank.KING, Card.Suit.HEART),
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND)
        )), new FullHouseHandler(null).convertCardPattern(normalFullHouse2));
    }

    @Test
    public void wrongCardsConvertToFullHouseTest() {
        List<Card> wrongFullHouse = Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FIVE, Card.Suit.DIAMOND));

        List<Card> wrongFullHouse2 = Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.SIX, Card.Suit.DIAMOND),
                new Card(Card.Rank.SEVEN, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FIVE, Card.Suit.DIAMOND));

        List<Card> wrongFullHouse3 = Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.SPADE),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.SPADE),
                new Card(Card.Rank.SIX, Card.Suit.HEART)
        );

        assertEquals(null, new FullHouseHandler(null).convertCardPattern(wrongFullHouse));

        assertEquals(null, new FullHouseHandler(null).convertCardPattern(wrongFullHouse2));

        assertEquals(null, new FullHouseHandler(null).convertCardPattern(wrongFullHouse3));
    }

    @Test
    public void rightCardsToConvertToStraightTest() {
        List<Card> eight910JQ = Arrays.asList(
                new Card(Card.Rank.EIGHT, Card.Suit.CLUB),
                new Card(Card.Rank.NINE, Card.Suit.SPADE),
                new Card(Card.Rank.TEN, Card.Suit.CLUB),
                new Card(Card.Rank.JACK, Card.Suit.SPADE),
                new Card(Card.Rank.QUEEN, Card.Suit.CLUB)
        );
        List<Card> JQK12 = Arrays.asList(
                new Card(Card.Rank.JACK, Card.Suit.SPADE),
                new Card(Card.Rank.QUEEN, Card.Suit.HEART),
                new Card(Card.Rank.KING, Card.Suit.HEART),
                new Card(Card.Rank.ACE, Card.Suit.CLUB),
                new Card(Card.Rank.TWO, Card.Suit.SPADE)
        );
        List<Card> QKA23 = Arrays.asList(
                new Card(Card.Rank.QUEEN, Card.Suit.HEART),
                new Card(Card.Rank.KING, Card.Suit.HEART),
                new Card(Card.Rank.ACE, Card.Suit.CLUB),
                new Card(Card.Rank.TWO, Card.Suit.SPADE),
                new Card(Card.Rank.THREE, Card.Suit.HEART)
        );
        List<Card> KA234 = Arrays.asList(
                new Card(Card.Rank.KING, Card.Suit.HEART),
                new Card(Card.Rank.ACE, Card.Suit.CLUB),
                new Card(Card.Rank.TWO, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.SPADE));

        List<Card> A2345 = Arrays.asList(
                new Card(Card.Rank.ACE, Card.Suit.CLUB),
                new Card(Card.Rank.TWO, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.SPADE),
                new Card(Card.Rank.FIVE, Card.Suit.HEART));

        List<Card> two3456 = Arrays.asList(
                new Card(Card.Rank.TWO, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.SPADE),
                new Card(Card.Rank.FIVE, Card.Suit.HEART),
                new Card(Card.Rank.SIX, Card.Suit.SPADE));


        assertEquals(new Straight(eight910JQ), new StraightHandler(null).convertCardPattern(eight910JQ));
        assertEquals(new Straight(JQK12), new StraightHandler(null).convertCardPattern(JQK12));
        assertEquals(new Straight(QKA23), new StraightHandler(null).convertCardPattern(QKA23));
        assertEquals(new Straight(KA234), new StraightHandler(null).convertCardPattern(KA234));
        assertEquals(new Straight(A2345), new StraightHandler(null).convertCardPattern(A2345));
        assertEquals(new Straight(two3456), new StraightHandler(null).convertCardPattern(two3456));
    }

    @Test
    public void wrongCardConvertToStraightTest() {
        List<Card> wrongStraight = Arrays.asList(
                new Card(Card.Rank.EIGHT, Card.Suit.CLUB),
                new Card(Card.Rank.NINE, Card.Suit.SPADE),
                new Card(Card.Rank.TEN, Card.Suit.CLUB),
                new Card(Card.Rank.JACK, Card.Suit.SPADE),
                new Card(Card.Rank.KING, Card.Suit.CLUB)
        );
        List<Card> wrongStraight2 = Arrays.asList(
                new Card(Card.Rank.KING, Card.Suit.CLUB),
                new Card(Card.Rank.KING, Card.Suit.DIAMOND),
                new Card(Card.Rank.KING, Card.Suit.HEART),
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND)
        );

        assertEquals(null, new StraightHandler(null).convertCardPattern(wrongStraight));

        assertEquals(null, new StraightHandler(null).convertCardPattern(wrongStraight2));
    }

}