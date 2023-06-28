package Base;

import Command.Command;

import java.util.HashMap;
import java.util.Map;

public class Keyboard {
    private static Map<String, Command> buttons = initButtons();
    private static Map<String, Command> backUpButtons = new HashMap<>();

    private static Map<String, Command> initButtons() {
        HashMap<String, Command> buttons = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            String key = String.valueOf(c);
            buttons.put(key, null);
        }
        return buttons;
    }

    public static boolean isInputOnKeyBoard(String input) {
        return buttons.containsKey(input);
    }

    public void reset() {
        for (Map.Entry<String, Command> entry : buttons.entrySet()) {
            backUpButtons.put(new String(entry.getKey()), entry.getValue());
            entry.setValue(null);
        }
    }

    public void unReset() {
        for (Map.Entry<String, Command> entry : backUpButtons.entrySet()) {
            buttons.put(new String(entry.getKey()), entry.getValue());
        }
    }

    public static void printCurrentHasCommandButton() {
        for (Map.Entry<String, Command> entry : buttons.entrySet()) {
            if (entry.getValue() != null) {
                System.out.printf("%s: %s\n", entry.getKey(), entry.getValue().getName());
            }
        }
    }

    public static void bindingCommandToKey(String key, Command command) {
        buttons.put(key, command);
    }

    public static Command getCommand(String key) {
        return buttons.get(key);
    }

    public static boolean isKeyHasCommand(String key) {
        return buttons.get(key) != null;
    }
}

