package ShowDown;

import TemplateGame.CommandLineInterface;

import java.util.Scanner;

public class ShowDownHuman extends Player implements CommandLineInterface {
    @Override
    protected void nameHimself() {
        System.out.print("請輸入你的姓名: ");
        name = inputStringByUser();
    }

    @Override
    public Card showCard() {
        getHands().getHandCards().forEach(card ->
            System.out.println("HandCard : "+card)
        );
        System.out.println("玩家: " + name + " 手上有這些牌，請輸入你要打第幾張牌");
        int num = inputIntByUser();
        Card showCard =  getHands().getHandCards().stream().skip(num).findFirst().orElse(null);
        getHands().getHandCards().remove(showCard);
        return showCard;
    }

    @Override
    public String inputStringByUser() {
        Scanner s = new Scanner(System.in);
        return s.next();
    }

    @Override
    public int inputIntByUser() {
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }
}
