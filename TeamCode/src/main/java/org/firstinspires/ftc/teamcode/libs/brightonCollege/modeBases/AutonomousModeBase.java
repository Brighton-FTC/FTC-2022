package org.firstinspires.ftc.teamcode.libs.brightonCollege.modeBases;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.util.TelemetryContainer;

public abstract class AutonomousModeBase extends LinearOpMode {
    public abstract void run() throws InterruptedException;

    /**
     * A class that wraps a LinearOpMode to set up inputs and outputs so they're accessible by classes
     */
    @Override
    public void runOpMode() throws InterruptedException {
        // Initialise the telemetry
        TelemetryContainer.setTelemetry(telemetry);

        run();
    }
}
