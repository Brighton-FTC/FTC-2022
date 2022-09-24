package org.firstinspires.ftc.teamcode.opModes.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.TankDrive;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers.DriveTrainController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.CosMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.RootMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.ButtonName;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.Inputs;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers.DebouncedButtonController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.modeBases.TeleOpModeBase;

@TeleOp(name = "Create drivetrain curves", group = "Test")
public class CreateDrivetrainCurves extends TeleOpModeBase {
    DriveTrainController driveTrain;

    DebouncedButtonController freezeReadings;

    double storedX;
    double storedY;
    double storedDesiredX;
    double storedDesiredY;

    @Override
    public void setup() {
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
        freezeReadings = new DebouncedButtonController(ButtonName.R_BUMPER);
    }

    @Override
    public void loop() {
        XY leftJoystick = Inputs.getLeftJoystickData();
        XY rightJoystick = Inputs.getRightJoystickData();
        double speed = -leftJoystick.y;
        double turn = leftJoystick.x;

        driveTrain.drive_scaled(speed, turn);

        telemetry.addData("x", leftJoystick.x);
        telemetry.addData("Desired x", rightJoystick.x);
        telemetry.addData("y", -leftJoystick.y);
        telemetry.addData("Desired y", -rightJoystick.y);

        if(freezeReadings.processTick()) {
            storedX = leftJoystick.x;
            storedY = -leftJoystick.y;
            storedDesiredX = rightJoystick.x;
            storedDesiredY = -rightJoystick.y;
        }

        telemetry.addData("Stored x", storedX);
        telemetry.addData("Stored desired x", storedDesiredX);
        telemetry.addData("Stored y", storedY);
        telemetry.addData("Stored desired y", storedDesiredY);

        telemetry.update();
    }
}
