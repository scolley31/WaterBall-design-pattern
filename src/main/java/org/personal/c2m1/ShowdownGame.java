package org.personal.c2m1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* 這款遊戲能支援四位玩家P1, P2, P3, P4
 * 撲克牌比大小遊戲 (ShowdownGame)
 * 遊戲中有一副牌堆 (Deck)。
 */
public class ShowdownGame {
	private	List<Player> playerList ;//玩家列表
	private int totalAound;//回合

	private Map<Player,Integer> scorePoint;//分數

	public int getTotalAound() {
		return totalAound;
	}

	public ShowdownGame() {
		this.totalAound=13;
	}

	public void start(List<Player> players) {
		players.stream().forEach(player -> createPlayerName(player));
	}
	//P1~P4 輪流 (Takes a turn) 依序執行以下：
	public Card play(Player player,int round) {
		Boolean isUsedPrivilege = player.isUsedPrivilege(player);
		Player playersSelected = null;
		if(isUsedPrivilege){
			player.exchangeHandsPrivilegeCount =0;
			player.setUsedPrivilegeRound(round); //回合
			//a玩家選擇要與哪位玩家（自己以外）交換手牌。
			playersSelected = player.choosePlayer2changeHandCard(player,playerList);
			//b選擇後，雙方的手牌交換。
			//System.out.println(round+":"+"1=12"+player.getHandCard());
			player.swapPlayerHandCard(player, playersSelected);
		}
		//c三回合後，雙方的手牌會交換回來。a
		if(player.exchangeHandsPrivilegeCount==0 & player.usedPrivilegeRound+3 ==round){
			player.swapPlayerHandCard(player, playersSelected);
		}
		//System.out.println(round+":"+"="+player.getHandCard());
		//2.出 (Show) 一張牌（此步驟彼此皆無法知曉彼此出的牌）。
		return player.playCard();
	}

	public void scorePoint(List<Player> players) {

	}

	private	void createPlayerName(Player player) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("請為自己取名 (Name himself):");
		String sName = "";
		try {
			sName = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		player.setName(sName);
	}
	public List<Player> createPlayer(int playerCount) {
		playerList=new ArrayList<>(playerCount);
	  for (int i=1 ; i<=playerCount ; i++){
			playerList.add(new HumanPlayer());
		}
		return playerList;
	}
}
