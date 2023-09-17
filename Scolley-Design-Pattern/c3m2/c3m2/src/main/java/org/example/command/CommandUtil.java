package org.example.command;

import java.util.List;
import java.util.Optional;

public class CommandUtil {

    public static <T extends Command> Optional<T> getCommand(List<Command> events, Class<T> type) {
        return events.stream()
                .filter(e -> type.isAssignableFrom(e.getClass()))
                .map(e -> (T) e)
                .findFirst();
    }
}
