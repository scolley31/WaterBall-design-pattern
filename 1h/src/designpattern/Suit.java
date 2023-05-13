package designpattern;

public enum Suit {
	CLUB(0,"♣"), DIAMOND(1,"♦"), HEART(2,"♥"), SPADE(3,"♠"),;

	private int number;
	private String symbol;

	Suit(int number, String symbol) {
		this.number = number;
		this.symbol = symbol;
	}

	public int getNumber() {
		return number;
	}
	
	public String getSymbol() {
		return symbol;
	}

}
