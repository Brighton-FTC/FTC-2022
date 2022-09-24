package org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.triggerControllers;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.InputSide;

public class RawTrigger {
    private final Gamepad gamepad;
    private final InputSide inputSide;

    public RawTrigger(Gamepad gamepad, InputSide inputSide) {
        this.gamepad = gamepad;
        this.inputSide = inputSide;
    }

    /**
     * Get the extent to which the trigger is pressed down
     * @return the level of the trigger
     */
    public float get_level(){
        if (inputSide == InputSide.RIGHT_INPUT) return gamepad.right_trigger;
        return gamepad.left_trigger;
    }
}
