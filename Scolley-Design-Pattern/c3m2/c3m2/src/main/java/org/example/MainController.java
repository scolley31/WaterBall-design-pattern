package org.example;

import org.example.command.Command;

import java.util.HashMap;
import java.util.Stack;

public class MainController {

    private HashMap<Button, Command> commandHashMap = new HashMap<>();

    private final Keyboard keyboard;

    private final Stack<Command> commandsHistory = new Stack<>();

    private final Stack<Command> undoCommandsHistory = new Stack<>();

    public MainController(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void setCommand(Button button, Command command) {
        commandHashMap.put(button, command);
    }

    public void press(String buttonString) {
        Button button = keyboard.pressButton(buttonString);
        Command command = commandHashMap.get(button);
        if (command == null) {
            System.out.println("請輸入正確的指令");
            return;
        }
        commandsHistory.push(command);
        undoCommandsHistory.clear();
        command.execute();
    }

    public void undo() {
        if (!commandsHistory.isEmpty()) {
            Command command = commandsHistory.pop();
            command.undo();
            undoCommandsHistory.push(command);
        }
    }

    public void redo() {
        if (!undoCommandsHistory.isEmpty()) {
            Command command = undoCommandsHistory.pop();
            command.execute();
            commandsHistory.push(command);
        }
    }

    public void resetButton() {
        commandHashMap.clear();
    }

    public void undoResetButton(HashMap<Button, Command> commandHashMap) {
        this.commandHashMap = commandHashMap;
    }

    public HashMap<Button, Command> getCommandHashMap() {
        return commandHashMap;
    }
}
