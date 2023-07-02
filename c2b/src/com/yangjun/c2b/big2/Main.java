package com.yangjun.c2b.big2;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Player> players = Arrays.asList(new AIPlayer(), new AIPlayer(), new AIPlayer(), new AIPlayer());
		Big2 game = new Big2(new Deck(), players, new SinglePatternHandler(new PairPatternHandler(new FullhousePatternHandler(new StraightPatternHandler(null)))));
		game.startGame();
	}

}
