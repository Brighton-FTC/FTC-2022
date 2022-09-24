package org.firstinspires.ftc.teamcode.opModes.testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Timer;
import org.firstinspires.ftc.teamcode.hardware.subsystems.CarouselSpinner;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DiscretePositionArm;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.TankDrive;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers.DriveTrainController;
import org.firstinspires.ftc.teamcode.hardware.subsystems.ServoGrabber;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.CosMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.RootMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.ButtonName;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.Inputs;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers.DebouncedButtonController;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.buttonControllers.ToggleableButtonController;
import org.firstinspires.ftc.teamcode.opModes.subroutines.autonomous.team1.Team1Deposit;
import org.firstinspires.ftc.teamcode.opModes.subroutines.autonomous.team2.Team2Deposit;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.modeBases.AutonomousModeBase;

@TeleOp(name = "Puppeteer test autonomous", group = "Test")
public class PuppeteerTestAutonomous extends AutonomousModeBase {
    DebouncedButtonController startDriveForward;
    DebouncedButtonController startTurn;
    DebouncedButtonController startDeposit;
    DebouncedButtonController startDeliver;

    ToggleableButtonController isTeam1Button;

    PuppeteerTestAutonomousState state = PuppeteerTestAutonomousState.FORWARD;

    private DriveTrainController driveTrain;
    private CarouselSpinner spinner;

    private long lastActionTime = 0;

    private final Timer actionTimer = new Timer();


    @Override
    public void run() throws InterruptedException {
        // Inputs
        isTeam1Button = new ToggleableButtonController(ButtonName.R_BUMPER, true);
        startDriveForward = new DebouncedButtonController(ButtonName.D_UP);
        startTurn = new DebouncedButtonController(ButtonName.D_LEFT);
        startDeposit = new DebouncedButtonController(ButtonName.D_RIGHT);
        startDeliver = new DebouncedButtonController(ButtonName.D_DOWN);

        spinner = new CarouselSpinner(hardwareMap.get(DcMotor.class, "motor_3"), false);

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

        waitForStart();

        while (opModeIsActive()) {
            long currentTime = System.currentTimeMillis();

            boolean isTeam1 = isTeam1Button.processTick();

            boolean isSwitchToForward = startDriveForward.processTick();
            boolean isSwitchToTurn = startTurn.processTick();
            boolean isSwitchToDeposit = startDeposit.processTick();
            boolean isSwitchToDeliver = startDeliver.processTick();

            if (isSwitchToForward) {
                state = PuppeteerTestAutonomousState.FORWARD;
                driveTrain.resetEncoders();
            }
            if (isSwitchToTurn) {
                state = PuppeteerTestAutonomousState.TURN;
                driveTrain.resetEncoders();
            }
            if (isSwitchToDeposit) state = PuppeteerTestAutonomousState.DEPOSIT;
            if (isSwitchToDeliver) state = PuppeteerTestAutonomousState.DELIVER;

            telemetry.addData("Is team 1:", isTeam1);
            telemetry.addData("Time to complete the last action:", lastActionTime);
            telemetry.addData("Time since last action:", actionTimer.getTimePassed());
            telemetry.addData("Current action:", state);

            XY leftJoystick = Inputs.getLeftJoystickData();

            if (isSwitchToForward || isSwitchToTurn || isSwitchToDeposit || isSwitchToDeliver)
            {
                lastActionTime = actionTimer.getTimePassed();
                // stop all
                spinner.spin(0);

                actionTimer.reset();
            }

            switch (state){
                case FORWARD:
                    driveTrain.drive_unscaled(-leftJoystick.y, 0);
                    telemetry.addData("Right pos", driveTrain.tankDrive.rightMotor.getCurrentPosition());
                    telemetry.addData("Left pos", driveTrain.tankDrive.leftMotor.getCurrentPosition());
                    break;
                case TURN:
                    driveTrain.drive_unscaled(0, -leftJoystick.x);
                    telemetry.addData("Right pos", driveTrain.tankDrive.rightMotor.getCurrentPosition());
                    telemetry.addData("Left pos", driveTrain.tankDrive.leftMotor.getCurrentPosition());
                    break;
                case DELIVER:
                    spinner.spin(0.5);
                    break;
                case DEPOSIT:
                    if (isTeam1){
                        new Team1Deposit().run(this,
                                new DiscretePositionArm(
                                        hardwareMap.get(DcMotor.class, "motor_2"),
                                        false,
                                        Constants.TEAM1_ARM_FRONT_COUNTS,
                                        Constants.TEAM1_ARM_BACK_COUNTS
                                ),
                                new ServoGrabber(hardwareMap.get(Servo.class, "servo_3"), Constants.TEAM1_GRABBER_CLOSED_POS, Constants.TEAM1_GRABBER_OPEN_POS)
                        );
                    } else {
                        new Team2Deposit().run(this,
                                new DiscretePositionArm(
                                        hardwareMap.get(DcMotor.class, "motor_2"),
                                        false,
                                        Constants.TEAM2_SLIDE_FRONT_COUNTS,
                                        Constants.TEAM2_SLIDE_BACK_COUNTS
                                )
                        );
                    }
                    state = PuppeteerTestAutonomousState.FORWARD;
                    break;
            }

            telemetry.update();
            idle();
        }
    }
}
