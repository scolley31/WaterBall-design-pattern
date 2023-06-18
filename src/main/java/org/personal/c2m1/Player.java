package org.personal.c2m1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public abstract class Player  {
	protected Stack<Card> handCard;
	protected Integer point;//分數
	protected int exchangeHandsPrivilegeCount;//交換手牌特權數
	protected String Name ;

	protected int usedPrivilegeRound;//回合

	public void setUsedPrivilegeRound(int round) {
		this.usedPrivilegeRound = round;
	}
	public int getUsedPrivilegeRound() {
		return usedPrivilegeRound;
	}

	public Player() {
		handCard = new Stack<Card>();
		point=0;
		exchangeHandsPrivilegeCount =1;
		Name="player";
	}
	public abstract Stack<Card> drawCard(Deck deck,Stack<Card> playerHandCard);
	public void setHandCard(Stack<Card> handCard) {
		this.handCard = handCard;
	}
	public Stack<Card> getHandCard() {
		return handCard;
	}
	public void setName(String name) {
		Name = name;
	}
	//出牌
	public Card playCard() {
		return this.handCard.pop();
	}
	public String getName() {
		return Name;
	}
	@Override
	public String toString() {
		return "Player{" + "handCard=" + handCard + ", point=" + point + ", exchangeHandsPrivilege="
				+ exchangeHandsPrivilegeCount + ", Name='" + Name + '\'' + '}';
	}

	public Boolean isUsedPrivilege(Player player) {
		String privilegeUsed = "N";
		if(player.exchangeHandsPrivilegeCount >0){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("玩家:"+player.getName()+"，是否使用「交換手牌 (Exchange Hands)」:Y/N");
			try {
				privilegeUsed = br.readLine();
				while (!(privilegeUsed.matches("(?i)^(?:y|n|Y|N)$"))){
					System.out.println("請輸入Y or N .");
					privilegeUsed = br.readLine();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return "Y".equalsIgnoreCase(privilegeUsed)?Boolean.TRUE :Boolean.FALSE;
	}

	public Player choosePlayer2changeHandCard(final Player player,final List<Player> playerList) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> playersName =playerList.stream().filter(p->!(player.getName().equalsIgnoreCase(p.getName())))
				.map(Player::getName).collect(Collectors.toList());
		System.out.println(player.getName()+"玩家選擇要與哪位玩家交換手牌:");
		playersName.forEach(playerName -> System.out.printf("%s,",playerName));
		String playersNameSelected = "";
		try {
			playersNameSelected = br.readLine();
			while (!(playersName.stream().map(String::toLowerCase).collect(Collectors.toList()).contains(playersNameSelected.toLowerCase()))){
				System.out.println("請輸入選擇要與哪位玩家交換手牌:");
				playersNameSelected = br.readLine();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String finalPlayersNameSelected = playersNameSelected;
		return playerList.stream().filter(p-> finalPlayersNameSelected.equalsIgnoreCase(p.getName())).findFirst().get();
	}
	public void swapPlayerHandCard(Player player, Player playersSelected) {
		Stack<Card> temp = new Stack<>();
		temp.addAll(playersSelected.getHandCard());
		playersSelected.setHandCard(player.getHandCard());
		player.setHandCard(temp);
	}
}
