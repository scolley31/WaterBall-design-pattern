import java.util.Optional;
import java.util.Scanner;

public class HumanPlayer extends Player {
    protected HumanPlayer(String name) {
        super(name);
    }

    public void nameHimself() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        this.setName(name);
    }

    @Override
    public Card showCard() {
        if (this.getHand().getCards().size() > 0) {
            System.out.println(this.getHand().getCards().toString());
            int index = playerPickCardIndex();

            if (index < 1 || index > this.getHand().getCards().size()) {
                System.out.println("Invalid input, please try again.");
                return showCard();
            }
            Card card = this.getHand().getCards().get(index - 1);

            this.getHand().removeCard(card);
            return card;
        }
        return null;
    }

    private static int playerPickCardIndex() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the index of the card you want to play:");
        int index = scanner.nextInt();
        return index;
    }
}
