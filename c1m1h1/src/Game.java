import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class Game {
    private final Deck deck = new Deck();
    private final List<Player> players = new ArrayList<>();
    private final ArrayList<ExchangeHands> exchangeHands = new ArrayList<>();
    private final Scanner scanner;
    private int round = 0;

    public Game(Scanner scanner) {
        this.scanner = scanner;
    }


    public void start() {
        System.out.println("【系統】歡迎來到撲克牌比大小遊戲(Showdown)，這款遊戲支援 4 位玩家，在遊戲開始之前，請先決定玩家的數量。");

        // 決定真實玩家與電腦玩家人數
        int humanPlayerNumber;

        while (true) {
            System.out.print("請輸入玩家的數量(0~4)，不足的人數會由電腦玩家替代: ");

            if (scanner.hasNextInt()) {
                humanPlayerNumber = scanner.nextInt();
                if (humanPlayerNumber >= 0 && humanPlayerNumber <= 4) {
                    break; // 如果是有效範圍內的數字，則跳出迴圈
                } else {
                    System.out.println("\u001B[31m" + "【錯誤】請輸入 0 到 4 之間的數字！" + "\u001B[0m");
                    scanner.nextLine(); // 清除無效的輸入
                }
            } else {
                System.out.println("\u001B[31m" + "【錯誤】請輸入有效的數字！" + "\u001B[0m");
                scanner.nextLine(); // 清除無效的輸入
            }
        }
        scanner.nextLine(); // 清除前面輸入的文字


        // 創建使用者的實體
        for (Player.PlayerId id : Player.PlayerId.values()) {
            if (id.ordinal() + 1 <= humanPlayerNumber) {
                players.add(new HumanPlayer(this, id, scanner));
            } else {
                players.add(new AIPlayer(this, id));
            }
        }

        System.out.println();
        System.out.println("=============== 玩家命名階段 ===============");
        System.out.println("【系統】在遊戲正式開始之前，請每位玩家替自己命名");
        for (Player player : players) {
            player.nameSelf();
        }

        System.out.println();
        System.out.println("=============== 遊戲規則說明 ===============");
        System.out.println("【系統】接著為各位說明比賽規則，在之後的 13 回合之內，請每位玩家輪流出牌，在出牌之前可以決定要不要將手上所有的牌與其他玩家交換。");
        System.out.println("【系統】但請注意，交換手牌的特權只能使用一次，另外手牌在交換的三回合之後必須重新交換回來。");
        System.out.println("【系統】不論是否有交換手牌，都必須選擇一張手牌放在檯面上比大小，如果沒有牌可出就直接視為該回合棄權，也無法與其他玩家比大小。");
        System.out.println("【系統】所有玩家出完牌之後，會揭開所有的牌，並且進行比大小。");
        System.out.println("【系統】比大小的規則為，先比較牌的階級，此時階級較大者勝，如果階級相同則比較花色，此時花色較大者勝");
        System.out.println("【系統】階級由小到大依序為：2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A");
        System.out.println("【系統】花色由小到大依序為：梅花(♣)、菱形(♦)、愛心(♥)、黑桃(♠)");
        System.out.println("【系統】獲勝者可以在當前回合獲得 1 分，在遊戲結束時得到最多分的人為獲勝者。");
        System.out.println("【系統】那麼解說就到這邊，比賽正式開始吧！");

        System.out.print("\n按下 Enter 鍵繼續...");
        scanner.nextLine();

        System.out.println();
        System.out.println("=============== 洗牌階段 ===============");
        System.out.println("【系統】首先，為了遊戲的公平性，系統會將撲克牌徹底進行洗牌。");
        deck.shuffle();

        System.out.println();
        System.out.println("=============== 抽牌階段 ===============");
        System.out.println("【系統】接下來請每位玩家輪流從牌堆中抽牌，直到手上的手牌擁有 13 張為止。");
        for (Player player : players) {
            try {
                System.out.printf("%s 正在抽牌.", player.getName());
                Thread.sleep((int) (Math.random() * 500) + 300);
                System.out.print(".");
                Thread.sleep((int) (Math.random() * 500) + 300);
                System.out.println(".");
                Thread.sleep((int) (Math.random() * 500) + 300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 13; i++) {
                player.draw(deck);
            }
            player.sortHands();
            player.describeEmotion(Player.Action.DRAW);
        }

        try {
            for (round = 1; round <= 13; round++) {
                System.out.println();
                System.out.printf("=============== 第 %d 回合 ===============\n", round);

                // 顯示系統通知
                Thread.sleep(1000);
                String notification = switch (round) {
                    case 13 -> "【系統】終於到了最後一回合了，究竟誰會獲得最後的勝利呢？！\n";
                    case 12 -> "【系統】已經是最後兩回合了，手中還有好牌的玩家請好好把握剩下的機會。\n";
                    case 11 -> "【系統】剩下最後三回合，遊戲準備進入最後的階段了！\n";
                    default -> null;
                };

                if (notification != null) {
                    System.out.println(notification);
                }

                // 每經過一回合增加交換手牌的回合數
                ArrayList<ExchangeHands> copyExchangeHands = new ArrayList<>(this.exchangeHands);
                boolean printLine = false;
                for (int i = copyExchangeHands.size() - 1; i >= 0; i--) {
                    ExchangeHands exchangeHands = copyExchangeHands.get(i);
                    exchangeHands.increaseRound();

                    if (exchangeHands.getRound() >= 3) {
                        exchangeHands.reexchangeHands();
                        printLine = true;
                    }
                }
                if(printLine){
                    System.out.println();
                }

                // 每位玩家輪流行動
                ArrayList<Hand> deckHands = new ArrayList<>();
                for (Player player : players) {
                    Hand hand = player.takeTurn();
                    if (hand != null) {
                        deckHands.add(hand);
                    }
                }

                Thread.sleep(1000);

                // 回合揭牌階段
                System.out.println("【系統】每位玩家都出好牌了，接下來看看大家出了什麼牌吧。");
                Hand winnerHand = deckHands.getFirst();
                for (Hand hand : deckHands) {
                    System.out.printf("%s － %s  ", hand.getPlayer().getName(), hand);

                    if (winnerHand.compareTo(hand) < 0) {
                        winnerHand = hand;
                    }
                }
                System.out.println();

                Thread.sleep(2000);

                Player winner = winnerHand.getPlayer();
                System.out.printf("這一回合的最大的牌是 %s 恭喜玩家 %s 獲得 1 分！！\n", winnerHand, winner.getName());
                winner.gainPoint();

                Thread.sleep(2000);

                // 回合結束顯示各玩家的得分
                System.out.println();
                System.out.println("【系統】在這回合結束後各位玩家的總分如下：");
                for (Player player : players) {
                    System.out.printf("%s － %d 分  ", player.getName(), player.getPoint());
                }
                System.out.println();

                System.out.print("\n按下 Enter 鍵繼續...");
                scanner.nextLine();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println();
            System.out.println("=============== 結算階段 ===============");
            System.out.println("【系統】這次的撲克牌比大小遊戲(Showdown)終於到了最後時刻，現在公布優勝者是...");

            // 找出分數最多的玩家
            int mostPoint = 0;
            List<Player> winners = new ArrayList<>();
            for (Player player : players) {
                if (player.getPoint() > mostPoint) {
                    mostPoint = player.getPoint();
                }
            }

            for (Player player : players) {
                if (player.getPoint() == mostPoint) {
                    winners.add(player);
                }
            }

            String winnerNames = winners.stream()
                    .map(Player::getName)
                    .collect(Collectors.joining(", "));

            Thread.sleep(1000);
            System.out.printf("【系統】恭喜玩家 %s 獲得了這次的勝利！！！\uD83C\uDF89\uD83C\uDF89\n", winnerNames);
            Thread.sleep(300);
            winners.forEach(winner -> winner.describeEmotion(Player.Action.WINNER));

            System.out.println("【系統】感謝各位玩家的遊玩，那麼我們下次再見吧～");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addExchangeHands(ExchangeHands exchangeHands) {
        this.exchangeHands.add(requireNonNull(exchangeHands));
    }

    public void removeExchangeHands(ExchangeHands exchangeHands) {
        this.exchangeHands.remove(requireNonNull(exchangeHands));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getRound() {
        return round;
    }

}
