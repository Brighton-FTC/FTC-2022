package org.firstinspires.ftc.teamcode.opModes.team2;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Team 2 - TeleOp Red", group = "2_TeleOp")
public class TeleOpRed extends TeleOpGeneric {
    @Override
    public void setup() {
        custom_setup();
    }

    @Override
    public void every_tick() {
        custom_loop(true);
    }
}
