package Command;

import Base.Keyboard;
import MilitaryEquipment.Tank;

public class MoveTankBackWard implements Command {
    private final Tank tank;
    private final String name = "MoveTankBackWard";

    public MoveTankBackWard(Tank tank) {
        this.tank = tank;
    }

    public String getName() {
        return name;
    }

    @Override
    public void undo() {
        tank.moveForward();
    }

    @Override
    public void execute() {
        tank.moveBackward();
    }
}
