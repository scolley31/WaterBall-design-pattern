package org.example;

import org.example.command.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Keyboard keyboard = new Keyboard();
        Tank tank = new Tank();
        Telecom telecom = new Telecom();
        MainController mainController = new MainController(keyboard);
        MoveForwardTankCommend moveForwardTankCommend = new MoveForwardTankCommend(tank);
        MoveBackwardTankCommend moveBackwardTankCommend = new MoveBackwardTankCommend(tank);
        ConnectTelecomCommand connectTelecomCommand = new ConnectTelecomCommand(telecom);
        DisconnectTelecomCommand disconnectTelecomCommand = new DisconnectTelecomCommand(telecom);
        ResetCommand resetCommand = new ResetCommand(mainController);
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.print("(1) 快捷鍵設置 (2) Undo (3) Redo (字母) 按下按鍵: ");
            String i = scanner.next();
            switch (i) {
                case "1" -> {
                    setupCommand(scanner, mainController, keyboard, moveForwardTankCommend, moveBackwardTankCommend, connectTelecomCommand, disconnectTelecomCommand, resetCommand, tank, telecom);
                }
                case "2" -> {
                    undo(mainController);
                }
                case "3" -> {
                    redo(mainController);
                }
                default -> {
                    mainController.press(i);
                }
            }
            showAllCommandSetup(mainController);
        }


    }

    private static void showAllCommandSetup(MainController mainController) {
        HashMap<Button, Command> commandHashMap = mainController.getCommandHashMap();
        for (Map.Entry<Button, Command> entry: commandHashMap.entrySet()) {
            Button button = entry.getKey();
            Command command = entry.getValue();
            System.out.println(button.getLowercase().getValue() + ": " + command.getName());
        }
    }


    private static void setupCommand(Scanner scanner, MainController mainController, Keyboard keyboard, MoveForwardTankCommend moveForwardTankCommend, MoveBackwardTankCommend moveBackwardTankCommend, ConnectTelecomCommand connectTelecom, DisconnectTelecomCommand disconnectTelecomCommand, ResetCommand resetCommand, Tank tank, Telecom telecom) {
        String j = askClient("設置巨集指令 (y/n)：", scanner);
        if (j.equals("n")) {
            setupSingleCommand(scanner, mainController, keyboard, moveForwardTankCommend, moveBackwardTankCommend, connectTelecom, disconnectTelecomCommand, resetCommand);
        } else if (j.equals("y")) {
            setupMacroCommand(scanner, tank, telecom, mainController, keyboard);
        } else {
            System.out.println("請輸入正確的指令");
        }
    }

    private static void redo(MainController mainController) {
        mainController.redo();
    }

    private static void undo(MainController mainController) {
        mainController.undo();
    }

    private static String askClient(String x, Scanner scanner) {
        System.out.println(x);
        return scanner.next();
    }

    private static void setupMacroCommand(Scanner scanner, Tank tank, Telecom telecom, MainController mainController, Keyboard keyboard) {
        String key = askClient("Key : ", scanner);
        System.out.println("(0) MoveTankForward");
        System.out.println("(1) MoveTankBackward");
        System.out.println("(2) ConnectTelecom");
        System.out.println("(3) DisconnectTelecom");
        System.out.println("(4) ResetMainControlKeyboard");
        System.out.println("要將哪些指令設置成快捷鍵 " + key + " 的巨集（輸入多個數字，以空白隔開）: ");
        Scanner scannerForMacro = new Scanner(System.in);
        String buttons = scannerForMacro.nextLine();
        String[] buttonArray = buttons.split(" ");

        List<Command> commandList = new ArrayList<>();

        for (String s : buttonArray) {
            switch (s) {
                case "0" -> {
                    commandList.add(new MoveForwardTankCommend(tank));
                }
                case "1" -> {
                    commandList.add(new MoveBackwardTankCommend(tank));
                }
                case "2" -> {
                    commandList.add(new ConnectTelecomCommand(telecom));
                }
                case "3" -> {
                    commandList.add(new DisconnectTelecomCommand(telecom));
                }
                case "4" -> {
                    commandList.add(new ResetCommand(mainController));
                }
                default -> System.out.println("請輸入正確的指令");
            }
        }

        Macro macro = new Macro(commandList);
        mainController.setCommand(keyboard.pressButton(key), macro);
    }

    private static void setupSingleCommand(Scanner scanner, MainController mainController, Keyboard keyboard, MoveForwardTankCommend moveForwardTankCommend, MoveBackwardTankCommend moveBackwardTankCommend, ConnectTelecomCommand connectTelecom, DisconnectTelecomCommand disconnectTelecomCommand, ResetCommand resetCommand) {
        String key = askClient("Key : ", scanner);
        System.out.println("要將哪一道指令設置到快捷鍵 " + key + " 上");
        System.out.println("(0) MoveTankForward");
        System.out.println("(1) MoveTankBackward");
        System.out.println("(2) ConnectTelecom");
        System.out.println("(3) DisconnectTelecom");
        System.out.println("(4) ResetMainControlKeyboard");
        Scanner scannerForSingleCommand = new Scanner(System.in);
        String button = scannerForSingleCommand.next();
        switch (button) {
            case "0" -> {
                mainController.setCommand(keyboard.pressButton(key), moveForwardTankCommend);
            }
            case "1" -> {
                mainController.setCommand(keyboard.pressButton(key), moveBackwardTankCommend);
            }
            case "2" -> {
                mainController.setCommand(keyboard.pressButton(key), connectTelecom);
            }
            case "3" -> {
                mainController.setCommand(keyboard.pressButton(key), disconnectTelecomCommand);
            }
            case "4" -> {
                mainController.setCommand(keyboard.pressButton(key), resetCommand);
            }
            default -> System.out.println("請輸入正確的指令");
        }
    }

}