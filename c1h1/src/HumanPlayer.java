import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(Game game, PlayerId id, Scanner scanner) {
        super(game, id);
        this.scanner = scanner;
    }

    @Override
    public void nameSelf() {
        System.out.printf("請輸入玩家 %s 的名字：", getId().name());
        String name = scanner.nextLine();

        System.out.printf("【公告】玩家 %s 已為自己命名為 %s。\n", getId().name(), name);
        this.setName(name);
    }

    @Override
    public Hand takeTurn() {
        System.out.printf("輪到玩家 %s 的回合。\n", getName());

        // 顯示目前的手牌
        System.out.printf("【系統】玩家 %s 目前的手牌為：", getName());

        List<Hand> hands = getHands();
        for (int i = 0; i < hands.size(); i++) {
            System.out.printf("【%s】%s ", i + 1, hands.get(i));
        }
        System.out.println();

        // 假如玩家仍擁有交換手牌的特權則判斷是否要使用
        if (isHasExchangeHandsRight()) {
            String userInput;

            System.out.printf("玩家 %s 目前仍擁有交換手牌的特權，你是否要使用這項特權(Y/N): ", getName());
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("Y")) {
                exchangeHands();
            } else {
                System.out.printf("【系統】玩家 %s 放棄交換手牌。\n", getName());
            }
        }

        return showHand();
    }

    @Override
    public void exchangeHands() {
        // 印出每位玩家的手牌數量
        List<Player> players = getGame().getPlayers();
        System.out.println("【系統】目前所有玩家的手牌數如下：");
        for (int i = 1; i <= 4; i++) {
            Player player = players.get(i - 1);
            System.out.printf("【%d】%s － %s  ", i, player.getName(), player.getHands().size());
        }
        System.out.println();

        int currentPlayerIndex = players.indexOf(this);
        int targetPlayerIndex;
        while (true) {
            System.out.print("請輸入想要交換手牌的玩家代號(1-4)，或輸入(-1)來放棄交換手牌：");

            if (scanner.hasNextInt()) {
                targetPlayerIndex = scanner.nextInt();

                if (targetPlayerIndex - 1 == currentPlayerIndex) {
                    System.out.println("\u001B[31m" + "【錯誤】無法與自己交換手牌。" + "\u001B[0m");
                } else if (targetPlayerIndex == -1) {
                    System.out.printf("【系統】玩家 %s 放棄交換手牌。\n", getName());
                    return;
                } else if (targetPlayerIndex >= 1 && targetPlayerIndex <= 4) {
                    break; // 如果是有效範圍內的數字，則跳出迴圈
                } else {
                    System.out.println("\u001B[31m" + "【錯誤】請輸入 1 到 4 之間的數字！" + "\u001B[0m");
                    scanner.nextLine(); // 清除無效的輸入
                }
            } else {
                System.out.println("\u001B[31m" + "【錯誤】請輸入有效的數字！" + "\u001B[0m");
                scanner.nextLine(); // 清除無效的輸入
            }
        }
        scanner.nextLine(); // 清除前面輸入的文字

        Player targetPlayer = players.get(targetPlayerIndex - 1);

        // 執行交換手牌
        System.out.printf("\u001B[34m" + "玩家 %s 選擇與玩家 %s 交換了手牌。\n" + "\u001B[0m", getName(), targetPlayer.getName());
        ExchangeHands exchangeHands = new ExchangeHands(getGame(), this, targetPlayer);
        this.addExchangeHands(exchangeHands);
        targetPlayer.addExchangeHands(exchangeHands);
        this.getGame().addExchangeHands(exchangeHands);

        // 顯示交換後的手牌
        System.out.printf("【系統】玩家 %s 換到的手牌為：", getName());

        List<Hand> hands = getHands();
        for (int i = 0; i < hands.size(); i++) {
            System.out.printf("【%s】%s ", i + 1, hands.get(i));
        }
        System.out.println();

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

        int showHandNumber;
        if (getHands().size() > 1) {
            while (true) {
                System.out.printf("【系統】請輸入要出的手牌號碼(1-%s)：", getHands().size());

                if (scanner.hasNextInt()) {
                    showHandNumber = scanner.nextInt() - 1;
                    if (showHandNumber >= 0 && showHandNumber < getHands().size()) {
                        break; // 如果是有效範圍內的數字，則跳出迴圈
                    } else {
                        System.out.printf("\u001B[31m" + "【錯誤】請輸入 1 到 %s 之間的數字！\n" + "\u001B[0m", getHands().size());
                        scanner.nextLine(); // 清除無效的輸入
                    }
                } else {
                    System.out.println("\u001B[31m" + "【錯誤】請輸入有效的數字！" + "\u001B[0m");
                    scanner.nextLine(); // 清除無效的輸入
                }
            }
            scanner.nextLine(); // 清除前面輸入的文字
        } else {
            System.out.println("【系統】僅剩下一張手牌，系統自動發出最後一張手牌。");
            showHandNumber = 0;
        }

        Hand hand = hands.remove(showHandNumber);
        hand.setPlayer(this);
        System.out.printf("玩家 %s 已將手牌 %s 覆蓋在桌上。\n", getName(), hand);
        System.out.println();

        return hand;
    }
}
