package org.firstinspires.ftc.teamcode.wrappers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.inputs.GamepadWrapper;
import org.firstinspires.ftc.teamcode.libs.util.TelemetryContainer;

public abstract class OpModeWrapper extends OpMode {
    /**
     * A class that wraps an OpMode to set up inputs and outputs so they're accessible by classes
     */
    public abstract void setup();

    @Override
    public void init() {
        GamepadWrapper.createGamepadWrapper(gamepad1);
        TelemetryContainer.setTelemetry(telemetry);
        setup();
    }
}
