package test;

import Base.Card;
import Base.CardPattern;
import Base.CardPatternType.FullHouse;
import Base.CardPatternType.Pair;
import Base.CardPatternType.Single;
import Base.CardPatternType.Straight;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CardPatternCompareTest {

    @Test
    public void compareSingleTest() {
        Single clubThree = new Single(Arrays.asList(new Card(Card.Rank.THREE, Card.Suit.CLUB)));
        Single spadeTwo = new Single(Arrays.asList(new Card(Card.Rank.TWO, Card.Suit.SPADE)));
        Single diamondKing = new Single(Arrays.asList(new Card(Card.Rank.KING, Card.Suit.DIAMOND)));
        Pair threePair = new Pair(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND)));
        assertTrue(clubThree.isSameCardPattern(spadeTwo));
        assertTrue(spadeTwo.isSameCardPattern(diamondKing));

        assertFalse(clubThree.isBiggerThan(spadeTwo));
        assertFalse(clubThree.isBiggerThan(diamondKing));
        assertTrue(spadeTwo.isBiggerThan(diamondKing));
    }

    @Test
    public void comparePairTest() {
        Pair threePair = new Pair(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND)));
        Pair twoPair = new Pair(Arrays.asList(
                new Card(Card.Rank.TWO, Card.Suit.SPADE),
                new Card(Card.Rank.TWO, Card.Suit.HEART)));
        Pair twoPair2 = new Pair(Arrays.asList(
                new Card(Card.Rank.TWO, Card.Suit.CLUB),
                new Card(Card.Rank.TWO, Card.Suit.DIAMOND)));
        assertTrue(twoPair.isSameCardPattern(threePair));
        assertTrue(twoPair.isBiggerThan(threePair));
        assertTrue(twoPair.isBiggerThan(twoPair2));
    }

    @Test
    public void compareFullHouseTest() {
        CardPattern threeFullHouse = new FullHouse(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND)));
        CardPattern twoFullHouse = new FullHouse(Arrays.asList(
                new Card(Card.Rank.TWO, Card.Suit.SPADE),
                new Card(Card.Rank.TWO, Card.Suit.HEART),
                new Card(Card.Rank.TWO, Card.Suit.DIAMOND),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND)));
        CardPattern nineFullHouse = new FullHouse(Arrays.asList(
                new Card(Card.Rank.NINE, Card.Suit.SPADE),
                new Card(Card.Rank.NINE, Card.Suit.HEART),
                new Card(Card.Rank.NINE, Card.Suit.DIAMOND),
                new Card(Card.Rank.KING, Card.Suit.CLUB),
                new Card(Card.Rank.KING, Card.Suit.DIAMOND)
        ));

        assertTrue(nineFullHouse.isBiggerThan(threeFullHouse));
        assertFalse(nineFullHouse.isBiggerThan(twoFullHouse));
        assertTrue(twoFullHouse.isSameCardPattern(threeFullHouse));
        assertTrue(twoFullHouse.isBiggerThan(threeFullHouse));
    }

    @Test
    public void compareStraightTest() {
        CardPattern smallStraight = new Straight(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND),
                new Card(Card.Rank.FIVE, Card.Suit.HEART),
                new Card(Card.Rank.SIX, Card.Suit.CLUB),
                new Card(Card.Rank.SEVEN, Card.Suit.DIAMOND)));
        CardPattern mediumStraight = new Straight(Arrays.asList(
                new Card(Card.Rank.NINE, Card.Suit.CLUB),
                new Card(Card.Rank.TEN, Card.Suit.DIAMOND),
                new Card(Card.Rank.JACK, Card.Suit.HEART),
                new Card(Card.Rank.QUEEN, Card.Suit.CLUB),
                new Card(Card.Rank.KING, Card.Suit.DIAMOND)));
        CardPattern cardPattern = new Straight(Arrays.asList(
                new Card(Card.Rank.ACE, Card.Suit.CLUB),
                new Card(Card.Rank.TWO, Card.Suit.DIAMOND),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FIVE, Card.Suit.DIAMOND)));
        CardPattern bigStraight = new Straight(Arrays.asList(
                new Card(Card.Rank.TWO, Card.Suit.SPADE),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND),
                new Card(Card.Rank.FIVE, Card.Suit.CLUB),
                new Card(Card.Rank.SIX, Card.Suit.DIAMOND)));

        assertTrue(smallStraight.isSameCardPattern(bigStraight) && mediumStraight.isSameCardPattern(bigStraight));
        assertFalse(smallStraight.isBiggerThan(mediumStraight));
        assertTrue(bigStraight.isBiggerThan(smallStraight));
        assertTrue(bigStraight.isBiggerThan(mediumStraight));
        assertTrue(bigStraight.isBiggerThan(cardPattern));
    }
}
