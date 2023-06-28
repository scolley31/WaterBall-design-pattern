package Command;

import Base.Keyboard;
import MilitaryEquipment.Telecom;

public class DisconnectTelecom implements Command {
    private final Telecom telecom;
    private final String name = "DisconnectTelecom";

    public String getName() {
        return name;
    }

    @Override
    public void undo() {
        telecom.connect();
    }

    public DisconnectTelecom(Telecom telecom) {
        this.telecom = telecom;
    }

    @Override
    public void execute() {
        telecom.disconnect();
    }
}
