package org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickControllers;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.InputSide;

/**
 * A raw joystick that gives raw data from the controller
 */
public class RawJoystick {
    private final Gamepad gamepad;
    private final InputSide inputSide;

    public RawJoystick(Gamepad gamepad, InputSide inputSide) {
        this.gamepad = gamepad;
        this.inputSide = inputSide;
    }

    public float get_x(){
        if (inputSide == InputSide.RIGHT_INPUT) return gamepad.right_stick_x;
        return gamepad.left_stick_x;
    }

    public float get_y(){
        if (inputSide == InputSide.RIGHT_INPUT) return gamepad.right_stick_y;
        return gamepad.left_stick_y;
    }
}
