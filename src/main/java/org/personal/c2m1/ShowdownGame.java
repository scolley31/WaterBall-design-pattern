package org.personal.c2m1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 這款遊戲能支援四位玩家P1, P2, P3, P4
 * 撲克牌比大小遊戲 (ShowdownGame)
 * 遊戲中有一副牌堆 (Deck)。
 */
public class ShowdownGame {
	private	List<Player> playerList ;//玩家列表
	private int round;//回合
	private Map<Player,Integer> scorePoint;//分數

	public Integer getRound() {
		return round;
	}

	public ShowdownGame() {
		this.round=13;
	}

	public void start(List<Player> players) {
		players.stream().forEach(player -> createPlayerName(player));
	}
	//P1~P4 輪流 (Takes a turn) 依序執行以下：
	public void play(Player player) {
		Boolean isUsedPrivilege = isUsedPrivilege(player);
		if(isUsedPrivilege && player.exchangeHandsPrivilege>0){
			player.exchangeHandsPrivilege=0;

		}else {

		}
	}

	private Boolean isUsedPrivilege(Player player) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(player.getName()+"，是否使用「交換手牌 (Exchange Hands)」:Y/N");
		String privilegeUsed = "N";
		try {
			privilegeUsed = br.readLine();
			while (!(privilegeUsed.matches("(?i)^(?:y|n|Y|N)$"))){
				System.out.println("請輸入Y or N .");
				privilegeUsed = br.readLine();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return "y".equalsIgnoreCase(privilegeUsed)?Boolean.TRUE :Boolean.FALSE;
	}

	public void scorePoint(List<Player> players) {

	}
	//a玩家選擇要與哪位玩家（自己以外）交換手牌。
	//b選擇後，雙方的手牌交換。
	//c三回合後，雙方的手牌會交換回來。
	private	void exchangeHands() {

	}

	private	void createPlayerName(Player player) {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("請為自己取名 (Name himself):");
//		String sName = "";
//		try {
//			sName = br.readLine();
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//		player.setName(sName);
	}
	public List<Player> createPlayer(int playerCount) {
		playerList=new ArrayList<>(playerCount);
	  for (int i=1 ; i<=playerCount ; i++){
			playerList.add(new HumanPlayer());
		}
		return playerList;
	}
}
