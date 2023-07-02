package com.yangjun.c2b.big2;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.yangjun.c2b.big2.Card.Rank;

public class FullhousePatternHandler extends CardPatternHandler {

	public FullhousePatternHandler(CardPatternHandler nextHandler) {
		super(nextHandler);
	}

	@Override
	public boolean isValidPattern(List<Card> cards) {
	    if (cards.size() < 5) {
	        return false;
	    }

	    Map<Card.Rank, Integer> counts = new EnumMap<>(Rank.class);
	    for (Card card : cards) {
	        counts.put(card.getRank(), counts.getOrDefault(card.getRank(), 0) + 1);
	    }

	    boolean hasThreeOfKind = counts.values().contains(3);
	    boolean hasPair = counts.values().contains(2);

	    return hasThreeOfKind && hasPair;
	}

	@Override
	public CardPattern combinationCardPattern(List<Card> cards) {
		return new FullhousePattern(cards);
	}

	@Override
	public boolean isValidClassType(CardPattern cardPattern) {
		return cardPattern instanceof FullhousePattern;
	}

	@Override
	public CardPattern getSuitablePattern(CardPattern cardPattern, List<Card> palyerHandCards) {
		List<Card> result = new ArrayList<>();
	    if (palyerHandCards.size() < 5) {
	        return null;
	    }

	    Map<Card.Rank, List<Card>> cardsByRank = new EnumMap<>(Rank.class);
	    Card mainCard = ((FullhousePattern)cardPattern).getMainCard();
	    for (Card card : palyerHandCards) {
	        cardsByRank.computeIfAbsent(card.getRank(), k -> new ArrayList<>()).add(card);
	    }

	    
	    for (Map.Entry<Card.Rank, List<Card>> entry : cardsByRank.entrySet()) {
	        Card.Rank rank = entry.getKey();
	        List<Card> sameRankCards = entry.getValue();
	        if (sameRankCards.size() >= 3 && rank.compareTo(mainCard.getRank()) > 0) {
	            result.addAll(sameRankCards.subList(0, 3));
	            break;
	        }
	    }

	    if (result.isEmpty()) {
	        return null;
	    }

	    for (List<Card> sameRankCards : cardsByRank.values()) {
	        if (sameRankCards.size() >= 2 && sameRankCards.get(0).getRank() != result.get(0).getRank()) {
	            result.addAll(sameRankCards.subList(0, 2));
	            break;
	        }
	    }

	    if (result.size() < 5) {
	        return null;
	    }

	    return new FullhousePattern(result);
	}
}
