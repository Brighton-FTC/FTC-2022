package org.firstinspires.ftc.teamcode.opModes.team1;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.hardware.subsystems.ServoGrabber;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.ButtonName;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.InputSide;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.RawButton;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers.ToggleableButtonController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickControllers.RawJoystick;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.modeBases.TeleOpModeBase;

@TeleOp(name = "Team 1 - Move grabber", group = "1_TeleOp")
abstract public class TeleOpMoveGrabber extends TeleOpModeBase {
    private ServoGrabber grabber;
    private ToggleableButtonController dirToggle;
    private RawJoystick leftJoystick = new RawJoystick(gamepad1, InputSide.LEFT_INPUT);

    @Override
    public void setup() {
        grabber = new ServoGrabber(hardwareMap.get(Servo.class, "grabber"), Constants.TEAM1_GRABBER_CLOSED_POS, Constants.TEAM1_GRABBER_OPEN_POS);
        dirToggle = new ToggleableButtonController(new RawButton(gamepad1, ButtonName.CROSS), false);
    }

    @Override
    public void loop() {
        boolean isReversed = dirToggle.getValue();

        grabber.setServoReversed(isReversed);

        grabber.rotateTo(leftJoystick.get_y() * 0.4);
        telemetry.addData("Joystick", leftJoystick.get_y());
        telemetry.addData("isReversed", isReversed);
        telemetry.addData("Pos", grabber.getRotation());

        telemetry.update();
    }
}
