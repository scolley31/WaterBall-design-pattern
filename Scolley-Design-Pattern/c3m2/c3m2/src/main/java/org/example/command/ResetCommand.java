package org.example.command;

import org.example.Keyboard;

public class ResetCommand implements Command {

    private final Keyboard keyboard;

    public ResetCommand(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public void execute() {
        keyboard.resetButton();
    }

    @Override
    public void undo() {
        keyboard.undoResetButton();
    }

    @Override
    public String getName() {
        return "ResetCommand";
    }
}
