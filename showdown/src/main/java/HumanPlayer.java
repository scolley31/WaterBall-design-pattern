import java.util.Scanner;
import java.util.stream.IntStream;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(true, name);
    }

    @Override
    Card showCard() {
        IntStream.range(0, getHands().size())
                .forEach(index -> {
                    Card card = getHands().get(index);
                    System.out.println("Index: " + index + ". HandCard : " + card.rank().getValue() + card.suit().getValue());
                });
        System.out.println("player: " + getName() + " print witch card you want to show(index) :");
        int num = inputIntByUser();
        Card showCard =  getHands().get(num);
        getHands().remove(showCard);
        return showCard;
    }

    private int inputIntByUser() {
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }
}
