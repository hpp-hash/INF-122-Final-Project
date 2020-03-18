package userinput;

public interface Input {
    // Handling input dealing with translational movement of a tile entity (e.g. move left, right, up, down)
    public void onMoveInput();

    // Handling any input not dealing with translational movement of a tile entity (e.g. rotation)
    public void onNonMoveInput();
}
