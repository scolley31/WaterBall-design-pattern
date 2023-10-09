package v2;

public class Main {
    public static void main(String[] args) {
        Hero hero1 = new Hero("Hero1", new Waterball());
        Hero hero2 = new Hero("Hero2", new Earth());
        Game game = new Game(hero1,hero2);
        game.start();
    }
}