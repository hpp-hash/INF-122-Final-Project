package bejeweled2;

import userinput.Input;

public class BejeweledInputAdapter implements Input {

    private BejeweledController controller;

    public BejeweledInputAdapter(BejeweledController controller) {
        this.controller = controller;
    }

    @Override
    public void onMoveInput() {
        controller.moveOnMouseDrag();
    }

    @Override
    public void onNonMoveInput() {
        controller.otherMove();
    }
}
