package org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.RawButton;

/**
 * A button that is triggered while the button is pressed
 */
public class HoldButtonController extends ButtonController<Boolean> {
    private final RawButton button;
    public HoldButtonController(RawButton button) {
        this.button = button;
    }

    /**
     * Return true if held down
     * @return true if held down
     */
    @Override
    protected Boolean processTick() {
        return button.isPressed();
    }
}
