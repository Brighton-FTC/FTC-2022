package org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs;

import com.qualcomm.robotcore.hardware.Gamepad;

public class RawButton {
    private final ButtonName button;
    private final Gamepad gamepad;

    public RawButton(Gamepad gamepad, ButtonName button) {
        this.button = button;
        this.gamepad = gamepad;
    }

    /**
     * Check whether this button is pressed
     * @return whether it is pressed
     */
    public boolean isPressed() {
        boolean result = false;
        switch(button) {
            case CIRCLE:
                result = gamepad.circle;
                break;
            case CROSS:
                result = gamepad.cross;
                break;
            case SQUARE:
                result = gamepad.square;
                break;
            case TRIANGLE:
                result = gamepad.triangle;
                break;
            case L_BUMPER:
                result = gamepad.left_bumper;
                break;
            case L_STICK:
                result = gamepad.left_stick_button;
                break;
            case R_BUMPER:
                result = gamepad.right_bumper;
                break;
            case R_STICK:
                result = gamepad.right_stick_button;
                break;
            case D_UP:
                result = gamepad.dpad_up;
                break;
            case D_RIGHT:
                result = gamepad.dpad_right;
                break;
            case D_DOWN:
                result = gamepad.dpad_down;
                break;
            case D_LEFT:
                result = gamepad.dpad_left;
                break;
        }
        return result;
    }
}
