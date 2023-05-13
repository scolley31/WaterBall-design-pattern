package designpattern;

import java.util.Scanner;

public class HumanPlayer extends Player {

	@Override
	public void nameHimself(String name) {
		this.name = name;
	}

	@Override
	public Card show(Scanner scanner) {
	    System.out.println("請輸入要打的牌?");
	    Card selectCard = null;
	    while (scanner.hasNext()) {
	        String input = scanner.nextLine();
	        if (!input.isEmpty()) {
	            int index = Integer.valueOf(input);
	            selectCard = this.getHandCards().get(index);
	            this.getHandCards().remove(index);
	            System.out.println("你選擇: " + selectCard.getSuit().getSymbol() + selectCard.getRank().getSymbol());
	            break;
	        }
	    }
	    return selectCard;
	}

}
