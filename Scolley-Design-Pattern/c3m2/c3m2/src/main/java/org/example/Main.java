package org.example;

import org.example.command.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Keyboard keyboard = new Keyboard();
        Tank tank = new Tank();
        Telecom telecom = new Telecom();

        MoveForwardTankCommend moveForwardTankCommend = new MoveForwardTankCommend(tank);
        MoveBackwardTankCommend moveBackwardTankCommend = new MoveBackwardTankCommend(tank);
        ConnectTelecomCommand connectTelecomCommand = new ConnectTelecomCommand(telecom);
        DisconnectTelecomCommand disconnectTelecomCommand = new DisconnectTelecomCommand(telecom);
        ResetCommand resetCommand = new ResetCommand(keyboard);

        List<Command> commandList = List.of(moveForwardTankCommend, moveBackwardTankCommend, connectTelecomCommand, disconnectTelecomCommand, resetCommand);
        MainController mainController = new MainController(keyboard, commandList);

        mainController.start();

    }

}