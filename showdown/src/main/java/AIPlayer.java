import java.util.Random;

public class AIPlayer extends Player {
    public AIPlayer(String name) {
        super(false, name);
    }

    @Override
    Card showCard() {
        Random random = new Random();
        int num = random.nextInt(getHands().size());
        Card showCard =  getHands().get(num);
        getHands().remove(showCard);
        return showCard;
    }
}
