package Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private World world;

    public Game(World world) {
        this.world = world;
    }

    public void start() {
        while (true) {
            System.out.println("Please enter the number of the position you want to move from and to: ");
            Scanner scanner = new Scanner(System.in);
            int From = scanner.nextInt();
            int To = scanner.nextInt();
            if (!isValidInput(From, To)) {
                start();
            }
            world.move(From, To);
        }
    }

    public boolean isValidInput(int From, int To) {
        return From >= 0 && From <= 30 && To >= 0 && To <= 30;
    }

}
