package org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.RawButton;

/**
 * A button that is only triggered for one tick when pressed
 */
public class DebouncedButtonController extends ButtonController<Boolean> {
    private final RawButton button;
    private boolean wasPressedLastTick;
    public DebouncedButtonController(RawButton button) {
        this.button = button;
        this.wasPressedLastTick = false;
    }

    @Override
    protected Boolean processTick() {
        boolean isDown = button.isPressed();

        boolean ret = isDown && !wasPressedLastTick;

        this.wasPressedLastTick = isDown;

        return ret;
    }
}
