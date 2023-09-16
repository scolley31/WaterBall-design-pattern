package org.example.command;

public interface Command {
    void execute();

    void undo();

    String getName();
}
