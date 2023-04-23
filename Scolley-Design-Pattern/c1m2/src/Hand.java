import java.util.Collection;
import java.util.HashSet;

public class Hand {

    private Collection<Card> cardList = new HashSet<>();;

    public Collection<Card> getCardList() {
        return cardList;
    }

    public void setCardList(Collection<Card> cardList) {
        this.cardList = cardList;
    }
    public int size() {
        return cardList.size();
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

}
