package test;

import Base.*;
import Base.CardPatternType.FullHouse;
import Base.CardPatternType.Pair;
import Base.CardPatternType.Single;

import Base.CardPatternType.Straight;
import CardPatternHandle.cardPatternHandler.FullHouseHandler;
import CardPatternHandle.cardPatternHandler.PairHandler;
import CardPatternHandle.cardPatternHandler.SingleHandler;
import CardPatternHandle.cardPatternHandler.StraightHandler;
import Strategy.AIChoosePlayCardStrategyHandler.*;
import Strategy.HumanPlayCardStrategyHandler.HumanFullHouseCardStrategyHandler;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameFlowTest {
    @Test
    public void findPlayerWhoHasClubThreeTest() {
        Player aiPlayer1 = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        Player hasClub3Player = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        Player aiPlayer3 = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        Player aiPlayer4 = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));

        aiPlayer1.addCardToHandCards(new Card(Card.Rank.THREE, Card.Suit.DIAMOND));
        hasClub3Player.addCardToHandCards(new Card(Card.Rank.THREE, Card.Suit.CLUB));
        aiPlayer3.addCardToHandCards(new Card(Card.Rank.THREE, Card.Suit.HEART));
        aiPlayer4.addCardToHandCards(new Card(Card.Rank.THREE, Card.Suit.SPADE));

        assertEquals(hasClub3Player, new Big2(new Deck(), Arrays.asList(aiPlayer1, hasClub3Player, aiPlayer3, aiPlayer4)).findPlayerWhoHasClubThree());
    }

    @Test
    public void playerHasSingleCardPatternTest() {
        Round round = new Round();
        round.setTopPlay(new Single(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND)
        )));
        Player aiPlayer = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));

        aiPlayer.setName("小明");
        aiPlayer.addCardToHandCards(
                new Card(Card.Rank.TWO, Card.Suit.CLUB));
        aiPlayer.play(round);
        assertTrue(aiPlayer.getHandCards().isEmpty());
        assertEquals(new Single(Arrays.asList(new Card(Card.Rank.TWO, Card.Suit.CLUB))), round.getTopPlay());

    }

    @Test
    public void playerDoesNotHasSingleCardPatternTest() {
        Round round = new Round();
        round.setTopPlayer(new HumanPlayer(new HumanFullHouseCardStrategyHandler(null), new SingleHandler(null)));
        round.setTopPlay(new Single(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND)
        )));

        Player aiPlayer = new AIPlayer(new AINewRoundStrategyHandler(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null)))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));

        aiPlayer.setName("小明");
        aiPlayer.addCardToHandCards(
                new Card(Card.Rank.THREE, Card.Suit.CLUB));
        aiPlayer.play(round);
        assertTrue(aiPlayer.getHandCards().size() == 1);
        assertEquals(new Single(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND)
        )), round.getTopPlay());
        assertTrue(aiPlayer.isPass());
    }

    @Test
    public void playerHasPairCardPatternTest() {
        Round round = new Round();
        round.setTopPlay(new Pair(List.of(
                new Card(Card.Rank.THREE, Card.Suit.DIAMOND),
                new Card(Card.Rank.THREE, Card.Suit.CLUB)
        )));

        Player aiPlayer = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        aiPlayer.setName("小明");
        aiPlayer.addCardToHandCards(
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB));
        aiPlayer.play(round);
        assertTrue(aiPlayer.getHandCards().isEmpty());
    }

    @Test
    public void playerDoesNotHasPairCardPatternTest() {
        Round round = new Round();
        round.setTopPlayer(new HumanPlayer(new HumanFullHouseCardStrategyHandler(null), new SingleHandler(null)));
        round.setTopPlay(new Pair(List.of(
                new Card(Card.Rank.TWO, Card.Suit.DIAMOND),
                new Card(Card.Rank.TWO, Card.Suit.CLUB)
        )));
        Player aiPlayer = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        aiPlayer.setName("小華");
        aiPlayer.addCardToHandCards(
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB));
        aiPlayer.play(round);

        assertTrue(aiPlayer.getHandCards().size() == 2);
        assertEquals(new Pair(List.of(
                new Card(Card.Rank.TWO, Card.Suit.DIAMOND),
                new Card(Card.Rank.TWO, Card.Suit.CLUB)
        )), round.getTopPlay());
        assertTrue(aiPlayer.isPass());
    }

    @Test
    public void playerHasFullHouseCardPatternTest() {
        CardPattern fullHouse4 = new FullHouse(Arrays.asList(
                new Card(Card.Rank.FOUR, Card.Suit.SPADE),
                new Card(Card.Rank.FOUR, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.DIAMOND),
                new Card(Card.Rank.TWO, Card.Suit.SPADE),
                new Card(Card.Rank.TWO, Card.Suit.HEART)));

        CardPattern fullHouseA = new FullHouse(Arrays.asList(
                new Card(Card.Rank.ACE, Card.Suit.SPADE),
                new Card(Card.Rank.ACE, Card.Suit.HEART),
                new Card(Card.Rank.ACE, Card.Suit.DIAMOND),
                new Card(Card.Rank.THREE, Card.Suit.SPADE),
                new Card(Card.Rank.THREE, Card.Suit.HEART)));

        Player aiPlayer1 = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));

        aiPlayer1.addCardToHandCards(
                new Card(Card.Rank.FIVE, Card.Suit.HEART),
                new Card(Card.Rank.FIVE, Card.Suit.SPADE),
                new Card(Card.Rank.ACE, Card.Suit.CLUB),
                new Card(Card.Rank.ACE, Card.Suit.HEART),
                new Card(Card.Rank.ACE, Card.Suit.SPADE),
                new Card(Card.Rank.KING, Card.Suit.CLUB),
                new Card(Card.Rank.KING, Card.Suit.HEART),
                new Card(Card.Rank.KING, Card.Suit.DIAMOND));

        Round round = new Round();
        round.setTopPlay(fullHouse4);
        aiPlayer1.setName("地瓜球");
        aiPlayer1.play(round);

        assertEquals(3, aiPlayer1.getHandCards().size());
        assertEquals(new FullHouse(Arrays.asList(
                new Card(Card.Rank.KING, Card.Suit.CLUB),
                new Card(Card.Rank.KING, Card.Suit.HEART),
                new Card(Card.Rank.KING, Card.Suit.DIAMOND),
                new Card(Card.Rank.FIVE, Card.Suit.SPADE),
                new Card(Card.Rank.FIVE, Card.Suit.HEART)
        )), round.getTopPlay());
        assertEquals(aiPlayer1, round.getTopPlayer());
    }

    @Test
    public void startNewRoundTest() {
        Player aiPlayer1 = new AIPlayer(new AINewRoundStrategyHandler(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null)))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        Player aiPlayer2 = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(new AINewRoundStrategyHandler(null)))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        Player aiPlayer3 = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(new AINewRoundStrategyHandler(null)))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        Player aiPlayer4 = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(new AINewRoundStrategyHandler(null)))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));

        List<Player> players = Arrays.asList(aiPlayer1, aiPlayer2, aiPlayer3, aiPlayer4);
        aiPlayer1.addCardToHandCards(new Card(Card.Rank.FOUR, Card.Suit.DIAMOND));
        aiPlayer2.addCardToHandCards(new Card(Card.Rank.ACE, Card.Suit.CLUB));
        aiPlayer3.addCardToHandCards(new Card(Card.Rank.EIGHT, Card.Suit.SPADE));
        aiPlayer4.addCardToHandCards(new Card(Card.Rank.JACK, Card.Suit.HEART));

        Round round = new Round();
        round.setTurn(players.indexOf(aiPlayer1));
        round.setTopPlayer(aiPlayer1);
        aiPlayer1.setName("新回合開牌玩家");
        aiPlayer2.setName("新回合接牌玩家");
        aiPlayer3.setName("新回合Pass玩家1");
        aiPlayer1.play(round);

        assertTrue(aiPlayer1.getHandCards().isEmpty());
        assertEquals(new Single(Arrays.asList(new Card(Card.Rank.FOUR, Card.Suit.DIAMOND))), round.getTopPlay());
        assertEquals(aiPlayer2, players.get(round.getTurn()));
        assertEquals(1, round.getTurn());

        players.get(round.getTurn()).play(round);

        assertTrue(aiPlayer2.getHandCards().isEmpty());
        assertEquals(new Single(Arrays.asList(new Card(Card.Rank.ACE, Card.Suit.CLUB))), round.getTopPlay());
        assertEquals(aiPlayer3, players.get(round.getTurn()));
        assertEquals(2, round.getTurn());

        players.get(round.getTurn()).play(round);
        assertTrue(aiPlayer3.getHandCards().size() == 1);
        assertEquals(new Single(Arrays.asList(new Card(Card.Rank.ACE, Card.Suit.CLUB))), round.getTopPlay());
        assertTrue(aiPlayer3.isPass());
        assertEquals(aiPlayer4, players.get(round.getTurn()));
        assertEquals(3, round.getTurn());

        players.get(round.getTurn()).play(round);
        assertTrue(aiPlayer4.getHandCards().size() == 1);
        assertEquals(new Single(Arrays.asList(new Card(Card.Rank.ACE, Card.Suit.CLUB))), round.getTopPlay());
        assertTrue(aiPlayer4.isPass());
        assertEquals(aiPlayer1, players.get(round.getTurn() % 4));
        assertEquals(4, round.getTurn());
    }

    @Test
    public void pairCompareTest() {
        Round round = new Round();
        round.setTopPlay(new Pair(Arrays.asList(new Card(Card.Rank.FIVE, Card.Suit.CLUB), new Card(Card.Rank.FIVE, Card.Suit.HEART))));
        Player aiPlayer = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));

        aiPlayer.addCardToHandCards(new Card(Card.Rank.FIVE, Card.Suit.SPADE), new Card(Card.Rank.FIVE, Card.Suit.DIAMOND));
        aiPlayer.setName("對子測試AI");
        aiPlayer.play(round);

        assertTrue(!aiPlayer.isPass());
        assertTrue(aiPlayer.getHandCards().isEmpty());
        assertEquals(new Pair(
                Arrays.asList(
                        new Card(Card.Rank.FIVE, Card.Suit.SPADE),
                        new Card(Card.Rank.FIVE, Card.Suit.DIAMOND))), round.getTopPlay());
    }

    @Test
    public void StraightFlowTest() {
        Round round = new Round();
        round.setTopPlay(new Straight(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.HEART),
                new Card(Card.Rank.FIVE, Card.Suit.CLUB),
                new Card(Card.Rank.SIX, Card.Suit.HEART),
                new Card(Card.Rank.SEVEN, Card.Suit.DIAMOND))
        ));
        Player aiPlayer = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));

        aiPlayer.addCardToHandCards(
                new Card(Card.Rank.SEVEN, Card.Suit.SPADE),
                new Card(Card.Rank.EIGHT, Card.Suit.DIAMOND),
                new Card(Card.Rank.NINE, Card.Suit.CLUB),
                new Card(Card.Rank.TEN, Card.Suit.DIAMOND),
                new Card(Card.Rank.JACK, Card.Suit.HEART),
                new Card(Card.Rank.ACE, Card.Suit.DIAMOND));
        aiPlayer.setName("對子測試AI-1");
        aiPlayer.play(round);

        assertEquals(new Straight(Arrays.asList(
                new Card(Card.Rank.SEVEN, Card.Suit.SPADE),
                new Card(Card.Rank.EIGHT, Card.Suit.DIAMOND),
                new Card(Card.Rank.NINE, Card.Suit.CLUB),
                new Card(Card.Rank.TEN, Card.Suit.DIAMOND),
                new Card(Card.Rank.JACK, Card.Suit.HEART))), round.getTopPlay());
        assertEquals(1, aiPlayer.getHandCards().size());

        Player aiPlayer2 = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        aiPlayer2.addCardToHandCards(
                new Card(Card.Rank.NINE, Card.Suit.CLUB),
                new Card(Card.Rank.TEN, Card.Suit.DIAMOND),
                new Card(Card.Rank.JACK, Card.Suit.HEART),
                new Card(Card.Rank.QUEEN, Card.Suit.CLUB),
                new Card(Card.Rank.KING, Card.Suit.DIAMOND));
        aiPlayer2.setName("對子測試AI-2");
        aiPlayer2.play(round);

        assertEquals(new Straight(Arrays.asList(
                new Card(Card.Rank.NINE, Card.Suit.CLUB),
                new Card(Card.Rank.TEN, Card.Suit.DIAMOND),
                new Card(Card.Rank.JACK, Card.Suit.HEART),
                new Card(Card.Rank.QUEEN, Card.Suit.CLUB),
                new Card(Card.Rank.KING, Card.Suit.DIAMOND)
        )), round.getTopPlay());
        assertEquals(0, aiPlayer2.getHandCards().size());

        Player aiPlayer3 = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        aiPlayer3.addCardToHandCards(
                new Card(Card.Rank.ACE, Card.Suit.CLUB),
                new Card(Card.Rank.TWO, Card.Suit.DIAMOND),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FIVE, Card.Suit.DIAMOND),
                new Card(Card.Rank.SEVEN, Card.Suit.HEART),
                new Card(Card.Rank.SEVEN, Card.Suit.SPADE));
        aiPlayer3.setName("對子測試AI-3");
        aiPlayer3.play(round);

        assertEquals(new Straight(Arrays.asList(
                new Card(Card.Rank.ACE, Card.Suit.CLUB),
                new Card(Card.Rank.TWO, Card.Suit.DIAMOND),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FIVE, Card.Suit.DIAMOND)
        )), round.getTopPlay());
        assertEquals(2, aiPlayer3.getHandCards().size());

        Player aiPlayer4 = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        aiPlayer4.addCardToHandCards(
                new Card(Card.Rank.TWO, Card.Suit.SPADE),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FIVE, Card.Suit.DIAMOND),
                new Card(Card.Rank.SIX, Card.Suit.HEART),
                new Card(Card.Rank.SEVEN, Card.Suit.HEART),
                new Card(Card.Rank.SEVEN, Card.Suit.SPADE));
        aiPlayer4.setName("對子測試AI-3");
        aiPlayer4.play(round);

        assertEquals(new Straight(Arrays.asList(
                new Card(Card.Rank.TWO, Card.Suit.SPADE),
                new Card(Card.Rank.THREE, Card.Suit.HEART),
                new Card(Card.Rank.FOUR, Card.Suit.CLUB),
                new Card(Card.Rank.FIVE, Card.Suit.DIAMOND),
                new Card(Card.Rank.SIX, Card.Suit.HEART))
        ), round.getTopPlay());
        assertEquals(2, aiPlayer3.getHandCards().size());
    }

    @Test
    public void StraightFlowPassTest() {
        Round round = new Round();
        round.setTopPlay(new Straight(Arrays.asList(
                new Card(Card.Rank.THREE, Card.Suit.CLUB),
                new Card(Card.Rank.FOUR, Card.Suit.HEART),
                new Card(Card.Rank.FIVE, Card.Suit.CLUB),
                new Card(Card.Rank.SIX, Card.Suit.HEART),
                new Card(Card.Rank.SEVEN, Card.Suit.DIAMOND))
        ));
        Player aiPlayer = new AIPlayer(new AISingleCardStrategyHandler(new AIPairCardStrategyHandler(new AIFullHouseStrategyHandler(new AIStraightStrategyHandler(new AIPassStrategyHandler(null))))), new SingleHandler(new PairHandler(new FullHouseHandler(new StraightHandler(null)))));
        aiPlayer.addCardToHandCards(new Card(Card.Rank.SEVEN, Card.Suit.SPADE),
                new Card(Card.Rank.EIGHT, Card.Suit.DIAMOND),
                new Card(Card.Rank.NINE, Card.Suit.CLUB),
                new Card(Card.Rank.TEN, Card.Suit.DIAMOND),
                new Card(Card.Rank.QUEEN, Card.Suit.HEART));
        aiPlayer.setName("順子測試AI");
        aiPlayer.play(round);

        assertEquals(5, aiPlayer.getHandCards().size());
        assertTrue(aiPlayer.isPass());
    }

}
