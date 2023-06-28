package Command;


import Base.Keyboard;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MacroCommand implements Command {
    private final List<Command> commands;

    public MacroCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void undo() {
        IntStream.iterate(commands.size() - 1, i -> i >= 0, i -> i - 1)
                .forEach(i -> commands.get(i).undo());
    }

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public String getName() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Command command : commands) {
            stringBuilder.append(command.getName());
            stringBuilder.append(" & ");
        }
        return stringBuilder.toString().substring(0, stringBuilder.length() - 3);
    }
}
