package designpattern;

import java.util.ArrayList;
import java.util.List;

public class ExchangeHands {
	// 執行交換手牌的回合
	private int exchangeRound;
	private Player player1;
	private Player player2;
	
	public ExchangeHands(Player currentPlayer, Player targetPlayer,int round) {
		this.setExchangeRound(round);
		this.player1 = currentPlayer;
		this.player2 = targetPlayer;
		
		List<Card> tempCards = new ArrayList<Card>();
		tempCards = this.player1.getHandCards();
		currentPlayer.setHandCards(this.player2.getHandCards());
		targetPlayer.setHandCards(tempCards);
	}
	
	public void rollBackExchangeHands(ExchangeHands exchangeHands) {
		System.out.println("三回合已結束, 玩家 "+exchangeHands.player1.getName()+" 與 "+"玩家 "+exchangeHands.player2.getName()+"手牌互換");
		List<Card> tempCards = new ArrayList<Card>();
		tempCards = exchangeHands.player1.getHandCards();
		exchangeHands.player1.setHandCards(player2.getHandCards());
		exchangeHands.player1.exchanging=false;
		exchangeHands.player2.setHandCards(tempCards);
		exchangeHands.player2.exchanging=false;
	}

	public int getExchangeRound() {
		return exchangeRound;
	}

	public void setExchangeRound(int exchangeRound) {
		this.exchangeRound = exchangeRound;
	}

}
