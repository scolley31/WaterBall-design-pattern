package com.yangjun.c2b.big2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.yangjun.c2b.big2.Card.Rank;

public class PairPatternHandler extends CardPatternHandler {

	public PairPatternHandler(CardPatternHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public boolean isValidPattern(List<Card> cards) {
		return cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank();
	}

	@Override
	public CardPattern combinationCardPattern(List<Card> cards) {
		return new PairPattern(cards);
	}

	@Override
	public boolean isValidClassType(CardPattern cardPattern) {
		return cardPattern instanceof PairPattern;
	}

	@Override
	public CardPattern getSuitablePattern(CardPattern cardPattern, List<Card> palyerHandCards) {
	    if (palyerHandCards.size() < 2) {
	        return null;
	    }

	    List<Card> result = new ArrayList<>();
	    Card mainCard = cardPattern.getCard(1);
	    Map<Card.Rank, List<Card>> cardsByRank = new EnumMap<>(Rank.class);
	    for (Card card : palyerHandCards) {
	        cardsByRank.computeIfAbsent(card.getRank(), k -> new ArrayList<>()).add(card);
	    }
	    
	    for (Map.Entry<Card.Rank, List<Card>> entry : cardsByRank.entrySet()) {
	        Card.Rank rank = entry.getKey();
	        List<Card> sameRankCards = entry.getValue();
	        Collections.sort(sameRankCards);
	        if (sameRankCards.size() >= 2 && ( rank.compareTo(mainCard.getRank()) > 0 
	        		|| (rank.compareTo(mainCard.getRank()) == 0 && sameRankCards.get(1).compareTo(mainCard) > 0))) {
	        	result.add(sameRankCards.get(0));
	        	result.add(sameRankCards.get(1));
	            break;
	        }
	    }
	    
	    if (result.size() < 2) {
	        return null;
	    }
	    
		return new PairPattern(result);
	}
}
