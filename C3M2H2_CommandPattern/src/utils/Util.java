package utils;

import java.util.Scanner;

import static Base.Keyboard.isInputOnKeyBoard;

public class Util {
    public static String askPlayerChooseKeyToBind() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
    public static int askPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        return index;
    }
    public static void printf(String content, String key){
        System.out.printf(content,key);
    }
    public static void printf(String content){
        System.out.printf(content);
    }
}
