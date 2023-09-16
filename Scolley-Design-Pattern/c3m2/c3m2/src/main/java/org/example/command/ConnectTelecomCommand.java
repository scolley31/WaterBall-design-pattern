package org.example.command;

import org.example.Telecom;

public class ConnectTelecomCommand implements Command {

    private final Telecom telecom;

    public ConnectTelecomCommand(Telecom telecom) {
        this.telecom = telecom;
    }

    @Override
    public void execute() {
        telecom.connect();
    }

    @Override
    public void undo() {
        telecom.disconnect();
    }

    @Override
    public String getName() {
        return "ConnectTelecomCommand";
    }


}
