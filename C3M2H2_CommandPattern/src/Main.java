import Base.Keyboard;
import Base.MainController;
import Command.*;
import MilitaryEquipment.Tank;
import MilitaryEquipment.Telecom;
import java.util.Scanner;

import static Base.Keyboard.printCurrentHasCommandButton;
import static utils.Util.printf;

public class Main {
    public static void main(String[] args) {
        Tank tank = new Tank();
        Telecom telecom = new Telecom();
        Command[] commands = new Command[]{new ConnectTelecom(telecom), new DisconnectTelecom(telecom), new MoveTankForward(tank), new MoveTankBackWard(tank), new ResetMainControlKeyboard(new Keyboard())};
        MainController mainController = new MainController(commands);

        while (true) {
            printCurrentHasCommandButton();
            printf("(1) 快捷鍵設置 (2) Undo (3) Redo (字母) 按下按鍵: ");
            Scanner scanner = new Scanner(System.in);
            mainController.start(scanner.nextLine());
        }
    }
}
