package org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.RawButton;

/**
 * A button that flips its state on button press
 */
public class ToggleableButtonController extends ButtonController<Boolean> {
    private final DebouncedButtonController button;
    private boolean state;
    public ToggleableButtonController(RawButton button, boolean initialState) {
        this.button = new DebouncedButtonController(button);
        state = initialState;
    }

    @Override
    protected Boolean processTick() {
        if (button.processTick()) state = !state;
        return state;
    }

    public void set(boolean newState){
        this.state = newState;
    }
}
