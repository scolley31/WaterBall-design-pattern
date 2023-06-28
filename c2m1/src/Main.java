import java.util.Arrays;
import java.util.List;

public class Main {
    static Individual individual1 = new Individual(1, 18, new Coord(10, 20), Gender.MALE, "swimming,playGame,WatchingMovie,playBasketball,dance", "小美自我介紹");
    static Individual individual2 = new Individual(2, 20, new Coord(50, 50), Gender.FEMALE, "swimming,playGame,WatchingMovie,dance", "小華自我介紹");
    static Individual individual3 = new Individual(3, 40,new Coord(20, 20) , Gender.FEMALE, "playBasketball,dance", "小明自我介紹");
    static Individual individual4 = new Individual(4, 20, new Coord(1000, 1000), Gender.FEMALE, "playBasketball,dance", "小帥自我介紹");
    static Individual individual5 = new Individual(5, 32, new Coord(20, 20), Gender.FEMALE, "read,playBaseball,soccer", "寶拉自我介紹");


    public static void main(String[] args) {
        List<Individual> individuals = initIndividuals();
        // 系統配對最近的人
        System.out.println("配對系統策略:選擇距離最近的");
        MatchmakingSystem matchmakingSystemByDistance = new MatchmakingSystem(individuals, new distanceBasedStrategy());
        Individual matchedByDistance = matchmakingSystemByDistance.match(individual1);
        System.out.printf("配對到的人為%s,距離為%s\n", matchedByDistance.getId(), matchedByDistance.getDistance(individual1));
        System.out.println("=====================================");

        //系統配對最遠的人
        System.out.println("配對系統策略:選擇距離最遠的");
        matchmakingSystemByDistance.setMatchStrategy(RevereStrategy.reversed(new distanceBasedStrategy()));
        Individual matchedByDistanceReverse = matchmakingSystemByDistance.match(individual1);
        System.out.printf("配對到的人為%s,距離為%s\n", matchedByDistanceReverse.getId(), matchedByDistanceReverse.getDistance(individual1));
        System.out.println("=====================================");


        //match the habit
        System.out.println("配對系統策略:選擇共同興趣最相近的");
        MatchmakingSystem matchmakingSystemByHabits = new MatchmakingSystem(individuals, new habitBasedStrategy());
        Individual matchedByHabit = matchmakingSystemByHabits.match(individual1);
        System.out.printf("配對的人為:%s,興趣為%s\n",individual1.getId(),individual1.getInterests());
        System.out.printf("配對到的人為%s,興趣為%s\n", matchedByHabit.getId(), matchedByHabit.getInterests());
        System.out.println("=====================================");

        //系統配對共同興趣最不相近的人
        System.out.println("配對系統策略:選擇共同興趣最不相近的");
        matchmakingSystemByHabits.setMatchStrategy(RevereStrategy.reversed(new habitBasedStrategy()));
        Individual matchedByHabitReverse = matchmakingSystemByHabits.match(individual1);
        System.out.printf("配對的人為:%s,興趣為%s\n",individual1.getId(),individual1.getInterests());
        System.out.printf("配對到的人為%s,興趣為%s\n", matchedByHabitReverse.getId(), matchedByHabitReverse.getInterests());
    }

    public static List<Individual> initIndividuals() {
        return Arrays.asList(individual1, individual2, individual3, individual4, individual5);
    }
}