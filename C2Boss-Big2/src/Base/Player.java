package Base;

import Base.CardPatternType.FullHouse;
import Base.CardPatternType.Pair;
import Base.CardPatternType.Single;
import Base.CardPatternType.Straight;
import CardPatternHandle.CardPatternHandler;
import Strategy.PlayCardStrategy;

import java.util.*;

import static Utils.Utils.printf;
import static java.util.stream.Collectors.toList;

public abstract class Player {
    private String name;
    private final PlayCardStrategy playCardStrategy;
    private final CardPatternHandler cardPatternHandler;
    private HandCards handCards = new HandCards();
    private boolean isPass = false;

    public Player(PlayCardStrategy playCardStrategy, CardPatternHandler cardPatternHandler) {
        this.playCardStrategy = playCardStrategy;
        this.cardPatternHandler = cardPatternHandler;
    }

    public void play(Round round) {
        if (round.getTopPlay() == null) System.out.println("新的回合開始了。");
        System.out.printf("輪到%s了\n", this.getName());
        printHandCards();
        CardPattern convertToCardPattern = Optional.ofNullable(cardPatternHandler.convertCardPattern(askPlayerChooseCards()))
                                                   .orElse(new Single(Arrays.asList(new Card(Card.Rank.THREE, Card.Suit.CLUB))));
        CardPattern cardPattern = playCardStrategy.play(round, this, convertToCardPattern);
        if (!round.checkIsValidOperation(cardPattern, this)) {
            play(round);
        } else {
            showCardPattern(cardPattern);
            removePlayedCards(cardPattern);
            round.update(cardPattern, this);
        }
    }

    private void removePlayedCards(CardPattern cardPattern) {
        if (cardPattern != null) {
            List<Card> cards = cardPattern.getCards();
            getHandCards().removeAll(cards);
        }
    }

    public void addCardToHandCards(Card... card) {
        handCards.addCard(card);
    }

    protected void nameHimself() {
        Scanner scanner = new Scanner(System.in);
        String inputName = scanner.nextLine();
        setName(inputName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean pass) {
        isPass = pass;
    }

    public List<Card> getHandCards() {
        return handCards.getCards();
    }


    protected void printHandCards() {
        handCards.sort();
        for (int i = 0; i < this.getHandCards().size(); i++) {
            System.out.printf("%-7d", i);
        }
        System.out.println();
        for (int i = 0; i < this.getHandCards().size(); i++) {
            System.out.printf("%-7s", this.getHandCards().get(i).toString());
        }
        System.out.println();
    }

    public List<Card> askPlayerChooseCards() {
        List<Card> cards = new ArrayList<>();
        boolean isPass = false;
        if (this instanceof HumanPlayer && !this.isPass) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] index = input.split(" ");
            for (String s : index) {
                if (s.equals("-1")) {
                    isPass = true;
                }
            }
            if (isPass) {
                this.setPass(true);
            } else
                cards = Arrays.stream(index)
                        .map(Integer::parseInt)
                        .collect(toList())
                        .stream()
                        .map(this.getHandCards()::get)
                        .collect(toList());
        }
        return cards;
    }

    private void showCardPattern(CardPattern cardPattern) {
        if (cardPattern == null) {
            if (this.isPass())
                printf("玩家 %s PASS.\n", this.getName());
        } else {
            List<Card> cards = cardPattern.getCards();
            String cardPatternType = "";
            if (cardPattern instanceof Single) {
                cardPatternType = "單張";
            } else if (cardPattern instanceof Pair) {
                cardPatternType = "對子";
            } else if (cardPattern instanceof FullHouse) {
                cardPatternType = "葫蘆";
            } else if (cardPattern instanceof Straight) {
                cardPatternType = "順子";
            }
            printf("玩家 %s 打出了 %s %s\n", this.getName(), cardPatternType, cards.toString().substring(1, cards.toString().length() - 1));
        }
    }
}
