package uno;
import java.util.ArrayList;
import java.util.List;

public class Card  {
    private Color color;
    private Number number;
    private Deck deck;

    public enum Color {
        BLUE,
        RED,
        YELLOW,
        GREEN,
    }

    public enum Number {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
    }

    public Card(Color color, Number number, Deck deck) {
        this.color = color;
        this.number = number;
        this.deck = deck;
    }

    public Color getColor() {
        return color;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return color.toString() +" "+ number.toString();
    }
    public boolean isValidCard(Card card){
        return this.getColor().equals(card.getColor()) || this.getNumber().equals(card.getNumber());
    }
}
