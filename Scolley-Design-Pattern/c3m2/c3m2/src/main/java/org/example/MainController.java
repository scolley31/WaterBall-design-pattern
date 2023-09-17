package org.example;

import org.example.command.*;

import java.util.*;

public class MainController {

    private final List<Command> supportCommands = new ArrayList<>();

    private final Keyboard keyboard;

    private final Scanner scanner = new Scanner(System.in);

    public MainController(Keyboard keyboard, List<Command> supportCommands) {
        this.keyboard = keyboard;
        this.supportCommands.addAll(supportCommands);
    }

    public void start() {

        while (true) {
            System.out.print("(1) 快捷鍵設置 (2) Undo (3) Redo (字母) 按下按鍵: ");
            String i = scanner.next();
            switch (i) {
                case "1" -> {
                    setupCommand();
                }
                case "2" -> {
                    undo();
                }
                case "3" -> {
                    redo();
                }
                default -> {
                    keyboard.press(i);
                }
            }
            showAllCommandSetup();
        }

    }

    private void setupCommand() {
        String j = askClient("設置巨集指令 (y/n)：");
        if (j.equals("n")) {
            setupSingleCommand();
        } else if (j.equals("y")) {
            setupMacroCommand();
        } else {
            System.out.println("請輸入正確的指令");
        }
    }

    private void undo() {
        keyboard.undo();
    }

    private void redo() {
        keyboard.redo();
    }

    private void setupSingleCommand() {
        String key = askClient("Key : ");
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
                keyboard.setCommand(keyboard.pressButton(key), CommandUtil.getCommand(supportCommands, MoveForwardTankCommend.class).orElseThrow());
            }
            case "1" -> {
                keyboard.setCommand(keyboard.pressButton(key), CommandUtil.getCommand(supportCommands, MoveBackwardTankCommend.class).orElseThrow());
            }
            case "2" -> {
                keyboard.setCommand(keyboard.pressButton(key), CommandUtil.getCommand(supportCommands, ConnectTelecomCommand.class).orElseThrow());
            }
            case "3" -> {
                keyboard.setCommand(keyboard.pressButton(key), CommandUtil.getCommand(supportCommands, DisconnectTelecomCommand.class).orElseThrow());
            }
            case "4" -> {
                keyboard.setCommand(keyboard.pressButton(key), CommandUtil.getCommand(supportCommands, ResetCommand.class).orElseThrow());
            }
            default -> System.out.println("請輸入正確的指令");
        }
    }

    private void setupMacroCommand() {
        String key = askClient("Key : ");
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
                    commandList.add(CommandUtil.getCommand(supportCommands, MoveForwardTankCommend.class).orElseThrow());
                }
                case "1" -> {
                    commandList.add(CommandUtil.getCommand(supportCommands, MoveBackwardTankCommend.class).orElseThrow());
                }
                case "2" -> {
                    commandList.add(CommandUtil.getCommand(supportCommands, ConnectTelecomCommand.class).orElseThrow());
                }
                case "3" -> {
                    commandList.add(CommandUtil.getCommand(supportCommands, DisconnectTelecomCommand.class).orElseThrow());
                }
                case "4" -> {
                    commandList.add(CommandUtil.getCommand(supportCommands, ResetCommand.class).orElseThrow());
                }
                default -> System.out.println("請輸入正確的指令");
            }
        }

        Macro macro = new Macro(commandList);
        keyboard.setCommand(keyboard.pressButton(key), macro);
    }

    private void showAllCommandSetup() {
        HashMap<Button, Command> commandHashMap = keyboard.getCommandHashMap();
        for (Map.Entry<Button, Command> entry : commandHashMap.entrySet()) {
            Button button = entry.getKey();
            Command command = entry.getValue();
            System.out.println(button.getLowercase().getValue() + ": " + command.getName());
        }
    }

    private String askClient(String s) {
        System.out.println(s);
        return scanner.next();
    }

}
