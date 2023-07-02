package com.yangjun.c2b.big2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Big2 {
	private boolean gameOver = false;
	private Player winner;
	private List<Player> players;
	private Deck deck;
	private List<Round> rounds = new ArrayList<>();
	private CardPattern topPlay;
	private final CardPatternHandler cardPatternHandler;

	public Big2(Deck deck, List<Player> players, CardPatternHandler cardPatternHandler) {
		players.forEach(p -> p.setGame(this));
		this.cardPatternHandler = cardPatternHandler;
		this.deck = deck;
		this.players = players;
	}

	public void startGame() {
		deck.shuffle();
		printAllCardInDeck();
		nameThemselves();
		dealTheCards();
		playRounds();
		printWinner();
	}

	public void printAllCardInDeck() {
		System.out.println(deck.getCards().stream().map(Objects::toString).collect(Collectors.joining(" ")));
	}
	
	private void nameThemselves() {
		for (int i = 0; i < players.size(); i++) {
			players.get(i).nameHimSelf(i + 1);
		}
	}
	
	private void dealTheCards() {
		int i = 0;
		while (!deck.empty()) {
			Card card = deck.deal();
			players.get(i % players.size()).addHandCard(card);
			i++;
		}
		
		players.forEach(p -> Collections.sort(p.getHandCards()));
	}

	private void playRounds() {
		while (!gameOver) {
			System.out.println("新的回合開始了。");
			Round round = createNewRound();
			Player topPlayer = getTopPlayer();
			round.setTopPlayer(topPlayer);
			topPlay = getNewTopPlay(topPlayer);

			int currentIndex = getPlayerIndex(topPlayer);
			while (!gameOver && !round.isRoundOver()) {
				Player currentPlayer = getNextPlayer(currentIndex);
				TurnDecison turnDecison = playerTakeTurn(currentPlayer);
				if (turnDecison.isPass()) {
					round.addPassCount();
				} else {
					topPlay = turnDecison.getCardPattern();
					round.setTopPlayer(currentPlayer);
					round.resetPassCount();
					removeCardsInPlayerHand(currentPlayer, turnDecison);
					checkIsPlayerWin(currentPlayer);
				}
				currentIndex++;
			}
			clearTopPlay();
		}
	}

	private boolean checkIsPlayerWin(Player currentPlayer) {
		if (currentPlayer.hasNoHandCard()) {
			this.gameOver = true;
			this.winner = currentPlayer;
		}
		return gameOver;
	}

	private void printWinner() {
		System.out.println("遊戲結束，遊戲勝利者為 " + winner.getName());
	}

	private void clearTopPlay() {
		topPlay = null;
	}

	public CardPattern getTopPlay() {
		return topPlay;
	}
	
	private CardPattern getNewTopPlay(Player topPlayer) {
		TurnDecison turnDecison = playerTakeTurn(topPlayer);
		addNewTurnDecisonToRound(turnDecison);
		removeCardsInPlayerHand(topPlayer, turnDecison);
		return turnDecison.getCardPattern();
	}

	private void addNewTurnDecisonToRound(TurnDecison turnDecison) {
		rounds.get(rounds.size() - 1).addTurnDecison(turnDecison);
	}
	
	private void removeCardsInPlayerHand(Player player, TurnDecison turnDecison) {
		List<Card> handCards = player.getHandCards();
		List<Card> turnDecisionCards = turnDecison.getCardPattern().getCards();
	    handCards.removeIf(turnDecisionCards::contains);
	}
	
	private TurnDecison playerTakeTurn(Player player) {
		System.out.printf("輪到 %s 了\n", player.getName());
		TurnDecison turnDecison = player.takeTurn();
		if (turnDecison.isPass()) {
			System.out.printf("玩家 %s PASS\n", player.getName());
		} else {
			System.out.printf("玩家 %s 打出了 %s \n", player.getName(), turnDecison.getCardPattern().getPatternInfo());
		}
		addNewTurnDecisonToRound(turnDecison);
		return turnDecison;
	}

	public boolean isFirstRound() {
		return rounds.size() == 1;
	}
	
	public boolean isNewRoundFirstTurn() {
		return rounds.get(rounds.size() - 1).isFirstTurn();
	}
	
	private Round createNewRound() {
		Round round = new Round();
		rounds.add(round);
		return round;
	}

	public boolean isCardsPatternContains(CardPattern cardPattern, Card.Suit suit, Card.Rank rank) {
		return cardPattern.getCards().stream().anyMatch(c -> c.getRank() == rank && c.getSuit() == suit);
	}

	private Player getTopPlayer() {
		if (isFirstRound()) {
			return findPlayerHasClubThree();
		} else {
			return getLastRoundTopPlayer();
		}
	}
	
	private Player findPlayerHasClubThree() {
		return players.stream()
				.filter(p -> p.getHand().getCards().stream()
						.anyMatch(c -> c.getRank() == Card.Rank.THREE && c.getSuit() == Card.Suit.CLUB))
				.findFirst()
	            .orElseThrow(() -> new IllegalStateException("No player found with the CLUB THREE card."));
	}
	
	private Player getLastRoundTopPlayer() {
		//-2 = 已經有加入新的 Round 進 List 了
		return rounds.get(rounds.size() - 2).getTopPlayer();
	}

	private Player getNextPlayer(int currentIndex) {
		int nextIndex = (currentIndex + 1) % players.size();
		return players.get(nextIndex);
	}

	private int getPlayerIndex(Player player) {
		return IntStream.range(0, players.size()).filter(i -> players.get(i).equals(player)).findFirst().getAsInt();
	}

	CardPattern getValidCardPattern(Player player, List<Integer> choiceIndexs) {
	    List<Card> choiceCards = player.getHandCardsByIndexs(choiceIndexs);
	    CardPattern cardPattern = cardPatternHandler.handle(choiceCards);
	    if (cardPattern == null) {
	        return null;
	    }
	    if (topPlay == null) {
	        return cardPattern;
	    }
	    boolean isSameClass = cardPattern.getClass().equals(topPlay.getClass());
	    boolean isValueHigher = cardPattern.compareTo(topPlay) > 0;
	    
	    if (isSameClass && isValueHigher) {
	        return cardPattern;
	    }
	    return null;
	}
	
	CardPattern assessAndFindSuitablePatternBiggerThanTopPlay (Player player) {
		if (topPlay == null) {
			Card card = player.getHandCards().get(0);
			return new SinglePattern(Arrays.asList(card));
		} else {
			return cardPatternHandler.assessAndFindSuitablePatternBiggerThanTopPlay(topPlay, player.getHandCards());
		}
	}
}
