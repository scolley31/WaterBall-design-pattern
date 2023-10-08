import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game(scanner);
        game.start();
        scanner.close();
    }
}