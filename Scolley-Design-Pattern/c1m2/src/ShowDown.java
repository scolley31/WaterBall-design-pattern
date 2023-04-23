import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;

public class ShowDown {

    private static final int PLAYER_NUMBER = 4;
    private static final int HAND_CARD_NUMBER = 13;
    private static final int TURN_NUMBER = 13;
    private int turn = 0;
    private Deck deck;
    private Collection<Player> players = new HashSet<>();
    private Player winner;
    public void start() {

        //初始化牌堆與玩家
        initPlayers();
        initDeck();

        //遊戲開始
        playersNameThemSelves();
        deckShuffle();

        //抽牌階段
        drawCardUntilHandEqualThirteen();

        //每回合
        showCardToCompareUntilTurnToThirteen();

        //遊戲結束
        showWinner();
    }

    private void initPlayers() {
        for (int i = 0; i < PLAYER_NUMBER; i++) {
            Player player = new AIPlayer();
            addPlayer(player);
        }
        System.out.println("Already init players, total member is "+players.size());
    }

    private void initDeck() {
        deck = new Deck();
    }

    public void addPlayer(Player player) {
        players.forEach( p -> {
                if (players != null) p.otherPlayers.add(player);
        });
        players.add(player);
        player.setShowDown(this);
    }

    public void playersNameThemSelves() {
        players.forEach(Player::nameHimself);
    }

    public void deckShuffle() {
        deck.shuffle();
    }

    public void drawCardUntilHandEqualThirteen() {
        players.forEach(player -> {
            while (player.getHand().size() < HAND_CARD_NUMBER) {
                Card card = deck.drawCard();
                player.addHandCard(card);
            }

            Collection<Card> cardList = player.hand.getCardList();
            cardList.forEach(card ->
                    System.out.println("Name :"+player.getName()+" HandCard : Rank = " +card.getRank() + " Suit = " + card.getSuit())
            );
            System.out.println("player "+player.getName()+" already draw card");
        });
    }

    private void showCardToCompareUntilTurnToThirteen() {
        while (turn < TURN_NUMBER) {

            turn++;
            System.out.println("第 "+turn+" 局遊戲");

            //每個玩家輪流take a turn
            players.forEach(Player::takeTurn);

            //每個玩家顯示出的牌內容，比大小
            Card maxShowCard = null;
            for (Player player: players) {
                System.out.println("player: " + player.getName()+" showCard: Rank = "+player.showCard.getRank()+" Suit = "+player.showCard.getSuit());
                maxShowCard = Card.bigThan(maxShowCard, player.showCard);
            }

            //最勝者point加一
            for (Player player: players) {
                if (player.showCard.equals(maxShowCard)) player.addPoint();
                System.out.println("Player: "+player.getName()+" point: "+player.getPoint());
            }

        }
    }

    private void showWinner() {
        winner = players.stream().max(Comparator.comparingInt(Player::getPoint)).get();
        System.out.println("Winner's name " + winner.getName());
    }


}
