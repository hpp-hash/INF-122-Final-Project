package tmge;

public class UserInputController
{
    private Input input;

    private static UserInputController controller;

    private UserInputController() {}

    public void setInput(Input input) {
        this.input = input;
    }

    public static UserInputController getInstance() {
        if (controller == null) {
            controller = new UserInputController();
        }
        return controller;
    }
    
    public void onInput(){
        input.onMoveInput();
        input.onNonMoveInput();
    }

}
