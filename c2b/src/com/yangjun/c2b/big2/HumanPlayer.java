package com.yangjun.c2b.big2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player  {
	private static final Scanner in = new Scanner(System.in);

	@Override
	public void nameHimSelf(int index) {
		String playerName = in.nextLine();
		if (playerName.isEmpty()) {
			nameHimSelf(index);
		} else {
			setName(playerName);
		}
	}

	@Override
	public TurnDecison makeTurnDecison() {
	    while (true) {
	        List<Integer> choiceIndexs = getUserInput();
	        Big2 game = getGame();
	        boolean isFirstRound = game.isFirstRound();
	        boolean isNewRoundFirstTurn = game.isNewRoundFirstTurn();
	        if (choiceIndexs.isEmpty()) {
	        	if (!isNewRoundFirstTurn) {
	        		return TurnDecison.pass(this);
	        	} else {
	        		System.out.println("你不能在新的回合中喊 PASS");
	        	}
	        } else {
	            CardPattern cardPattern = game.getValidCardPattern(this, choiceIndexs);
	            if (cardPattern != null) {
	            	if (isFirstRound && isNewRoundFirstTurn) {
	            		if (game.isCardsPatternContains(cardPattern, Card.Suit.CLUB, Card.Rank.THREE)) {
	            			return new TurnDecison(this, false, cardPattern);
	            		}
	            	} else {
	            		return new TurnDecison(this, false, cardPattern);
	            	}
	            } else {
	                System.out.println("此牌型不合法，請再嘗試一次。");
	            }
	        }
	    }
	}

	private List<Integer> getUserInput() {
	    while (true) {
	    	List<Integer> choiceIndexs = new ArrayList<>();
	        String input = in.nextLine().trim();
	        if ("-1".equals(input)) {
	            return choiceIndexs;
	        } else {
	            try {
	                String[] indexes = input.split("\\s+");
	                int handSize = getHand().size();
	                for (String index : indexes) {
	                    int i = Integer.parseInt(index);
	                    if (i < 0 || i >= handSize) {
	                        throw new NumberFormatException();
	                    } else {
	                        choiceIndexs.add(i);
	                    }
	                }
	                return choiceIndexs;
	            } catch (NumberFormatException e) {
	                System.out.println("此牌型不合法，請再嘗試一次。");
	            }
	        }
	    }
	}


}
