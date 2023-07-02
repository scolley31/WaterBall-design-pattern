package com.yangjun.test.c2b.big2;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.yangjun.c2b.big2.Card;
import com.yangjun.c2b.big2.CardPattern;
import com.yangjun.c2b.big2.CardPatternHandler;
import com.yangjun.c2b.big2.FullhousePatternHandler;
import com.yangjun.c2b.big2.PairPatternHandler;
import com.yangjun.c2b.big2.SinglePatternHandler;
import com.yangjun.c2b.big2.StraightPatternHandler;

public class CardPatternHandlerTest {
    private static CardPatternHandler handler;
	
    @BeforeAll
    public static void setup() {
        handler = new SinglePatternHandler(new PairPatternHandler(new FullhousePatternHandler(new StraightPatternHandler(null))));
    }
	
	@Test
    public void testNoCertainCardPattern() {
		Card card1 = new Card(Card.Suit.SPADE , Card.Rank.ACE);
        Card card2 = new Card(Card.Suit.HEART, Card.Rank.ACE);
        Card card3 = new Card(Card.Suit.CLUB, Card.Rank.ACE);
        Card card4 = new Card(Card.Suit.DIAMOND, Card.Rank.ACE);

        Card card5 = new Card(Card.Suit.DIAMOND, Card.Rank.ACE);
        Card card6 = new Card(Card.Suit.SPADE , Card.Rank.TWO);
        Card card7 = new Card(Card.Suit.HEART, Card.Rank.THREE);
        Card card8 = new Card(Card.Suit.CLUB, Card.Rank.FOUR);
        Card card9 = new Card(Card.Suit.DIAMOND, Card.Rank.SIX);
        
        List<Card> cardList1 = Arrays.asList(card1, card2, card3, card4);
        List<Card> cardList2 = Arrays.asList(card5, card6, card7, card8, card9);

        CardPattern cardPattern1 = handler.handle(cardList1);
        CardPattern cardPattern2 = handler.handle(cardList2);

        assertNull(cardPattern1);
        assertNull(cardPattern2);
    }

	@Test
    public void testSinglePattern() {
		Card card1 = new Card(Card.Suit.SPADE , Card.Rank.ACE);
        Card card2 = new Card(Card.Suit.HEART, Card.Rank.ACE);
        
        Card card3 = new Card(Card.Suit.SPADE , Card.Rank.FIVE);
        Card card4 = new Card(Card.Suit.HEART, Card.Rank.ACE);

        List<Card> cardList1 = Arrays.asList(card1);
        List<Card> cardList2 = Arrays.asList(card2);
        
        List<Card> cardList3 = Arrays.asList(card3);
        List<Card> cardList4 = Arrays.asList(card4);

        CardPattern cardPattern1 = handler.handle(cardList1);
        CardPattern cardPattern2 = handler.handle(cardList2);
        CardPattern cardPattern3 = handler.handle(cardList3);
        CardPattern cardPattern4 = handler.handle(cardList4);
        
        assertTrue(cardPattern1.compareTo(cardPattern2) > 0);
        assertTrue(cardPattern3.compareTo(cardPattern4) < 0);
    }
	
	@Test
    public void testPairPattern() {
		Card card1 = new Card(Card.Suit.SPADE , Card.Rank.ACE);
        Card card2 = new Card(Card.Suit.HEART, Card.Rank.ACE);
        Card card3 = new Card(Card.Suit.CLUB, Card.Rank.TWO);
        Card card4 = new Card(Card.Suit.DIAMOND, Card.Rank.TWO);
        
        Card card5 = new Card(Card.Suit.CLUB, Card.Rank.FIVE);
        Card card6 = new Card(Card.Suit.SPADE , Card.Rank.FIVE);
        Card card7 = new Card(Card.Suit.HEART, Card.Rank.FIVE);
        Card card8 = new Card(Card.Suit.DIAMOND, Card.Rank.FIVE);

        List<Card> cardList1 = Arrays.asList(card1, card2);
        List<Card> cardList2 = Arrays.asList(card3, card4);
        List<Card> cardList3 = Arrays.asList(card5, card6);
        List<Card> cardList4 = Arrays.asList(card7, card8);

        CardPattern cardPattern1 = handler.handle(cardList1);
        CardPattern cardPattern2 = handler.handle(cardList2);
        CardPattern cardPattern3 = handler.handle(cardList3);
        CardPattern cardPattern4 = handler.handle(cardList4);

        assertTrue(cardPattern1.compareTo(cardPattern2) < 0);
        assertTrue(cardPattern3.compareTo(cardPattern4) > 0);
    }
	
	@Test
    public void testStraightPattern() {
        Card card1 = new Card(Card.Suit.CLUB, Card.Rank.JACK);
        Card card2 = new Card(Card.Suit.CLUB, Card.Rank.QUEEN);
        Card card3 = new Card(Card.Suit.CLUB, Card.Rank.KING);
        Card card4 = new Card(Card.Suit.CLUB , Card.Rank.ACE);
        Card card5 = new Card(Card.Suit.CLUB, Card.Rank.TWO);
        
        Card card6 = new Card(Card.Suit.SPADE , Card.Rank.ACE);
        Card card7 = new Card(Card.Suit.SPADE, Card.Rank.TWO);
        Card card8 = new Card(Card.Suit.SPADE, Card.Rank.THREE);
        Card card9 = new Card(Card.Suit.SPADE, Card.Rank.FOUR);
        Card card10 = new Card(Card.Suit.SPADE, Card.Rank.FIVE);
        //大小比較規則：將五張牌中最大的牌作為比較基準，最大是二，所以比較二
        
        Card card11 = new Card(Card.Suit.CLUB , Card.Rank.SIX);
        Card card12 = new Card(Card.Suit.CLUB, Card.Rank.SEVEN);
        Card card13 = new Card(Card.Suit.CLUB, Card.Rank.EIGHT);
        Card card14 = new Card(Card.Suit.CLUB, Card.Rank.NINE);
        Card card15 = new Card(Card.Suit.SPADE, Card.Rank.TEN);
        
        Card card16 = new Card(Card.Suit.SPADE , Card.Rank.SIX);
        Card card17 = new Card(Card.Suit.SPADE, Card.Rank.SEVEN);
        Card card18 = new Card(Card.Suit.SPADE, Card.Rank.EIGHT);
        Card card19 = new Card(Card.Suit.SPADE, Card.Rank.NINE);
        Card card20 = new Card(Card.Suit.CLUB, Card.Rank.TEN);

        List<Card> cardList1 = Arrays.asList(card1, card2, card3, card4, card5);
        List<Card> cardList2 = Arrays.asList(card6, card7, card8, card9, card10);
        List<Card> cardList3 = Arrays.asList(card11, card12, card13, card14, card15);
        List<Card> cardList4 = Arrays.asList(card16, card17, card18, card19, card20);

        CardPattern cardPattern1 = handler.handle(cardList1);
        CardPattern cardPattern2 = handler.handle(cardList2);
        CardPattern cardPattern3 = handler.handle(cardList3);
        CardPattern cardPattern4 = handler.handle(cardList4);

        assertTrue(cardPattern1.compareTo(cardPattern2) < 0);
        assertTrue(cardPattern3.compareTo(cardPattern4) > 0);   
    }
	
	@Test
	public void testFullhousePattern() {
        Card card1 = new Card(Card.Suit.CLUB, Card.Rank.JACK);
        Card card2 = new Card(Card.Suit.SPADE, Card.Rank.JACK);
        Card card3 = new Card(Card.Suit.DIAMOND, Card.Rank.JACK);
        Card card4 = new Card(Card.Suit.CLUB , Card.Rank.FOUR);
        Card card5 = new Card(Card.Suit.DIAMOND, Card.Rank.FOUR);
        
        Card card6 = new Card(Card.Suit.CLUB , Card.Rank.TEN);
        Card card7 = new Card(Card.Suit.SPADE, Card.Rank.TEN);
        Card card8 = new Card(Card.Suit.DIAMOND, Card.Rank.TEN);
        Card card9 = new Card(Card.Suit.SPADE, Card.Rank.ACE);
        Card card10 = new Card(Card.Suit.HEART, Card.Rank.ACE);
        
        Card card11 = new Card(Card.Suit.CLUB , Card.Rank.TWO);
        Card card12 = new Card(Card.Suit.DIAMOND, Card.Rank.TWO);
        Card card13 = new Card(Card.Suit.SPADE, Card.Rank.TWO);
        Card card14 = new Card(Card.Suit.CLUB, Card.Rank.SIX);
        Card card15 = new Card(Card.Suit.SPADE, Card.Rank.SIX);
        
        Card card16 = new Card(Card.Suit.CLUB , Card.Rank.KING);
        Card card17 = new Card(Card.Suit.DIAMOND, Card.Rank.KING);
        Card card18 = new Card(Card.Suit.SPADE, Card.Rank.KING);
        Card card19 = new Card(Card.Suit.SPADE, Card.Rank.NINE);
        Card card20 = new Card(Card.Suit.CLUB, Card.Rank.NINE);

        List<Card> cardList1 = Arrays.asList(card1, card2, card3, card4, card5);
        List<Card> cardList2 = Arrays.asList(card6, card7, card8, card9, card10);
        List<Card> cardList3 = Arrays.asList(card11, card12, card13, card14, card15);
        List<Card> cardList4 = Arrays.asList(card16, card17, card18, card19, card20);

        CardPattern cardPattern1 = handler.handle(cardList1);
        CardPattern cardPattern2 = handler.handle(cardList2);
        CardPattern cardPattern3 = handler.handle(cardList3);
        CardPattern cardPattern4 = handler.handle(cardList4);

        assertTrue(cardPattern1.compareTo(cardPattern2) > 0);
        assertTrue(cardPattern3.compareTo(cardPattern4) > 0);   
	}
}
