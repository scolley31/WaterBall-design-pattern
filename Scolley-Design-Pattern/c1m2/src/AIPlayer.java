import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

public class AIPlayer extends Player {

    @Override
    void nameHimself() {
        name = String.valueOf(this.hashCode());
        System.out.println("AI Player name himself, name is "+name);
    }

    @Override
    Card showCard() {
        int num = (int) (Math.random() * (hand.size()));
        System.out.println("name "+name+" 手上還有 "+hand.size()+" 張牌");
        Card showCard =  hand.getCardList().stream().skip(num).findFirst().orElse(null);
        hand.getCardList().remove(showCard);
        return showCard;
    }

    @Override
    void makeExchangeHandsDecision() {

        //每回合exchangeHands的倒數回合數減一，並在倒數回合數為零時換回來。
        exchangeHands.forEach(ExchangeHands::turnDown);

        //移除倒數回合數為0的exchangeHands
        exchangeHands.removeIf( exchangeHands1 -> exchangeHands1.getTurnCountDown() == 0);

        //是否已經換過牌
        if (isAlreadyExchangeHands()) return;

        //隨機決定是否要換牌
        boolean wantExchange = new Random().nextBoolean();

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

}
