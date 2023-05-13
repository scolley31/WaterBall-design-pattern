package designpattern;

import java.util.Random;
import java.util.Scanner;

public class AIPlayer extends Player {

	@Override
	public void nameHimself(String name) {
		this.commandLine = false;
		this.name = name;
	}
	
	@Override
	public Card show(Scanner scanner) {
		int index = new Random().nextInt(13);
		Card selectCard=this.getHandCards().get(index);
		this.getHandCards().remove(index);
		System.out.println("你選擇: "+selectCard.getSuit().getSymbol()+selectCard.getRank().getSymbol());
		return selectCard;
	}

}
