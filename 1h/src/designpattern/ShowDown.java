package designpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class ShowDown {
	private List<Player> players;

	// 每場遊戲都會有一副牌堆
	private Deck deck;

	public void start(ShowDown showDown) {

		// 玩家初始化及取名
		List<Player> players = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		for (int i = 1; i <= 4; i++) {
			System.out.print("請輸入P" + i + "的名稱:");
			String playerName = scanner.nextLine();
			if (playerName.isEmpty()) {
				playerName = "AIPlayer" + i;
				Player player = new AIPlayer();
				player.nameHimself(playerName);
				player.setShowDown(this);
				players.add(player);
			} else {
				Player player = new HumanPlayer();
				player.nameHimself(playerName);
				player.setShowDown(this);
				players.add(player);
			}
			System.out.println(playerName + " 已加入遊戲");
		}

		this.players = players;

//		System.out.println(this.deck.getCards().get(0).getSuit().getSymbol()+this.deck.getCards().get(0).getRank());

		// 牌堆洗牌
		this.deck.shuffle();

		// P1~P4 輪流從牌堆中抽牌
		for (int i = 1; i <= 13; i++) {
			for (Player player : players) {
				List<Card> cards = player.getHandCards();
				cards.add(this.deck.drawCard());
			}
		}

		// P1~P4 輪流 (Takes a turn)
		for (int round = 1; round <= 13; round++) {
			System.out.println("第" + round + "回合");
			LinkedHashMap<Player, Card> selectCards = new LinkedHashMap<>();
			for (Player player : players) {
				Card card = player.takesTurn(scanner, player, round);
				selectCards.put(player, card);
			}
			// 顯示 P1~P4 各出的牌的內容
			showCard(selectCards);
			// 進行比大小決勝負
			compare(selectCards);
		}
		System.out.println("此局遊戲結束");
		System.out.println("勝者:" + showWiner());
		scanner.close();
	}

	public ShowDown() {
		// 牌堆初始化
		this.deck = new Deck();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public void showCard(LinkedHashMap<Player, Card> selectCards) {
		System.out.println("此回合結束");
		for (Map.Entry<Player, Card> entry : selectCards.entrySet()) {
			String name = entry.getKey().getName();
			Card card = entry.getValue();
			System.out.println("玩家 " + name + ": " + card.getSuit().getSymbol() + card.getRank().getSymbol());
		}
	}

	public void compare(LinkedHashMap<Player, Card> selectCards) {
		int maxRank = 0;
		int maxSuit = 0;
		Player winer = null;
		for (Map.Entry<Player, Card> entry : selectCards.entrySet()) {
			int rank = entry.getValue().getRank().getNumber();
			int suit = entry.getValue().getSuit().getNumber();

			if (rank > maxRank || (rank == maxRank && suit > maxSuit)) {
				maxRank = rank;
				maxSuit = suit;
				winer = entry.getKey();
			}
		}
		if (winer != null) {
			System.out.println("玩家 " + winer.getName() + " +1分");
			winer.setScore(winer.getScore() + 1);
		}
	}

	public String showWiner() {
		List<Player> players = getPlayers();
		int maxScore = 0;
		Player winer = null;
		for (Player player : players) {
			int score = player.getScore();
			if (score > maxScore) {
				maxScore = score;
				winer = player;
			}
		}
		for (Player player : players) {
			System.out.println(player.getName() + " 最後得分: " + player.getScore() + " ");
		}
		return winer.getName();
	}

}
