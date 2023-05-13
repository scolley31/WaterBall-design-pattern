package designpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player {
	private List<Card> handCards;
	protected String name;
	private int score = 0;
	// 依此判斷電腦還是玩家
	protected boolean commandLine = true;
	// 是否在交換手牌
	protected boolean exchanging = false;
	// 是否已使用特權
	private boolean privilege = false;
	private ExchangeHands exchangeHands;

	private ShowDown showDown;

	public ShowDown getshowDown() {
		return showDown;
	}

	public void setShowDown(ShowDown showDown) {
		this.showDown = showDown;
	}

	public Player() {
		List<Card> handCards = new ArrayList<Card>();
		this.handCards = handCards;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isCommandLine() {
		return commandLine;
	}

	public void setCommandLine(boolean commandLine) {
		this.commandLine = commandLine;
	}

	public String getName() {
		return name;
	}

	public abstract void nameHimself(String name);

	public abstract Card show(Scanner scanner);

	public Card takesTurn(Scanner scanner, Player currentPlayer, int round) {

		// 若是人類玩家才判斷是否使用特權
		if (currentPlayer.commandLine == true) {
			// 三回合後, 雙方手牌交換回來
			ExchangeHands exchangeHands = currentPlayer.getExchangeHands();
			if (exchangeHands != null && round - exchangeHands.getExchangeRound() == 3) {
				exchangeHands.rollBackExchangeHands(exchangeHands);
			}

			// 人類玩家決定是否使用特權
			System.out.println("玩家 " + currentPlayer.getName() + " 是否要使用交換手牌特權?");
			String decide = scanner.nextLine();
			if (decide.equals("yes") && currentPlayer.privilege == false) {
				List<Player> tempPlayers = new ArrayList<>(currentPlayer.getshowDown().getPlayers());
				tempPlayers.remove(currentPlayer);

				List<Player> playersToRemove = new ArrayList<>();
				for (Player player : tempPlayers) {
					if (player.exchanging) {
						playersToRemove.add(player);
					}
				}
				tempPlayers.removeAll(playersToRemove);

				if (!tempPlayers.isEmpty()) {
					System.out.println("可交換手牌的玩家名稱列表:");
					for (Player player : tempPlayers) {
						System.out.println(player.getName());
					}
					System.out.println("請輸入欲交換手牌的玩家名稱");
					String targetPlayerName = scanner.nextLine();
					for (Player player : currentPlayer.getshowDown().getPlayers()) {
						if (player.getName().equals(targetPlayerName)) {
							System.out.println("與玩家 " + player.getName() + " 交換手牌");

							System.out.println("交換前 " + this.getName() + " 的手牌: " + this.showHandCards());

							setExchangeHands(new ExchangeHands(currentPlayer, player, round));

							System.out.println("交換後 " + this.getName() + " 的手牌: " + this.showHandCards());

							currentPlayer.privilege = true;
							currentPlayer.exchanging = true;
							player.exchanging = true;
							break;
						}
					}
				} else {
					System.out.println("當前沒有可交換手牌的玩家");
				}
			} else if (decide.equals("yes") && currentPlayer.privilege == true) {
				System.out.println("你已使用過此特權");
			}
		}
		// 從手牌中選擇要出的牌
		System.out.println("玩家 " + this.getName() + " 的手牌: " + this.showHandCards());
		return currentPlayer.show(scanner);
	}

	public List<Card> getHandCards() {
		return handCards;
	}

	public void setHandCards(List<Card> handCards) {
		this.handCards = handCards;
	}

	public ExchangeHands getExchangeHands() {
		return exchangeHands;
	}

	public void setExchangeHands(ExchangeHands exchangeHands) {
		this.exchangeHands = exchangeHands;
	}

	protected StringBuilder showHandCards() {
		StringBuilder cardsString = new StringBuilder();
		List<Card> cards = this.getHandCards();
		for (Card card : cards) {
			cardsString.append(card.getSuit().getSymbol() + card.getRank().getSymbol() + " ");
		}
		return cardsString;
	}

}
