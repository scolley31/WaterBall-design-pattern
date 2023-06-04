package UNO;

import java.util.List;

public record Card(Color color, Number number) {

    public static boolean isTheSameType(Card c1, Card c2) {
        if (c1 == null || c2 == null) throw new IllegalArgumentException("沒有牌可以比大小") ;
        return (c1.number == c2.number || c1.color == c2.color);
    }

}
