package Command;

import Base.Keyboard;

public class ResetMainControlKeyboard implements Command {
    private final String name = "ResetMainControlKeyboard";
    private final Keyboard keyboard;

    public ResetMainControlKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public String getName() {
        return name;
    }

    @Override
    public void undo() {
        keyboard.unReset();
    }

    @Override
    public void execute() {
        keyboard.reset();
    }
}
