package com.yangjun.c2b.big2;

import java.util.ArrayList;
import java.util.List;

public class Round {
	private int passCount = 0;
	private Player topPlayer;
	private final List<TurnDecison> turnDecisons = new ArrayList<>();
	
	public void addPassCount() {
		passCount++;
	}
	
	public int getPassCount() {
		return passCount;
	}
	
	public void resetPassCount() {
		passCount = 0;
	}
	
	public Player getTopPlayer() {
		return topPlayer;
	}
	
	public void setTopPlayer(Player topPlayer) {
		this.topPlayer = topPlayer;
	}
	
	public boolean isRoundOver() {
		return passCount > 2;
	}

	public boolean isFirstTurn() {
		return turnDecisons.isEmpty();
	}
	
	public List<TurnDecison> getTurnDecisons() {
		return turnDecisons;
	}
	
	public void addTurnDecison(TurnDecison turnDecison) {
		turnDecisons.add(turnDecison);
	}
}
