package org.firstinspires.ftc.teamcode.opModes.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.TankDrive;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers.DriveTrainController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.CosMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.CubicMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.JoystickMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.LinearMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.RootMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.ButtonName;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.Inputs;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers.IncrementButtonController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.modeBases.TeleOpModeBase;

@TeleOp(name = "Test drivetrain curves", group = "Test")
public class TestDrivetrainCurves extends TeleOpModeBase {
    DriveTrainController driveTrain;

    final JoystickMapping[] mappings = new JoystickMapping[] {
            new CosMapping(),
            new CubicMapping(),
            new LinearMapping(),
            new RootMapping(2),
            new RootMapping(3),
            new RootMapping(4),
    };

    final IncrementButtonController speedMappingSelector = new IncrementButtonController(ButtonName.D_UP, ButtonName.D_DOWN, 0, 1);
    final IncrementButtonController turningMappingSelector = new IncrementButtonController(ButtonName.D_RIGHT, ButtonName.D_LEFT, 0, 1);

    @Override
    public void setup(){
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
        int speedMappingIndex = (int) speedMappingSelector.processTick();
        int turnMappingIndex = (int) turningMappingSelector.processTick();

        // make it very hard to overflow into the negatives, and make it wrap around

        speedMappingIndex = (speedMappingIndex + mappings.length * 1000) % mappings.length;
        turnMappingIndex = (turnMappingIndex + mappings.length * 1000) % mappings.length;

        driveTrain.setSpeedMapping(mappings[speedMappingIndex]);
        driveTrain.setTurnMapping(mappings[turnMappingIndex]);

        XY leftJoystick = Inputs.getLeftJoystickData();
        double speed = -leftJoystick.y;
        double turn = -leftJoystick.x;

        driveTrain.drive_scaled(speed, turn);

        telemetry.addData("speed", speed);
        telemetry.addData("turn", turn);
        telemetry.addData("Speed mapping:", driveTrain.getSpeedMapping().toString());
        telemetry.addData("Turn mapping:", driveTrain.getTurnMapping().toString());

        telemetry.update();
    }
}
