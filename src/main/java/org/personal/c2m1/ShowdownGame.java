package org.personal.c2m1;

import java.util.List;
/* 這款遊戲能支援四位玩家P1, P2, P3, P4
 * 撲克牌比大小遊戲 (ShowdownGame)
 * 遊戲中有一副牌堆 (Deck)。
 */
public class ShowdownGame {
	private	List<Player> playerList ;//玩家列表

	private	Deck deck ;
	public ShowdownGame() {
	}

	public void start(List<Player> players) {
		players.stream().forEach(player -> createPlayerName(player));
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
	  for (int i=1 ; i<=playerCount ; i++){
			playerList.add(new HumanPlayer());
		}
		return playerList;
	}
}
