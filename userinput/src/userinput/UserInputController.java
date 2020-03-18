package userinput;

public class UserInputController
{
    private Input input;

    private static UserInputController controller;

    private UserInputController(Input input) {
        this.input = input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public static UserInputController getInstance(Input input) {
        if (controller == null) {
            controller = new UserInputController(input);
        }
        return controller;
    }
    
    public void onInput(){
        input.onMoveInput();
        input.onNonMoveInput();
    }

}
