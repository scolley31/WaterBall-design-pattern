package org.example;

import org.example.command.Command;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Keyboard {

    private List<Button> buttons;

    private final Stack<Command> commandsHistory = new Stack<>();

    private final Stack<Command> undoCommandsHistory = new Stack<>();

    private final HashMap<Button, Command> commandHashMap = new HashMap<>();

    private final HashMap<Button, Command> undoCommandHashMap = new HashMap<>();

    public Keyboard() {
        initButtons();
    }

    private void initButtons() {
        buttons = List.of(
                new Button(Lowercase.A),
                new Button(Lowercase.B),
                new Button(Lowercase.C),
                new Button(Lowercase.D),
                new Button(Lowercase.E),
                new Button(Lowercase.F),
                new Button(Lowercase.G),
                new Button(Lowercase.H),
                new Button(Lowercase.I),
                new Button(Lowercase.J),
                new Button(Lowercase.K),
                new Button(Lowercase.L),
                new Button(Lowercase.M),
                new Button(Lowercase.N),
                new Button(Lowercase.O),
                new Button(Lowercase.P),
                new Button(Lowercase.Q),
                new Button(Lowercase.R),
                new Button(Lowercase.S),
                new Button(Lowercase.T),
                new Button(Lowercase.U),
                new Button(Lowercase.V),
                new Button(Lowercase.W),
                new Button(Lowercase.X),
                new Button(Lowercase.Y),
                new Button(Lowercase.Z)
        );
    }

    public Button pressButton(String lowercase) {
        return buttons.stream().filter(button -> button.getLowercase().getValue().equals(lowercase)).findFirst().orElseThrow();
    }

    public void press(String buttonString) {
        Button button = pressButton(buttonString);
        Command command = commandHashMap.get(button);
        if (command == null) {
            System.out.println("請輸入正確的指令");
            return;
        }
        commandsHistory.push(command);
        undoCommandsHistory.clear();
        command.execute();
    }

    public void setCommand(Button button, Command command) {
        commandHashMap.put(button, command);
    }

    public HashMap<Button, Command> getCommandHashMap() {
        return commandHashMap;
    }

    public void resetButton() {
        undoCommandHashMap.putAll(commandHashMap);
        commandHashMap.clear();
    }

    public void undoResetButton() {
        commandHashMap.putAll(undoCommandHashMap);
        undoCommandHashMap.clear();
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

}
