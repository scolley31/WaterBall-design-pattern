package org.example.command;

import org.example.Button;
import org.example.MainController;

import java.util.HashMap;

public class ResetCommand implements Command {

    private final MainController mainController;

    private final HashMap<Button, Command> commandHashMap = new HashMap<>();

    public ResetCommand(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void execute() {
        HashMap<Button, Command> commands = mainController.getCommandHashMap();
        commandHashMap.putAll(commands);
        mainController.resetButton();
    }

    @Override
    public void undo() {
        mainController.undoResetButton(commandHashMap);
    }

    @Override
    public String getName() {
        return "ResetCommand";
    }
}
