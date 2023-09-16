package org.example.command;

import org.example.Tank;

public class MoveBackwardTankCommend implements Command {

    private final Tank tank;

    public MoveBackwardTankCommend(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void execute() {
        tank.moveBackward();
    }

    @Override
    public void undo() {
        tank.moveForward();
    }

    @Override
    public String getName() {
        return "MoveBackwardTankCommend";
    }

}
