package designpattern;

public enum Rank {
	TWO(0, "2"), THREE(1, "3"), FOUR(2, "4"), FIVE(3, "5"), SIX(4, "6"), SEVEN(5, "7"), EIGHT(6, "8"), NINE(7, "9"),
	TEN(8, "10"), JACK(9, "11"), QUEEN(10, "12"), KING(11, "13"), ACE(12, "1");

	private int number;
	private String symbol;

	Rank(int number, String symbol) {
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
