package com.yangjun.c2b.big2;

public class TurnDecison {
	private final boolean isPass;
	private final Player player;
	private final CardPattern cardPattern;
	
	public TurnDecison(Player player, boolean isPass, CardPattern cardPattern) {
		this.player = player;
		this.cardPattern = cardPattern;
		this.isPass = isPass;
	}
	
	public Player getPlayer() {
		return player;
	}

	public boolean isPass() {
		return isPass;
	}
	
	public CardPattern getCardPattern() {
		return cardPattern;
	}
	
	public static TurnDecison pass(Player player) {
		return new TurnDecison(player, true, null);
	}
}
