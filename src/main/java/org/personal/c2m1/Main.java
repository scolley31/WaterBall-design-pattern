package org.personal.c2m1;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		ShowdownGame game= new ShowdownGame();
		//遊戲依照以下 1~4 的流程進行：
		List<Player> players = game.createPlayer(4);
		game.start(players);//請 P1~P4 為自己取名 (Name himself)。
		Deck deck = new Deck();
		deck.shuffle();//牌堆會進行洗牌 (Shuffle)。

		//b抽牌階段:直到所有人都擁有手牌 (Hand) 13 張牌為止。
		for (Player player : players){
			List<Card> playerHandCard = player.getHandCard();
			player.drawCard(deck); //抽牌'
			//player.getHand().stream().forEach(System.out::print);


		}







	}


}
