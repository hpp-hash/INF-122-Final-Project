public class UserInputController
{
    public KeyboardBehavior keyboardBehavior;
    public MouseBehavior mouseBehavior;
    private UserInputController controller;

    private UserInputController() {}

    public void setKeyboardBehavior(KeyboardBehavior keyboardBehavior) {
        this.keyboardBehavior = keyboardBehavior;
    }

    public void setMouseBehavior(MouseBehavior mouseBehavior) {
        this.mouseBehavior = mouseBehavior;
    }

    public UserInputController getInstance() {
        if (controller == null){
            controller = new UserInputController();
        }
        return controller;
    }

    public void onKeyboardEvent() {
        keyboardBehavior.onKeyboardEvent();
    }

    public void onMouseEvent() {
        mouseBehavior.onMouseEvent();
    }
}
