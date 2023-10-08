import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {
    static final ArrayList<String> PLAYER_NAMES = new ArrayList<>(Arrays.asList(
            "Jenny", "Mike", "Max", "Anna", "David", "Emily", "Daniel", "Olivia",
            "Liam", "Sophia", "Ethan", "Ava", "Lucas", "Emma", "Alexander", "Mia",
            "Logan", "Grace", "James", "Chloe"));

    public AIPlayer(Game game, PlayerId id) {
        super(game, id);
    }

    @Override
    public void nameSelf() {
        Random r = new Random();
        int randomIndex = r.nextInt(PLAYER_NAMES.size());
        String name = PLAYER_NAMES.remove(randomIndex);

        try {
            Thread.sleep((int) (Math.random() * 500) + 200);
            System.out.printf("【公告】電腦玩家 %s 已為自己命名為 %s。\n", getId().name(), name);
            setName(name);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Hand takeTurn() {
        System.out.printf("輪到玩家 %s 的回合。\n", getName());

        try {
            Thread.sleep((int)(Math.random()*1500)+500);
        // 假如玩家仍擁有交換手牌的特權則判斷是否要使用
        if (isHasExchangeHandsRight()) {
            // 當電腦玩家無手牌的時候一定使用手牌交換的功能
            if (getHands().isEmpty()) {
                exchangeHands();
            }

            Random r = new Random();
            int possible = r.nextInt(100);

            // 假設電腦玩家在該回合擁有的手牌數量比沒有交換過的狀況還少有 50% 機率使用交換手牌的特權
            if (getHandsCount() < 13 - (getGame().getRound() - 1) && possible < 50) {
                exchangeHands();
            }
            // 假設電腦玩家有 20% 機率使用交換手牌的特權
            else if (possible < 20) {
                exchangeHands();
            }
        }

        return showHand();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exchangeHands() {
        // 找出手牌數量最多的玩家
        List<Player> players = getGame().getPlayers();
        int mostHands = 0;
        List<Player> targetPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.getHandsCount() > mostHands) {
                mostHands = player.getHandsCount();
            }
        }

        for (Player player : players) {
            if (player.getHandsCount() == mostHands && getId() != player.getId()) {
                targetPlayers.add(player);
            }
        }

        if (targetPlayers.isEmpty()) {
            targetPlayers = new ArrayList<>(players);
            targetPlayers.remove(this);
        }

        // 隨機選擇手牌最多的玩家交換手牌
        Random r = new Random();
        Player targetPlayer;
        do {
            int playerIndex = r.nextInt(targetPlayers.size());
            targetPlayer = targetPlayers.get(playerIndex);
        } while (targetPlayer.getId() == this.getId());

        // 執行交換手牌
        System.out.printf("\u001B[34m" + "玩家 %s 選擇與玩家 %s 交換了手牌。\n" + "\u001B[0m", getName(), targetPlayer.getName());
        ExchangeHands exchangeHands = new ExchangeHands(getGame(), this, targetPlayer);
        this.addExchangeHands(exchangeHands);
        targetPlayer.addExchangeHands(exchangeHands);
        this.getGame().addExchangeHands(exchangeHands);

        // 每個玩家只能交換一次手牌
        setHasExchangeHandsRight(false);
    }

    @Override
    public Hand showHand() {
        // 決定要出的手牌
        List<Hand> hands = getHands();

        if (hands.isEmpty()) {
            describeEmotion(Action.EMPTY_HAND);
            return null;
        }

        // 隨機決定要出的手牌
        Random r = new Random();
        int showHandNumber = r.nextInt(hands.size());

        Hand hand = hands.remove(showHandNumber);
        hand.setPlayer(this);
        describeEmotion(Action.SHOW);
        System.out.println();

        return hand;
    }
}
