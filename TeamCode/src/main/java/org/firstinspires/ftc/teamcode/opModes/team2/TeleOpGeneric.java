package org.firstinspires.ftc.teamcode.opModes.team2;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.hardware.subsystems.CarouselSpinner;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.TankDrive;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers.DriveTrainController;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DiscretePositionArm;
import org.firstinspires.ftc.teamcode.hardware.subsystems.ServoIntake;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.LinearMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.RootMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.ButtonName;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.Inputs;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers.DebouncedButtonController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers.ToggleableButtonController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.util.Maths;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.modeBases.TeleOpModeBase;

abstract class TeleOpGeneric extends TeleOpModeBase {

    private DriveTrainController driveTrain;
    private CarouselSpinner spinner;
    private DiscretePositionArm arm;
    private ServoIntake intake;
    
    DebouncedButtonController raiseArmButton;
    DebouncedButtonController floatArmButton;
    DebouncedButtonController powerDownArmButton;
    ToggleableButtonController isIntakeEnabledButton;

    public void custom_setup() {
        raiseArmButton = new DebouncedButtonController(ButtonName.TRIANGLE);
        floatArmButton = new DebouncedButtonController(ButtonName.CIRCLE);
        powerDownArmButton = new DebouncedButtonController(ButtonName.CROSS);
        isIntakeEnabledButton = new ToggleableButtonController(ButtonName.SQUARE, false);
        spinner = new CarouselSpinner(hardwareMap.get(DcMotor.class, "carousel_spinner"), false);
        driveTrain = new DriveTrainController(new TankDrive(
                hardwareMap.get(DcMotor.class, "left_drivetrain_motor"),
                hardwareMap.get(DcMotor.class, "right_drivetrain_motor"),
                false
        ),
                new RootMapping(2),
                new LinearMapping(),
                Constants.TEAM2_DRIVETRAIN_FORWARDS_GRADIENT,
                Constants.TEAM2_DRIVETRAIN_FORWARDS_INTERCEPT
        );
        arm = new DiscretePositionArm(
                hardwareMap.get(DcMotor.class, "slide"),
                false,
                Constants.TEAM2_SLIDE_FRONT_COUNTS,
                Constants.TEAM2_SLIDE_BACK_COUNTS
        );
        intake = new ServoIntake(hardwareMap.get(CRServo.class, "intake"), true);
    }

    /**
     * First movement done here.
     */
    @Override
    public void start() {
        arm.moveToFront(Constants.TEAM2_SLIDE_SPEED); // Hover
    }

    public void custom_loop(boolean isSpinnerInverted) {
        /* Carousel Spinner*/
        // CONTROLS: use the triggers to rotate left or right
        double spinnerSpeed = Inputs.getRightTriggerData() - Inputs.getLeftTriggerData();
        if (isSpinnerInverted) spinnerSpeed *= -1;
        spinner.spin(spinnerSpeed);

        /* Intake servo*/
        // CONTROLS: use the direction pad up/down

        if(raiseArmButton.processTick()) {
            arm.moveToBack(Constants.TEAM2_SLIDE_SPEED);
            isIntakeEnabledButton.set(false);
        }
        if(floatArmButton.processTick()) arm.moveToFront(Constants.TEAM2_SLIDE_SPEED);
        if(powerDownArmButton.processTick()) {
            arm.powerDown();
            isIntakeEnabledButton.set(true);
        }

        if (isIntakeEnabledButton.processTick()) intake.spin(Constants.TEAM2_INTAKE_SPEED);
        else intake.spin(0.0);

        // if arm powered down, slow down for more control
        boolean isArmPoweredDown = arm.getPower() == 0;

        boolean isBoost = Inputs.isPressed(ButtonName.D_UP);

        double scale = 0.5;
        if(isArmPoweredDown) scale = 0.3;
        if(isBoost) scale = 1.0;

        /* Drivetrain */
        // CONTROLS: Left joystick
        XY leftJoystick = Inputs.getLeftJoystickData();
        XY rightJoystick = Inputs.getRightJoystickData();

        double speed = Maths.clamp(-leftJoystick.y - rightJoystick.y, -1, 1);
        double turn = Maths.clamp(-leftJoystick.x + rightJoystick.x, -1, 1);

        driveTrain.drive_scaled(speed, turn, scale, 1.3);

        telemetry.update();
    }
}
