package com.yangjun.c2b.big2;

import java.util.List;

public abstract class CardPatternHandler {
	private CardPatternHandler nextHandler;
	
	protected CardPatternHandler(CardPatternHandler nextHandler) {
		this.nextHandler = nextHandler;
	}
	
	public CardPattern handle(List<Card> cards) {
		if (isValidPattern(cards)) {
			return combinationCardPattern(cards);
		} else if (nextHandler != null) {
			return nextHandler.handle(cards);
		} else {
			return null;
		}
	}
	
	public CardPattern assessAndFindSuitablePatternBiggerThanTopPlay(CardPattern cardPattern, List<Card> palyerHandCards) {
		if (isValidClassType(cardPattern)) {
			return getSuitablePattern(cardPattern, palyerHandCards);
		} else if (nextHandler != null) {
			return nextHandler.assessAndFindSuitablePatternBiggerThanTopPlay(cardPattern, palyerHandCards);
		} else {
			return null;
		}
	}
	
	
	public abstract boolean isValidClassType(CardPattern cardPattern);
	public abstract CardPattern getSuitablePattern(CardPattern cardPattern, List<Card> palyerHandCards);
	
	public abstract boolean isValidPattern(List<Card> cards);
	public abstract CardPattern combinationCardPattern(List<Card> cards);

}
