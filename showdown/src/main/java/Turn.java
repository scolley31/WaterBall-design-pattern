import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Turn {
    private List<Card> cards = new ArrayList<>();
    private HashMap<Card, Player> map = new HashMap<>();

    public void start(List<Player> players) {
        for (Player p : players) {
            ExchangeHands exchangeHands = p.getExchangeHand();
            if (p.isExchanged() && exchangeHands != null) {
                if (exchangeHands.getTurnCount() == ExchangeHands.TURN_COUNT_DOWN) {
                    exchangeHands.restoreHands();
                } else if (exchangeHands.getTurnCount() < ExchangeHands.TURN_COUNT_DOWN) {
                    exchangeHands.incTurnCount();
                }
            }
        }

        for (Player p : players) {
            if ((p instanceof HumanPlayer) && !p.isExchanged())
                askForExchange(p, players);
        }

        for (Player p : players) {
            Card c = p.showCard();
            map.put(c, p);
            cards.add(c);
        }
        sortedCards();
        addPointToWinner();
    }

    private void askForExchange(Player p, List<Player> players) {
        System.out.print("Are you want to exchange? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        if ("y".equalsIgnoreCase(scanner.next())) {
            System.out.print("pick another one player (1~3): ");
            int playerChoice = scanner.nextInt();
            if (playerChoice >= 1 && playerChoice <= 3) {
                List<Player> temp = players.stream().filter(player -> !player.equals(p))
                        .toList();
                p.exchangeHandWith(temp.get(playerChoice -1));
                System.out.println("you pick player :" + playerChoice + ". Exchanging...");
            }
        }
    }

    private void addPointToWinner() {
        Player p = map.get(cards.get(3));
        p.addPoint();
        System.out.println("this turn winner is :" + p.getName());
    }

    public void sortedCards() {
        cards.sort(Card::compareTo);
        System.out.println("this turn cards are :");
        for (Card card : cards) {
            System.out.print(" <" +  card.suit().getValue() + ">" + "[<" + card.rank().getValue() + ">]  ");
        }
        System.out.println();
    }
}
