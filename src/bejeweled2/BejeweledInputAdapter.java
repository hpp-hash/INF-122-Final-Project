package bejeweled2;

import tmge.Input;

public class BejeweledInputAdapter implements Input {

    private BejeweledController controller;

    public BejeweledInputAdapter(BejeweledController controller) {
        this.controller = controller;
    }

    @Override
    public void onMoveInput() {
        controller.moveOnKeyPress();
        // other methods can go here
    }

    @Override
    public void onNonMoveInput() {
        controller.otherMove();
        // other methods can go here
    }
}
