import java.util.Random;

public class AIPlayer extends Player {

    @Override
    void nameHimself() {
        name = String.valueOf(this.hashCode());
        System.out.println("AI Player name himself, name is "+name);
    }

    @Override
    Card showCard() {
        int num = (int) (Math.random() * (hand.size()));
        Card showCard =  hand.getCardList().stream().skip(num).findFirst().orElse(null);
        hand.getCardList().remove(showCard);
        return showCard;
    }

    @Override
    void makeExchangeHandsDecision() {

        if (isAlreadyExchangeHands()) return;

        //隨機決定是否要換牌
        Boolean wantExchange = new Random().nextBoolean();

        if (wantExchange) {
            //隨機決定一位玩家換牌
            System.out.println("player "+this.getName()+" 決定要換牌");
            System.out.println("otherPlayers.size() "+otherPlayers.size());
            int num = (int) (Math.random() * (otherPlayers.size()));
            Player exchangee = otherPlayers.stream().skip(num).findFirst().orElse(null);
            System.out.println("player "+this.getName()+" exchangee "+ exchangee);
            System.out.println("player "+this.getName()+" 選擇 player "+exchangee.getName()+" 換牌");
            exchangeHands(exchangee);
            alreadyExchangeHands = true;
            System.out.println("otherPlayers.size() "+otherPlayers.size());
        } else {
            System.out.println("player "+this.getName()+" 決定不換牌");
        }
    }
}
