package com.yangjun.c2b.big2;

import java.util.Arrays;

public class AIPlayer extends Player {

	@Override
	public void nameHimSelf(int index) {
		String aiName = index + "-AI";
		setName(aiName);
	}

	@Override
	public TurnDecison makeTurnDecison() {
		Big2 game = getGame();
		boolean isFirstRound = game.isFirstRound();
		boolean isNewRoundFirstTurn = game.isNewRoundFirstTurn();

		if (isFirstRound && isNewRoundFirstTurn) {
			Card clubThree = findCLUBTHREE();
			return new TurnDecison(this, false, new SinglePattern(Arrays.asList(clubThree)));
		} else {
			CardPattern cardPattern = game.assessAndFindSuitablePatternBiggerThanTopPlay(this);
			if (cardPattern == null) {
				return TurnDecison.pass(this);
			} else {
				return new TurnDecison(this, false, cardPattern);
			}
		}
	}

	private Card findCLUBTHREE() {
		return getHandCards().stream().filter(c -> c.getRank() == Card.Rank.THREE && c.getSuit() == Card.Suit.CLUB).findFirst()
				.orElseThrow(() -> new RuntimeException("CLUB THREE not found"));
	}

}
