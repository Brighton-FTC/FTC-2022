package org.firstinspires.ftc.teamcode.opModes.subroutines;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.TankDrive;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers.DriveTrainController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.CosMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.RootMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.util.TelemetryContainer;

public class AutonomousDriveForward {
    // TODO: Should we move this to OpModes/subroutines as I think it is not team-specific?
    public void run(LinearOpMode opMode, double forwardGradient, double forwardIntercept) throws InterruptedException {
        // Get hardwareMap
        HardwareMap hardwareMap = opMode.hardwareMap;

        // BODY
        DriveTrainController driveTrain = new DriveTrainController(new TankDrive(
                hardwareMap.get(DcMotor.class, "left_drivetrain_motor"),
                hardwareMap.get(DcMotor.class, "right_drivetrain_motor"),
                false
        ),
                new RootMapping(2),
                new CosMapping(),
                forwardGradient,
                forwardIntercept
        );
        Telemetry telemetry = TelemetryContainer.getTelemetry();

        driveTrain.startDrivingForward(3, 1);
        while (driveTrain.isBusy()) { opMode.sleep(50); }
        telemetry.addData("Subroutine", "completed");
    }
}
