import java.util.*;

public class ShowDown {
    private int maxRound = 13;
    private Deck deck;
    private final Set<Player> players;

    public ShowDown(Deck deck, Set<Player> player) {
        this.deck = deck;
        this.players = player;
    }

    public void start() {
        playersNameHimself();
        shuffleTheDeck();
        playersDrawCard();
        playGame();
        showWinner();
    }

    private void shuffleTheDeck() {
        deck.shuffle();
    }

    private void showWinner() {
        this.players.stream()
                .max(Comparator.comparingInt(Player::getPoint))
                .ifPresent(player -> {
                    System.out.println(player.getName() + " is the winner!");
                });
    }

    private void playersDrawCard() {
        for (Player player : players) {
            deck.drawCard(player);
        }
    }

    private void playersNameHimself() {
        for (Player player : players) {
            player.nameHimself();
        }
    }

    private void playGame() {
        for (int i = 0; i < maxRound; i++) {
            Map<Player, Card> turnInformationMap = new HashMap<>();
            for (Player player : players) {
                askPlayerExchageHands(player);
            }
            for (Player player : players) {
                playerShowCard(turnInformationMap, player);
                player.checkExchangeHandRound();
            }
            showTurnCards(turnInformationMap);
            winnerGainPoint(turnInformationMap);
        }
    }

    private static void playerShowCard(Map<Player, Card> turnInformationMap, Player player) {
        Card card = player.takeTurn();
        turnInformationMap.put(player, card);
    }

    private void askPlayerExchageHands(Player player) {
        if (player.isHasSwapPrivilege() && player instanceof HumanPlayer) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(player.getName() + ", do you want to exchange hands? (yes/no)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                Player otherPlayer = selectOtherPlayer(player);
                player.exchangeHands(otherPlayer);
            }
        }
    }

    private Player selectOtherPlayer(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(player.getName() + ", please select another player to exchange hands with: ");
        for (Player p : players) {
            if (!p.equals(player)) {
                System.out.println(p.getName());
            }
        }
        String input = scanner.nextLine();
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(input) && !p.equals(player)) {
                return p;
            }
        }
        System.out.println("Invalid input, please try again.");
        return selectOtherPlayer(player);
    }

    private void winnerGainPoint(Map<Player, Card> informationMap) {
        informationMap.values().stream()
                .max((c1, c2) -> c1.compare(c2) ? 1 : -1)
                .ifPresent(card -> {
                    for (Map.Entry<Player, Card> entry : informationMap.entrySet()) {
                        if (entry.getValue().equals(card)) {
                            entry.getKey().gainPoint();
                            System.out.println(entry.getKey().getName() + " wins this round!");
                        }
                    }
                });
    }

    private void showTurnCards(Map<Player, Card> turnCards) {
        for (Map.Entry<Player, Card> entry : turnCards.entrySet()) {
            System.out.println(entry.getKey().getName() + " plays " + entry.getValue().toString());
        }
    }
}