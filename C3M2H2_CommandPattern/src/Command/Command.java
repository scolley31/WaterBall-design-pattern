package Command;

import Base.Keyboard;

public interface Command {
    void execute();
    String getName();
    void undo();
}
