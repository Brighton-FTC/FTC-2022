package org.firstinspires.ftc.teamcode.opModes.team1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.hardware.subsystems.DiscretePositionArm;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.TankDrive;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.subsystems.drivetrain.controllers.DriveTrainController;
import org.firstinspires.ftc.teamcode.hardware.subsystems.ServoGrabber;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.CosMapping;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.inputs.joystickMappings.RootMapping;
import org.firstinspires.ftc.teamcode.opModes.subroutines.autonomous.DriveForwardCounts;
import org.firstinspires.ftc.teamcode.libs.brightonCollege.modeBases.AutonomousModeBase;

public abstract class AutonomousGeneric extends AutonomousModeBase {
    protected boolean isSpinnerReversed;
    @Override
    public void run() throws InterruptedException {
        setup();
        waitForStart();
        DiscretePositionArm arm = new DiscretePositionArm(
                hardwareMap.get(DcMotor.class, "arm"),
                false,
                Constants.TEAM1_ARM_FRONT_COUNTS,
                Constants.TEAM1_ARM_BACK_COUNTS
        );
        DriveTrainController driveTrain = new DriveTrainController(new TankDrive(
                hardwareMap.get(DcMotor.class, "left_drivetrain_motor"),
                hardwareMap.get(DcMotor.class, "right_drivetrain_motor"),
                true
        ),
                new RootMapping(2),
                new CosMapping(),
                0,0
        );
        ServoGrabber grabber = new ServoGrabber(hardwareMap.get(Servo.class, "grabber"), Constants.TEAM1_GRABBER_CLOSED_POS, Constants.TEAM1_GRABBER_OPEN_POS);
        arm.moveToFront(Constants.TEAM1_ARM_SPEED);
        grabber.setClosed(true);

        /* Drive forwards into the warehouse */
        new DriveForwardCounts().run(this, driveTrain, 1000, 0.20);
    }

    public abstract void setup();
}
