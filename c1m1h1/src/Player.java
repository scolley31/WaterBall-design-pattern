import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

public abstract class Player {
    public enum PlayerId {
        P1,P2,P3,P4;
    }
    public enum Action {
        DRAW, SHOW, EMPTY_HAND, WINNER
    }

    private final PlayerId id;
    private List<Hand> hands = new ArrayList<>();
    private Game game;
    private String name;
    private int point = 0;
    private final ArrayDeque<ExchangeHands> exchangeHands = new ArrayDeque<>();
    private boolean hasExchangeHandsRight = true;
    public Player(Game game, PlayerId id) {
        this.id = id;
        this.game = game;
    }


    public abstract void nameSelf();
    public void draw(Deck deck) {
         hands.add(requireNonNull(deck).getHand());
    }
    public abstract Hand takeTurn();
    public abstract void exchangeHands();
    public abstract Hand showHand();
    public void gainPoint(){
        this.point++;
    }
    public void addExchangeHands(ExchangeHands exchangeHands) {
        this.exchangeHands.add(requireNonNull(exchangeHands));
    }
    public void removeExchangeHands(ExchangeHands exchangeHands) {
        this.exchangeHands.remove(exchangeHands);
    }

    public int getHandsCount() {
        return getHands().size();
    }

    //region Getter and Setter
    public PlayerId getId() {
        return id;
    }
    public List<Hand> getHands() {
        return hands;
    }
    public Game getGame() {
        return game;
    }
    public String getName() {
        return name;
    }
    public int getPoint() {
        return point;
    }
    public ArrayDeque<ExchangeHands> getExchangeHands() {
        return exchangeHands;
    }

    public void setHands(List<Hand> hands) {
        this.hands = requireNonNull(hands);
    }
    public void setGame(Game game) {
        this.game = requireNonNull(game);
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isHasExchangeHandsRight() {
        return hasExchangeHandsRight;
    }
    public void setHasExchangeHandsRight(boolean hasExchangeHandsRight) {
        this.hasExchangeHandsRight = hasExchangeHandsRight;
    }

    public void sortHands() {
        hands.sort(Collections.reverseOrder());
    }
    public void describeEmotion(Action action) {
        switch (action) {
            case DRAW -> {
                int randomEmotion = (int) (Math.random() * 25); // 隨機選擇25種情緒
                String emotion = switch (randomEmotion) {
                    case 0 -> getName() + " 看著手中的牌，露出了滿意的微笑。";
                    case 1 -> getName() + " 皺了皺眉頭，似乎對手牌有些失望。";
                    case 2 -> getName() + " 眉飛色舞，看起來這把手氣很好。";
                    case 3 -> getName() + " 微微一笑，似乎對手牌感到滿意。";
                    case 4 -> getName() + " 表情冷靜，似乎早已預料到自己的手牌。";
                    case 5 -> getName() + " 眼中閃爍著興奮的光芒，似乎對手牌很滿意。";
                    case 6 -> getName() + " 表情有些無奈，似乎對手牌不太滿意。";
                    case 7 -> getName() + " 微微一笑，顯然對手牌感到滿意。";
                    case 8 -> getName() + " 看起來有些驚訝，似乎沒想到會有這些牌。";
                    case 9 -> getName() + " 看著手牌，額頭上冒出了細密的汗珠。";
                    case 10 -> getName() + " 頗為驚訝地瞪大了眼睛，看著自己的手牌。";
                    case 11 -> getName() + " 輕輕地歎了口氣，似乎對手牌感到很失望。";
                    case 12 -> getName() + " 見到手牌，一臉無奈地苦笑著。";
                    case 13 -> getName() + " 凝視著手牌，眼神中帶著一絲深思。";
                    case 14 -> getName() + " 忍不住笑了出來，顯然對手牌感到非常愉快。";
                    case 15 -> getName() + " 轉動著眼珠，似乎在思考下一步的策略。";
                    case 16 -> getName() + " 忽然放聲大笑，看來對手牌感到非常開心。";
                    case 17 -> getName() + " 凝視著牌，臉上露出了驚訝的表情。";
                    case 18 -> getName() + " 笑著拿著牌，似乎對手牌充滿了信心。";
                    case 19 -> getName() + " 表情凝重，似乎在思考極為複雜的戰術。";
                    case 20 -> getName() + " 看著牌，眉頭緊鎖，似乎在擔心接下來的局勢。";
                    case 21 -> getName() + " 笑著看著手牌，似乎對將要發生的事情充滿了期待。";
                    case 22 -> getName() + " 輕輕地嘆了口氣，似乎對手上的手牌感到擔憂。";
                    case 23 -> getName() + " 看著手牌，露出了自信的笑容。";
                    case 24 -> getName() + " 表情堅定，似乎在暗自下定決心。";
                    default -> getName() + " 滿臉堆笑，似乎對手牌感到非常滿意。";
                };
                System.out.println(emotion);
            }
            case SHOW -> {
                int randomEmotion = (int) (Math.random() * 25); // 隨機選擇25種情緒
                String emotion = switch (randomEmotion) {
                    case 0 -> getName() + " 耐心地思考著，然後慎重地出了一張牌。";
                    case 1 -> getName() + " 快速地拿起一張牌，毫不猶豫地出了出去。";
                    case 2 -> getName() + " 看著桌上的局勢，眉頭微微一皺，然後出了一張牌。";
                    case 3 -> getName() + " 笑著出了一張牌，似乎對自己的選擇很有信心。";
                    case 4 -> getName() + " 出牌的速度很快，顯示出他的自信和經驗。";
                    case 5 -> getName() + " 裝作鎮定，但眼中的光芒顯示出他的興奮。";
                    case 6 -> getName() + " 出了一張牌，臉上帶著微笑，看起來很滿意自己的選擇。";
                    case 7 -> getName() + " 看起來非常專注，出了一張似乎是最佳選擇的牌。";
                    case 8 -> getName() + " 拿著牌思索了片刻，然後自信地出了一張。";
                    case 9 -> getName() + " 神秘地笑著，悄悄地放下了一張牌，讓其他人猜不透。";
                    case 10 -> getName() + " 快速地瞥了一眼其他玩家，然後出了一張牌。";
                    case 11 -> getName() + " 看著其他人的牌，出了一張似乎能掌握局勢的牌。";
                    case 12 -> getName() + " 慢慢地出了一張牌，顯示出他的沉著和耐心。";
                    case 13 -> getName() + " 笑著出了一張牌，似乎在向其他玩家示好。";
                    case 14 -> getName() + " 裝作不經意地出了一張牌，實際上卻暗藏著深思熟慮的策略。";
                    case 15 -> getName() + " 蓋下一張牌，臉上充滿了自信，似乎在挑戰其他玩家。";
                    case 16 -> getName() + " 看了一眼其他玩家，然後用手指輕輕地放下了一張牌。";
                    case 17 -> getName() + " 看似毫不在意，實際上卻在用心地選擇每一張牌。";
                    case 18 -> getName() + " 蓋下一張牌，微笑著，不讓其他人看清楚他的手牌。";
                    case 19 -> getName() + " 出了一張牌，然後看著其他人，似乎在挑釁。";
                    case 20 -> getName() + " 淡定自若地放下了一張牌，毫不外露自己的情緒。";
                    case 21 -> getName() + " 用眼神交流，然後悄聲地放下了一張牌，保持神秘感。";
                    case 22 -> getName() + " 面無表情地出了一張牌，讓人無法窺探他的真正意圖。";
                    case 23 -> getName() + " 出了一張牌，然後沉思地看著桌上的局勢。";
                    case 24 -> getName() + " 忍著笑容，輕輕地放下了一張牌，保持冷靜。";
                    default -> getName() + " 突然變得冷靜，他出了一張牌，顯示出他的決斷力。";
                };
                System.out.println(emotion);
            }
            case EMPTY_HAND -> {
                int randomEmotion = (int) (Math.random() * 25); // 隨機選擇25種情緒
                String emotion = switch (randomEmotion) {
                    case 0 -> getName() + " 一臉無奈地放下手牌，看來這回合他沒有機會了。";
                    case 1 -> getName() + " 轉頭看向其他玩家，無奈地耸耸肩，表示自己無牌可出。";
                    case 2 -> getName() + " 輕輕地歎了口氣，手中空空如也。";
                    case 3 -> getName() + " 深深地吸了口氣，看起來對於沒有手牌感到無奈。";
                    case 4 -> getName() + " 皺了皺眉，看來他在試圖找出解決辦法，但最終還是放棄了。";
                    case 5 -> getName() + " 看著桌上的牌局，擔憂地嘆了口氣，他已經沒有牌可出了。";
                    case 6 -> getName() + " 眼神有些灰暗，似乎對於手牌的空虛感到沮喪。";
                    case 7 -> getName() + " 悶悶不樂地坐著，無法參與比大小，心情顯得很低落。";
                    case 8 -> getName() + " 尷尬地笑了笑，表示自己無牌可出，不禁有些尷尬。";
                    case 9 -> getName() + " 無奈地看了一眼其他玩家，表示自己沒有牌可以打。";
                    case 10 -> getName() + " 顯得有些無措，手中的空牌讓他感到手足無措。";
                    case 11 -> getName() + " 淡然地放下手牌，看似泰然處之，但其實心中有些無奈。";
                    case 12 -> getName() + " 心情顯得有些低落，眼中閃過一絲失望的神色。";
                    case 13 -> getName() + " 低頭歎了口氣，表示自己已經無牌可出了。";
                    case 14 -> getName() + " 無奈地搖了搖頭，似乎對於無法參與比大小感到無奈。";
                    case 15 -> getName() + " 看著桌上的空桌面，心情顯得有些孤單和失落。";
                    case 16 -> getName() + " 無可奈何地舉起手，示意自己已經出局了。";
                    case 17 -> getName() + " 表情凝重，看起來有些不甘心，但他的手中已經沒有牌了。";
                    case 18 -> getName() + " 有些無奈地耸了耸肩，表示自己已經無牌可出。";
                    case 19 -> getName() + " 看著手中的空牌，臉上露出一絲無奈和無措的神色。";
                    case 20 -> getName() + " 無奈地拍了拍桌子，似乎對於沒有機會參與比大小感到無奈。";
                    case 21 -> getName() + " 有些尷尬地笑了笑，表示自己已經無牌可出，場面有些尷尬。";
                    case 22 -> getName() + " 低頭沉思了片刻，然後無奈地放下了手中的空牌。";
                    case 23 -> getName() + " 看著其他人繼續比大小，心情顯得有些落寞和無奈。";
                    case 24 -> getName() + " 看著手中的空牌，無奈地苦笑著，表示自己已經無法參與比大小了。";
                    default -> getName() + " 無奈地放下手中的牌，心情顯得有些掙扎和無奈。";
                };
                System.out.println(emotion);
            }
            case WINNER -> {
                int randomEmotion = (int) (Math.random() * 25); // 隨機選擇25種情緒
                String emotion = switch (randomEmotion) {
                    case 0 -> getName() + " 露出了勝利的微笑，他是這場比賽的冠軍！";
                    case 1 -> getName() + " 豎起大拇指，表示自己是這場比賽的冠軍！";
                    case 2 -> getName() + " 得意地笑了笑，他成功地贏得了這場比賽。";
                    case 3 -> getName() + " 擁有光榮的微笑，他是比賽中的最強者！";
                    case 4 -> getName() + " 表情自信地宣布自己是這場比賽的勝利者。";
                    case 5 -> getName() + " 高興地舉起雙手，他是這場比賽的冠軍！";
                    case 6 -> getName() + " 腦海中充滿了喜悅，因為他成功地贏得了這場比賽。";
                    case 7 -> getName() + " 頗為驚訝地笑了笑，他成功地戰勝了所有對手。";
                    case 8 -> getName() + " 露出了勝利者的笑容，他是這場比賽的最終贏家！";
                    case 9 -> getName() + " 驕傲地看著自己的牌，他是這場比賽的冠軍！";
                    case 10 -> getName() + " 感到十分高興，他成功地擊敗了所有競爭對手。";
                    case 11 -> getName() + " 誇張地歡呼，表示自己是這場比賽的冠軍！";
                    case 12 -> getName() + " 表情自豪，他是這場比賽的最後勝者。";
                    case 13 -> getName() + " 顯得非常開心，因為他贏得了這場比賽的冠軍寶座。";
                    case 14 -> getName() + " 興高采烈地舉起雙手，宣布自己是這場比賽的冠軍！";
                    case 15 -> getName() + " 眼中充滿了光彩，他是這場比賽的勝利者！";
                    case 16 -> getName() + " 感到非常驚喜，他是這場比賽的最終贏家！";
                    case 17 -> getName() + " 忍不住歡呼，他是這場比賽的冠軍！";
                    case 18 -> getName() + " 得意地拍了拍自己的肩膀，因為他是這場比賽的最終贏家。";
                    case 19 -> getName() + " 表現出無比的喜悅，他成功地擊敗了所有競爭者。";
                    case 20 -> getName() + " 露出了得意的笑容，宣布自己是這場比賽的冠軍！";
                    case 21 -> getName() + " 非常自豪地舉起手來，他是這場比賽的最後勝利者。";
                    case 22 -> getName() + " 心情興奮，因為他是這場比賽的冠軍！";
                    case 23 -> getName() + " 看起來非常滿足，因為他成功地贏得了這場比賽。";
                    case 24 -> getName() + " 高興地舉起手，宣告自己是這場比賽的冠軍！";
                    default -> getName() + " 得意地笑著，他是這場比賽的冠軍！";
                };
                System.out.println(emotion);
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
    }
    //endregion

}
