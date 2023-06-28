package Command;

import Base.Keyboard;
import MilitaryEquipment.Tank;

public class MoveTankForward implements Command {
    private final Tank tank;
    private final String name = "MoveTankForward";

    public MoveTankForward(Tank tank) {
        this.tank = tank;
    }

    public String getName() {
        return name;
    }

    @Override
    public void undo() {
        tank.moveBackward();
    }

    @Override
    public void execute() {
        tank.moveForward();
    }
}
