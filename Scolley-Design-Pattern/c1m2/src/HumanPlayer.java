import java.util.Random;
import java.util.Scanner;

public class HumanPlayer extends Player implements CommandLineInterFace {

    @Override
    void nameHimself() {
        System.out.print("請輸入你的姓名: ");
        name = inputStringByUser();
    }

    @Override
    Card showCard() {
        hand.getCardList().forEach(card ->
                System.out.println("HandCard : Rank = " + card.getRank() + " Suit = " + card.getSuit())
        );
        System.out.println("玩家: " + name + " 手上有這些牌，請輸入你要打第幾張牌");
        int num = inputIntByUser();
        Card showCard =  hand.getCardList().stream().skip(num).findFirst().orElse(null);
        hand.getCardList().remove(showCard);
        return showCard;
    }

    @Override

    void makeExchangeHandsDecision() {

        exchangeHands.forEach(ExchangeHands::turnDown);

        //是否已經換過牌
        if (isAlreadyExchangeHands()) return;

        System.out.print(this.getName()+"要換牌嗎? (1) 換 (2) 不換 : ");

        //輸入決定是否要換牌
        boolean wantExchange = false;
        if (inputIntByUser() == 1) {
            wantExchange = true;
        } else if (inputIntByUser() == 2){
            wantExchange = false;
        } else {
            System.out.print(this.getName()+"不懂你要幹嘛");
            makeExchangeHandsDecision();
        }

        if (wantExchange) {
            //隨機決定一位玩家換牌
            alreadyExchangeHands = true;

            int num = (int) (Math.random() * (otherPlayers.size()));
            Player exchangee = otherPlayers.stream().skip(num).findFirst().orElse(null);
            System.out.println("player "+this.getName()+" 選擇 player "+exchangee.getName()+" 換牌");
            exchangeHands(exchangee);

        } else {
            System.out.println("player "+this.getName()+" 決定不換牌");
        }



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

    @Override
    public boolean inputBooleanByUser() {
        Scanner s = new Scanner(System.in);
        return s.nextBoolean();
    }
}
