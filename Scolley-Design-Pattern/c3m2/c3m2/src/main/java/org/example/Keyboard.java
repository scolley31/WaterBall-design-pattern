package org.example;

import java.util.List;

public class Keyboard {

    private List<Button> buttons;

    public Keyboard() {
       initButtons();
    }

    private void initButtons() {
        buttons = List.of(
                new Button(Lowercase.A),
                new Button(Lowercase.B),
                new Button(Lowercase.C),
                new Button(Lowercase.D),
                new Button(Lowercase.E),
                new Button(Lowercase.F),
                new Button(Lowercase.G),
                new Button(Lowercase.H),
                new Button(Lowercase.I),
                new Button(Lowercase.J),
                new Button(Lowercase.K),
                new Button(Lowercase.L),
                new Button(Lowercase.M),
                new Button(Lowercase.N),
                new Button(Lowercase.O),
                new Button(Lowercase.P),
                new Button(Lowercase.Q),
                new Button(Lowercase.R),
                new Button(Lowercase.S),
                new Button(Lowercase.T),
                new Button(Lowercase.U),
                new Button(Lowercase.V),
                new Button(Lowercase.W),
                new Button(Lowercase.X),
                new Button(Lowercase.Y),
                new Button(Lowercase.Z)
        );
    }

    public Button pressButton(String lowercase) {
        return buttons.stream().filter(button -> button.getLowercase().getValue().equals(lowercase)).findFirst().orElseThrow();
    }

}
