package org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Inputs {
    public static Gamepad gamepad1;
    public static Gamepad gamepad2;

    private static int tick;

    public static void init(Gamepad _gamepad1, Gamepad _gamepad2){
        gamepad1 = _gamepad1;
        gamepad2 = _gamepad2;
        tick = 0;
    }

    public static void tickEnd(){
        tick += 1;
    }

    public static int getTick() {
        return tick;
    }
}


