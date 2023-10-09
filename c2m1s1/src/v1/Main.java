package v1;

public class Main {
    public static void main(String[] args) {
        Hero hero1 = new Hero("Hero1", Hero.AttackType.WaterBall);
        Hero hero2 = new Hero("Hero2", Hero.AttackType.FireBall);
        Game game = new Game(hero1,hero2);
        game.start();
    }
}