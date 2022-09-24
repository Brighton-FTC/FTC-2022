package org.firstinspires.ftc.teamcode.libs.brightonCollege.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.TankDrive;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers.DriveTrainController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.CosMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.RootMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.ButtonName;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers.IncrementButtonController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers.ToggleableButtonController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.modeBases.TeleOpModeBase;

@TeleOp(name = "Test turn counts", group = "Test")
public class TestTurnCounts extends TeleOpModeBase {
    IncrementButtonController numCountsSelector;
    ToggleableButtonController driveToggle;
    DriveTrainController driveTrain;
    double SPEED = 0.5;

    @Override
    public void setup(){
        // Inputs
        numCountsSelector = new IncrementButtonController(ButtonName.D_UP, ButtonName.D_DOWN, 100, 10);
        driveToggle = new ToggleableButtonController(ButtonName.TRIANGLE, false);
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
        boolean isDriving = driveToggle.processTick();

        if(isDriving) {
            // Display if driving
            telemetry.addLine("Turning");
            telemetry.update();
            // Drive
            driveTrain.startDrivingCounts(numCounts, -numCounts, SPEED); // One forwards, one back
        } else {
            // Display if not driving
            telemetry.addData("Number of counts to turn by (pos. is clockwise, neg. is anticlockwise)", numCounts);
            telemetry.addLine("Use the DPAD Up/Down to change num counts");
            telemetry.addLine("Triangle to turn");
            telemetry.update();
        }
    }
}
