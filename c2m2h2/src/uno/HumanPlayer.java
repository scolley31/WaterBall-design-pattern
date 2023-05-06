package uno;

import java.util.Scanner;

public class HumanPlayer extends Player {
    protected HumanPlayer(String name) {
        super(name);
    }
    @Override
    public void nameHimself() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        this.setName(name);
    }

    @Override
    protected Card showCard(Card card) {
        if (haveValidCard() && this.getHand().getHandSize() > 0) {
            System.out.println(this.getHand().getCards().toString());
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the index of the card you want to play:");
            int index = scanner.nextInt();

            if (index < 1 || index > this.getHand().getCards().size() || this.getHand().getCard(index).isValidCard(this.getUno().getTopCard())) {
                System.out.println("Invalid input, please try again.");
                return showCard(this.getUno().getTopCard());
            }
            uno.Card playCard = this.getHand().getCards().get(index - 1);
            this.getHand().removeCard(card);
            return playCard;
        }
        return null;
    }
}
