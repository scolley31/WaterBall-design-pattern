package org.example.command;

import org.example.Tank;

public class MoveForwardTankCommend implements Command {

    private final Tank tank;

    public MoveForwardTankCommend(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.moveForward();
    }

    @Override
    public void undo() {
        tank.moveBackward();
    }

    @Override
    public String getName() {
        return "MoveForwardTankCommand";
    }

}
