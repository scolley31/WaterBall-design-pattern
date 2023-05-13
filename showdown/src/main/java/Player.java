import java.util.ArrayList;
import java.util.List;

abstract public class Player {
    private String name;
    private boolean isHuman;
    public static final int PLAYER_CARDS_SIZE = 13;
    private List<Card> hands = new ArrayList<>(PLAYER_CARDS_SIZE);
    private boolean isExchanged = false;
    private int score;
    private ExchangeHands exchangeHand;
    abstract Card showCard();

    public ExchangeHands getExchangeHand() {
        return exchangeHand;
    }

    public List<Card> getHands() {
        return hands;
    }

    public void setHands(List<Card> hands) {
        this.hands = hands;
    }

    public Player(boolean isHuman, String name) {
        this.isHuman = isHuman;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setIsHuman(boolean b) {
        this.isHuman = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExchanged() {
        return isExchanged;
    }

    public void setExchanged(boolean exchanged) {
        isExchanged = exchanged;
    }

    public void addHandCard(Card card) {
        hands.add(card);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addPoint() {
        this.score += 1;
    }

    public void exchangeHandWith(Player _player) {
        setExchanged(true);
        exchangeHand = new ExchangeHands(this, _player);
        exchangeHand.swapHands();
    }
}
