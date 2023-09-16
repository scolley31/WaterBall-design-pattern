package org.example.command;

import org.example.Telecom;

public class DisconnectTelecomCommand implements Command {

    private final Telecom telecom;


    public DisconnectTelecomCommand(Telecom telecom) {
        this.telecom = telecom;
    }

    @Override
    public void execute() {
        telecom.disconnect();
    }

    @Override
    public void undo() {
        telecom.connect();
    }

    @Override
    public String getName() {
        return "DisconnectTelecomCommand";
    }

}
