package org.firstinspires.ftc.teamcode.libs.brightonCollege.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.TankDrive;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers.DriveTrainController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.CosMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.RootMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.ButtonName;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers.DebouncedButtonController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers.IncrementButtonController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.modeBases.TeleOpModeBase;

@TeleOp(name = "Test drive counts", group = "Test")
public class TestDriveCounts extends TeleOpModeBase {
    IncrementButtonController numCountsSelector;
    DebouncedButtonController startDriveButton;
    DriveTrainController driveTrain;
    double SPEED = 1.0;

    @Override
    public void setup(){
        // Inputs
        numCountsSelector = new IncrementButtonController(ButtonName.D_UP, ButtonName.D_DOWN, 1000, 500);
        startDriveButton = new DebouncedButtonController(ButtonName.TRIANGLE);
        // Actuators
        driveTrain = new DriveTrainController(new TankDrive(
                hardwareMap.get(DcMotor.class, "motor_0"),
                hardwareMap.get(DcMotor.class, "motor_1"),
                false
        ),
                new RootMapping(2),
                new CosMapping(),
    0.0,
    0.0
        );
    }

    @Override
    public void loop() {
        // Get inputs
        int numCounts = (int) numCountsSelector.processTick();
        boolean isDriving = startDriveButton.processTick();

        if(isDriving) {
            // Drive
            driveTrain.startDrivingCounts(numCounts, numCounts, SPEED);
        } else {
            // Display if not driving
            telemetry.addData("Number of counts to drive", numCounts);
            telemetry.addLine("Use the DPAD Up/Down to change num counts");
            telemetry.addLine("Triangle to drive");
        }
        telemetry.addData("IsBusy", driveTrain.isBusy());
        telemetry.update();
    }
}
