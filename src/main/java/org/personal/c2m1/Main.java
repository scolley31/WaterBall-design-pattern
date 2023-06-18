package org.personal.c2m1;

import java.util.List;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		ShowdownGame game= new ShowdownGame();
		//遊戲依照以下 1~4 的流程進行：
		//a.遊戲開始時，依序執行以下：
		List<Player> players = game.createPlayer(4);
		game.start(players);//i.請 P1~P4 為自己取名 (Name himself)。
		Deck deck = new Deck();
		deck.shuffle();//ii.牌堆會進行洗牌 (Shuffle)。
		//b.抽牌階段:直到所有人都擁有手牌 (Hand) 13 張牌為止。
		drawCard(players, deck);
		//c.抽完牌後，在接下來的 13 回合中，每一回合依序執行以下：
		for(int round=1; round < game.getTotalAound(); round++){
			for (Player player : players){
				//決定要不要使用 「交換手牌 (Exchange Hands)」 特權，參見需求 5。
				//出 (S how) 一張牌（此步驟彼此皆無法知曉彼此出的牌）。
				//顯示 P1~P4 各出的牌的內容。
				Card card=game.play(player, round);
				System.out.println("回合:"+round+","+player.getName()+"出的牌的內容:"+card);
				//將 P1~P4 出的牌進行比大小決勝負，將最勝者的分數(Point)加一。
			}
		}
	}

	private static void drawCard(List<Player> players, Deck deck) {
		for (Player player : players){
			Stack<Card> playerHandCard = player.getHandCard();
			for(int i=0 ;playerHandCard.size() < 13; i++){
				player.drawCard(deck,playerHandCard); //抽牌'
			//player.getHand().stream().forEach(System.out::print);
			}
		}
	}


}
