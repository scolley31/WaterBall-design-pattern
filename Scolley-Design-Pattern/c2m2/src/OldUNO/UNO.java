package OldUNO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class UNO {

    private static final int PLAYER_NUMBER = 4;
    private static final int HAND_CARD_NUMBER = 5;
    private Collection<Player> players = new HashSet<>();
    private Deck deck;
    private Player winner;
    private Card cardOnTable;
    private ArrayList<Card> alreadyShowCard = new ArrayList<>();

    public void start() {

        //初始化牌堆與玩家
        initPlayers();
        initDeck();

        //遊戲開始
        playersNameThemSelves();
        deckShuffle();

        //抽牌階段
        drawCardUntilHandEqualFive();

        //遊戲執行
        cardOnTable = deck.drawCard();
        showCardToCompareUntilSomeoneWin();


    }

    private void showCardToCompareUntilSomeoneWin() {

        for (Player player: players) {
            if (player.canShowCard()) {
                System.out.println("CardOnTable "+ getCardOnTable());
                alreadyShowCard.add(cardOnTable);
                cardOnTable = player.showCard();
            } else {
                if (deck.getCardList().size() != 0) {
                    player.addHandCard(deck.drawCard());
                }
                deck.addAlreadyShowCardAndShuffle(alreadyShowCard);
            }

            if (player.getHand().size() == 0) {
                winner = player;
                showWinner();
                return;
            }

        }

        showCardToCompareUntilSomeoneWin();

    }

    private void drawCardUntilHandEqualFive() {

        players.forEach(player -> {
            while (player.getHand().size() < HAND_CARD_NUMBER) {
                Card card = deck.drawCard();
                player.addHandCard(card);
            }

            Collection<Card> cardList = player.hand.getCardList();
            cardList.forEach(card ->
                    System.out.println("Name :"+player.getName()+" HandCard : OldUNO.Color = " +card.color() + " OldUNO.Number = " + card.number())
            );
            System.out.println("player "+player.getName()+" already draw card");
        });

    }

    private void deckShuffle() {
        deck.shuffle();
    }

    private void playersNameThemSelves() {
        players.forEach(Player::nameHimself);
    }

    private void initDeck() {
        deck = new Deck();
    }

    private void initPlayers() {

        addPlayer(new AIPlayer());
        addPlayer(new AIPlayer());
        addPlayer(new AIPlayer());
        addPlayer(new AIPlayer());

        System.out.println("Already init players, total member is " + players.size());
        players.forEach(player ->
                System.out.println("player:" + player.getName())
        );
    }

    public Card getCardOnTable() {
        return cardOnTable;
    }

    private void addPlayer(Player player) {
        players.add(player);
        player.setUNO(this);
    }

    private void showWinner() {
        System.out.println("Winner's name " + winner.getName());
    }

}