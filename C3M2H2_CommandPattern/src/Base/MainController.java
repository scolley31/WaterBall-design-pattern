package Base;

import Command.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

import static Base.Keyboard.*;
import static utils.Util.*;

public class MainController {
    private final Command[] commands;
    private Keyboard keyboard = new Keyboard();
    private Stack<Command> s1 = new Stack();
    private Stack<Command> s2 = new Stack();

    public MainController(Command[] commands) {
        this.commands = commands;
    }

    public void start(String input) {
        if ("1".equals(input)) {
            bind();
        } else if ("2".equals(input)) {
            undo();
        } else if ("3".equals(input)) {
            redo();
        } else {
            press(input);
        }
    }

    private void bind() {
        printf("請輸入要設置的按鍵: ");
        String input = askPlayerChooseKeyToBind();
        if (playerWantToBindMacroCommand()) {
            bindMacroCommand(input);
        } else {
            bindCommand(input);
        }
    }

    private void undo() {
        if (!s1.isEmpty()) {
            Command previousCommand = s1.pop();
            previousCommand.undo();
            s2.push(previousCommand);
        }
    }

    private void redo() {
        if (!s2.isEmpty()) {
            Command nextCommand = s2.pop();
            nextCommand.execute();
            s1.push(nextCommand);
        }
    }

    private void press(String key) {
        if (!isKeyHasCommand(key)) {
            throw new IllegalArgumentException("此案件沒有綁定任何功能");
        }
        Command executeCommand = getCommand(key);
        executeCommand.execute();
        s1.push(executeCommand);
        s2.clear();
    }

    private boolean playerWantToBindMacroCommand() {
        Scanner scanner = new Scanner(System.in);
        printf("設置巨集指令 (y/n)：\n");
        return "y".equalsIgnoreCase(scanner.nextLine());
    }

    private void bindMacroCommand(String button) {
        printf("要將哪些指令設置成快捷鍵 %s 的巨集（輸入多個數字，以空白隔開）: \n", button);
        printCommands();
        String input = askPlayerChooseKeyToBind();

        List<Command> macroCommands = Arrays.stream(input.split(" "))
                .map(s -> Integer.parseInt(s))
                .filter(i -> i >= 0 && i < commands.length)
                .map(i -> this.commands[i])
                .collect(Collectors.toList());

        bindingCommandToKey(button, new MacroCommand(macroCommands));
    }

    private void bindCommand(String key) {
        printf("要將哪一道指令設置到快捷鍵 %s 上: \n", key);
        printCommands();
        int index = askPlayerInput();

        if (index < 0 || index >= commands.length) {
            printf("請輸入合法的指令");
            bindCommand(key);
        }
        bindingCommandToKey(key, commands[index]);
    }


    private void printCommands() {
        for (int i = 0; i < commands.length; i++) {
            System.out.println(String.format("(%d) %s", i, commands[i].getName()));
        }
    }
}
