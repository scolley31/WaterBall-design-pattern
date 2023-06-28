package Command;

import Base.Keyboard;
import MilitaryEquipment.Telecom;

public class ConnectTelecom implements Command {
    private final Telecom telecom;
    private final String name = "ConnectTelecom";

    public ConnectTelecom(Telecom telecom) {
        this.telecom = telecom;
    }

    public String getName() {
        return name;
    }

    @Override
    public void undo() {
        telecom.disconnect();
    }

    @Override
    public void execute() {
        telecom.connect();
    }
}
