package org.example.command;

import java.util.List;

public class Macro implements Command {


    private List<Command> commandList;

    public Macro(List<Command> commandList) {
        this.commandList = commandList;
    }

    @Override
    public void execute() {
        commandList.forEach(Command::execute);
    }

    @Override
    public void undo() {
        commandList.forEach(Command::undo);
    }

    @Override
    public String getName() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < commandList.size(); i++) {
            stringBuilder.append(commandList.get(i).getName());
            if (i < commandList.size() - 1) {
                stringBuilder.append(" & ");
            }
        }
        return stringBuilder.toString();
    }


}
