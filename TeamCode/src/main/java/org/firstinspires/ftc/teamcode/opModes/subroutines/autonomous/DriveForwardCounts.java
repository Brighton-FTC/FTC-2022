package org.firstinspires.ftc.teamcode.opModes.subroutines.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers.DriveTrainController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.util.TelemetryContainer;

public class DriveForwardCounts {
    public void run(LinearOpMode opMode, DriveTrainController driveTrain, int counts, double speed) throws InterruptedException {
        driveTrain.startDrivingCounts(counts, counts, speed);
        while (driveTrain.isBusy()) {
            if (opMode.isStopRequested()) return;
            opMode.sleep(50);
        }
        Telemetry telemetry = TelemetryContainer.getTelemetry();
        telemetry.addData("Drive Forward - Is drivetrain busy", driveTrain.isBusy());
    }
}
