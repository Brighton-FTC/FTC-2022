package org.firstinspires.ftc.teamcode.libs.brightonCollege.modeBases;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.Inputs;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.util.TelemetryContainer;

public abstract class TeleOpModeBase extends OpMode {
    /**
     * A class that wraps an OpMode to set up inputs and outputs so they're accessible by classes
     */
    public abstract void setup();
    public abstract void every_tick();

    @Override
    public void init() {
        // initialise the controls and the telemetry
        Inputs.init(gamepad1, gamepad2);
        TelemetryContainer.setTelemetry(telemetry);
        setup();
    }

    /**
     * Things to be done every tick
     */
    @Override
    public void loop() {
        every_tick();
        // Make sure that the Inputs are reset
        Inputs.tickEnd();
    }
}
